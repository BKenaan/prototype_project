package test.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.datamodel.Cart;
import com.datamodel.Item;
import com.datamodel.Product;
import com.datamodel.SavingsSummary;

public class Helpers {

    private List<Cart> carts = new ArrayList<>();

    public String createSharedCart(String hostId, List<String> participants, Date deadline) {
        // Generate a unique cart ID
        String cartId = UUID.randomUUID().toString();
        Cart cart = new Cart(cartId, hostId, participants, new ArrayList<>(), "Open", 5.0, deadline);
        carts.add(cart);
        return cartId;
    }

    public void addItemToCart(String cartId, String userId, Item item) {
        Cart cart = null;
        for (Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }

        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }

        item.setUserId(userId);
    
        
        cart.getItems().add(item);
    
        System.out.println("Item added to cart: " + item.getProductId() + " by user " + userId);
    }
    
    public void removeItemFromCart(String cartId, String itemId, String userId) {

        Cart cart = null;
        for (Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        Item itemToRemove = null;
        for (Item item : cart.getItems()) {
            if (item.getItemId().equals(itemId)) {
                
                if (!item.getUserId().equals(userId)) {
                    throw new IllegalArgumentException("User " + userId + " is not authorized to remove this item.");
                }
                itemToRemove = item;
                break;
            }
        }
    

        if (itemToRemove == null) {
            throw new IllegalArgumentException("Item with ID " + itemId + " does not exist in the cart.");
        }
    
        cart.getItems().remove(itemToRemove);
    
        System.out.println("Item removed from cart: " + itemId + " by user " + userId);
    }
    
    public List<Item> viewCartDetails(String cartId) {
        Cart cart = null;
        for (Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        return cart.viewItems();
    }


    public void finalizeOrder(String cartId) {
        Cart cart = null;
        for (Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        if (cart.getStatus().equalsIgnoreCase("Finalized")) {
            System.out.println("The cart with ID " + cartId + " is already finalized.");
            return;
        }
    
        cart.setStatus("Finalized");
    
        System.out.println("Order for cart ID " + cartId + " has been finalized.");
    }
    

    public String getOrderStatus(String cartId) {

        Cart cart = null;
        for (Cart c : carts) {
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

        Cart cart = null;
        for (Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        if (cart.getStatus().equalsIgnoreCase("Finalized")) {
            System.out.println("The cart with ID " + cartId + " is already finalized.");
            return;
        }
    
        if (currentTime.after(cart.getDeadline())) {
            cart.setStatus("Finalized");
            System.out.println("Order for cart ID " + cartId + " has been automatically finalized.");
        } else {
            System.out.println("Cart with ID " + cartId + " is still open. Deadline not reached.");
        }
    }
    
    public double calculateTotalCost(String cartId) {
        Cart cart = null;
        for (Cart c : carts) {
            if (c.getCartId().equals(cartId)) {
                cart = c;
                break;
            }
        }
    
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
        }
    
        double totalCost = 0;
        for (Item item : cart.getItems()) {
            totalCost += item.getQuantity() * item.getPrice();
        }
    
        return totalCost;
    }
    
    public Map<String, Double> calculateIndividualCosts(String cartId) {
      Cart cart = null;
      for (Cart c : carts) {
          if (c.getCartId().equals(cartId)) {
              cart = c;
              break;
          }
      }

      if (cart == null) {
          throw new IllegalArgumentException("Cart with ID " + cartId + " does not exist.");
      }

      // Initialize a map to store individual costs
      Map<String, Double> individualCosts = new HashMap<>();

      for (Item item : cart.getItems()) {
          String userId = item.getUserId();
          double itemCost = item.getQuantity() * item.getPrice();
          individualCosts.put(userId, individualCosts.getOrDefault(userId, 0.0) + itemCost);
      }

      double deliveryFeeSplit = calculateDeliveryFeeSplit(cart.getGroupDeliveryFee(), cart.getParticipants().size());

      for (String userId : individualCosts.keySet()) {
          individualCosts.put(userId, individualCosts.get(userId) + deliveryFeeSplit);
      }

      return individualCosts;
    }

    public double calculateDeliveryFeeSplit(double groupDeliveryFee, int participants) {
        if (participants <= 0) {
            throw new IllegalArgumentException("Number of participants must be greater than 0.");
        }
        return groupDeliveryFee / participants;
    }
    
    // Savings Calculation
    public double calculateSavings(double individualDeliveryFee, double groupDeliveryFee, int participants) {
        if (participants <= 0) {
            throw new IllegalArgumentException("Number of participants must be greater than 0.");
        }
    
        double totalIndividualDeliveryFee = individualDeliveryFee * participants;
    
        double savings = totalIndividualDeliveryFee - groupDeliveryFee;
    
        return savings;
    }
    
    public SavingsSummary getSavingsDetails(String cartId, double individualDeliveryFee, double groupDeliveryFee) {
        Cart cart = null;
        for (Cart c : carts) {
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
    
        return new SavingsSummary(totalSavings, individualSavings, groupDeliveryFee, numberOfParticipants);
    }
    
    // Product Management
    public List<Product> fetchProductsFromApi(String query) {
            return null; }

    public void updateProductPrices() { }

    // Utilities
    public List<Product> parseApiResponse(String response) {
            return null; }

    // Validation
    public boolean validateCartContents(String cartId) {
            return false; }

    // Error Handling
    public void handleApiError(int errorCode, int retryCount) { }
    
}
