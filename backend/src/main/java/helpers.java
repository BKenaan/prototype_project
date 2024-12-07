import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Helpers {
    private List<DataModel.Cart> carts = new ArrayList<>();

    public String createSharedCart(String hostId, List<String> participants, Date deadline) {
        String cartId = "cart" + (carts.size() + 1);
        DataModel.Cart cart = new DataModel.Cart(cartId, hostId, participants, new ArrayList<>(), "Open", 5.0, deadline);
        carts.add(cart);
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
            cart.getItems().add(item);
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

      // Initialize a map to store individual costs
      Map<String, Double> individualCosts = new HashMap<>();

      for (DataModel.Item item : cart.getItems()) {
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
        
            // Update the cart status to "Finalized"
            cart.setStatus("Finalized");
        
            // Perform any additional actions needed to finalize the order
            // For example, notify participants, process payments, etc.
            notifyParticipants(cart);
            processPayments(cart);
        
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
        
            // Check if the current time is past the deadline
            if (currentTime.after(cart.getDeadline())) {
                // Finalize the order
                finalizeOrder(cartId);
            }
        }


        
        private void notifyParticipants(DataModel.Cart cart) {
            // Implementation for notifying participants
            for (String participant : cart.getParticipants()) {
                System.out.println("Notifying participant: " + participant);
            }
        }
        
        private void processPayments(DataModel.Cart cart) {
            // Implementation for processing payments
            System.out.println("Processing payments for cart: " + cart.getCartId());
        }

    
    
    // Product Management
    public List<DataModel.Product> fetchProductsFromApi(String query) {
            return null; }

    public void updateProductPrices() { }

    // Utilities
    public List<DataModel.Product> parseApiResponse(String response) {
            return null; }

    // Validation
    public boolean validateCartContents(String cartId) {
            return false; }

    // Error Handling
    public void handleApiError(int errorCode, int retryCount) { }
    
}
