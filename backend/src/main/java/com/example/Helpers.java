package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kong.unirest.Unirest;
import kong.unirest.HttpResponse;

import org.springframework.stereotype.Component;

@Component

public class Helpers {
    public List<DataModel.Cart> carts = new ArrayList<>();

    public List<DataModel.Cart> getCarts() {
        return carts;
    }
    
    public String createSharedCart(String hostId, List<String> participants, Date deadline) {
        String cartId = "cart" + (carts.size() + 1);
        DataModel.Cart cart = new DataModel.Cart(cartId, hostId, participants, new ArrayList<>(), "Open", 5.0, deadline);
        carts.add(cart);
        System.out.println("Cart created: " + cart);
        return cartId;
    }

    public void addItemToCart(String cartId, String userId, DataModel.Item item) {
        DataModel.Cart cart = null;
        for (DataModel.Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart != null) {
            item.setUserId(userId); 
            cart.getItems().add(item);
            System.out.println("Item added to cart: " + item);
        } else {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    }
    
    public List<DataModel.Item> viewCartDetails(String cartId) {
        DataModel.Cart cart = null;
        for (DataModel.Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
        return cart != null ? cart.getItems() : new ArrayList<>();
    }

    public double calculateTotalCost(String cartId) {
        DataModel.Cart cart = null;
        for (DataModel.Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
        double totalCost = 0.0;
        if (cart != null) {
            for (DataModel.Item item : cart.getItems()) {
                totalCost += item.getQuantity() * item.getPrice();
            }
        }
        return totalCost;
    }
    
    public Map<String, Double> calculateIndividualCosts(String cartId) {

        DataModel.Cart cart = null;
        for (DataModel.Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        if (cart.getParticipants() == null || cart.getParticipants().isEmpty()) {
            throw new IllegalStateException("Cart with ID " + cartId + " has no participants.");
        }
    
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
    
        Map<String, Double> individualCosts = new HashMap<>();
    
        for (DataModel.Item item : cart.getItems()) {
            String userId = item.getUserId();
            
            if (userId == null || userId.isEmpty()) {
                throw new IllegalStateException("Item in cart " + cartId + " is missing a userId.");
            }
    
            double itemCost = item.getQuantity() * item.getPrice();
            individualCosts.put(userId, individualCosts.getOrDefault(userId, 0.0) + itemCost);
        }
    
        double deliveryFeeSplit = calculateDeliveryFeeSplit(cart.getGroupDeliveryFee(), cart.getParticipants().size());
        for (String userId : cart.getParticipants()) {
            individualCosts.put(userId, individualCosts.getOrDefault(userId, 0.0) + deliveryFeeSplit);
        }
    
        return individualCosts;
    }

    public double calculateDeliveryFeeSplit(double groupDeliveryFee, int participants) {
        if (participants <= 0) {
            throw new IllegalArgumentException("Number of participants must be greater than 0.");
        }
        return groupDeliveryFee / participants;
    }
    
    public double calculateSavings(double individualDeliveryFee, double groupDeliveryFee, int participants) {
        if (participants <= 0) {
            throw new IllegalArgumentException("Number of participants must be greater than 0.");
        }
    
        double totalIndividualDeliveryFee = individualDeliveryFee * participants;
    
        double savings = totalIndividualDeliveryFee - groupDeliveryFee;
    
        return savings;
    }
    
    public  DataModel.SavingsSummary getSavingsDetails(String cartId, double individualDeliveryFee, double groupDeliveryFee) {
        DataModel.Cart cart = null;
        for (DataModel.Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        int numberOfParticipants = cart.getParticipants().size();
    
        double totalIndividualDeliveryFee = individualDeliveryFee * numberOfParticipants;
    
        double totalSavings = totalIndividualDeliveryFee - groupDeliveryFee;
    
        double individualSavings = (totalSavings / numberOfParticipants);
    
        return new DataModel.SavingsSummary(totalSavings, individualSavings, groupDeliveryFee, numberOfParticipants);
    }

    public void finalizeOrder(String cartId) {
            DataModel.Cart cart = null;
            for (DataModel.Cart c : carts) {
                if (c.getCartId().equals(cartId)) {
                    cart = c;
                    break;
                }
            }
        
            if (cart == null) {
                throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
            }
        
            cart.setStatus("Finalized");
            System.out.println("Order for cart " + cartId + " has been finalized.");
        }

    public String getOrderStatus(String cartId) {
            DataModel.Cart cart = null;
            for (DataModel.Cart c : carts) {
                if (c.getCartId().equals(cartId)) {
                    cart = c;
                    break;
                }
            }
        
            if (cart == null) {
                throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
            }
        
            return cart.getStatus();
        }

    public void checkAndAutoFinalizeOrder(String cartId, Date currentTime) {
            DataModel.Cart cart = null;
            for (DataModel.Cart c : carts) {
                if (c.getCartId().equals(cartId)) {
                    cart = c;
                    break;
                }
            }
        
            if (cart == null) {
                throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
            }
        
            if (currentTime.after(cart.getDeadline())) {
                finalizeOrder(cartId);
            }
        }
    
    public List<DataModel.Product> fetchProductsFromApi(String query) {
        try {
            HttpResponse<String> response = Unirest.get("https://world.openfoodfacts.org/api/v2/search")
                    .header("accept", "application/json")
                    .queryString("fields", "code,product_name,price")
                    .queryString("page_size", "50")
                    .queryString("query", query)
                    .asString();
    
            if (response.getStatus() == 200) {
                return parseApiResponse(response.getBody());
            } else {
                throw new RuntimeException("Failed to fetch products: " + response.getStatusText());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching products from API: " + e.getMessage(), e);
        }
    }

    public List<DataModel.Product> parseApiResponse(String response) {
        List<DataModel.Product> products = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        JsonArray productsArray = jsonResponse.getAsJsonArray("products");
    
        for (int i = 0; i < productsArray.size(); i++) {
            JsonObject productJson = productsArray.get(i).getAsJsonObject();
    
            String code = productJson.has("code") ? productJson.get("code").getAsString() : null;
            String name = productJson.has("product_name") ? productJson.get("product_name").getAsString() : "Unknown Product";
    
            double price = Math.random() * 10; 
            price = Double.parseDouble(String.format("%.2f", price));
            int stockQuantity = (int) (Math.random() * 100); 
    
            DataModel.Product product = new DataModel.Product(code, name, price, stockQuantity);
            products.add(product);
        }
        return products;
    }
    
    public boolean validateCartContents(String cartId) {
        DataModel.Cart cart = null;
        for (DataModel.Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        for (DataModel.Item item : cart.getItems()) {
            String productId = item.getProductId();
            List<DataModel.Product> fetchedProducts = fetchProductsFromApi(productId);
            if (fetchedProducts.isEmpty()) {
                System.out.println("Product with ID " + productId + " is invalid.");
                return false;
            }
        }
    
        System.out.println("All products in the cart are valid.");
        return true;
    }
    
}

    

