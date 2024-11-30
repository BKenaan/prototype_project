

import java.util.*;
import DataModel.*;


public class Main {
    public static void main(String[] args) {
        
        Helpers helpers = new Helpers();

        // Create a new shared cart
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        Date deadline = new Date(System.currentTimeMillis() + 3600 * 1000); // 1 hour from now
        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        System.out.println("Cart created with ID: " + cartId);

        // Add hardcoded items to the cart
        Item milk = new Item("item1", "Milk", "user1", 2, 1.5); // 2 units of Milk at $1.5 each by user1
        Item bread = new Item("item2", "Bread", "user2", 1, 0.8); // 1 unit of Bread at $0.8 by user2
        Item eggs = new Item("item3", "Eggs", "user3", 12, 0.1); // 12 units of Eggs at $0.1 each by user3


        helpers.addItemToCart(cartId, "user1", milk);
        helpers.addItemToCart(cartId, "user2", bread);
        helpers.addItemToCart(cartId, "user3", eggs);

        // View cart details
        System.out.println("Cart Details:");
        List<Item> cartItems = helpers.viewCartDetails(cartId);
        for (Item item : cartItems) {
            System.out.println(item.getProductId() + " - " + item.getQuantity() + " units @ $" + item.getPrice() + " each");
        }

        // Calculate the total cost
        double totalCost = helpers.calculateTotalCost(cartId);
        System.out.println("Total Cost of Cart: $" + totalCost);

        // Calculate individual costs
        Map<String, Double> individualCosts = helpers.calculateIndividualCosts(cartId);
        System.out.println("Individual Costs:");
        for (Map.Entry<String, Double> entry : individualCosts.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }

        // Calculate delivery fee split
        double groupDeliveryFee = 5.0;
        double deliveryFeeSplit = helpers.calculateDeliveryFeeSplit(groupDeliveryFee, participants.size());
        System.out.println("Delivery Fee Split per User: $" + deliveryFeeSplit);

        // Get savings details
        double individualDeliveryFee = 3.0;
        SavingsSummary savings = helpers.getSavingsDetails(cartId, individualDeliveryFee, groupDeliveryFee);
        System.out.println("Savings Summary:");
        System.out.println("Total Savings: $" + savings.getTotalSavings());
        System.out.println("Individual Savings: $" + savings.getIndividualSavings());
        System.out.println("Group Delivery Fee: $" + savings.getTotalDeliveryFee());
        System.out.println("Number of Participants: " + savings.getNumberOfParticipants());

        // Finalize the cart manually
        helpers.finalizeOrder(cartId);
        System.out.println("Cart finalized. Current Status: " + helpers.getOrderStatus(cartId));

        // Check auto-finalization (simulate deadline passing)
        Date currentTime = new Date(System.currentTimeMillis() + 2 * 3600 * 1000); // 2 hours later
        helpers.checkAndAutoFinalizeOrder(cartId, currentTime);
    }
}

