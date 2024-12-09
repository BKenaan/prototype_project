import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        
        Helpers helpers = new Helpers();

        // Step 1: Test the API integration
        System.out.println("Fetching products from the Open Food Facts API...");
        List<DataModel.Product> fetchedProducts = helpers.fetchProductsFromApi("milk");
        if (fetchedProducts.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Fetched Products:");
            for (DataModel.Product product : fetchedProducts) {
                System.out.println("Product ID: " + product.getProductId() +
                                   ", Name: " + product.getName() +
                                   ", Price: $" + product.getPrice());
            }
        }

        // Step 2: Update product prices (from API)
        System.out.println("\nUpdating product prices...");
        helpers.updateProductPrices();
        System.out.println("Product prices updated successfully.\n");

        // Step 3: Create a new shared cart
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        Date deadline = new Date(System.currentTimeMillis() + 3600 * 1000); // 1 hour from now
        String cartId = helpers.createSharedCart(hostId, participants, deadline);
        System.out.println("Cart created with ID: " + cartId);

        // Step 4: Add items to the cart using fetched products
        if (!fetchedProducts.isEmpty()) {
            DataModel.Product milkProduct = fetchedProducts.get(0); // Use the first fetched product for testing
            DataModel.Item milkItem = new DataModel.Item("item1", milkProduct.getName(), "user1", 2, milkProduct.getPrice());
            helpers.addItemToCart(cartId, "user1", milkItem);
            System.out.println("Added Milk to the cart from API response.");
        }

        // Add hardcoded items to the cart
        DataModel.Item bread = new DataModel.Item("Item2", "Bread", "user2", 1, 0.8); // 1 unit of Bread at $0.8 by user2
        DataModel.Item eggs = new DataModel.Item("item3", "Eggs", "user3", 12, 0.1); // 12 units of Eggs at $0.1 each by user3
        helpers.addItemToCart(cartId, "user2", bread);
        helpers.addItemToCart(cartId, "user3", eggs);

        // Step 5: View cart details
        System.out.println("\nCart Details:");
        List<DataModel.Item> cartItems = helpers.viewCartDetails(cartId);
        for (DataModel.Item item : cartItems) {
            System.out.println(item.getProductId() + " - " + item.getQuantity() + " units @ $" + item.getPrice() + " each");
        }

        // Step 6: Validate cart contents using API
        System.out.println("\nValidating cart contents against API...");
        boolean isCartValid = helpers.validateCartContents(cartId);
        System.out.println("Cart validation status: " + (isCartValid ? "Valid" : "Invalid"));

        // Step 7: Calculate the total cost
        double totalCost = helpers.calculateTotalCost(cartId);
        System.out.println("\nTotal Cost of Cart: $" + totalCost);

        // Step 8: Calculate individual costs
        Map<String, Double> individualCosts = helpers.calculateIndividualCosts(cartId);
        System.out.println("Individual Costs:");
        for (Map.Entry<String, Double> entry : individualCosts.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }

        // Step 9: Calculate delivery fee split
        double groupDeliveryFee = 5.0;
        double deliveryFeeSplit = helpers.calculateDeliveryFeeSplit(groupDeliveryFee, participants.size());
        System.out.println("\nDelivery Fee Split per User: $" + deliveryFeeSplit);

        // Step 10: Get savings details
        double individualDeliveryFee = 3.0;
        DataModel.SavingsSummary savings = helpers.getSavingsDetails(cartId, individualDeliveryFee, groupDeliveryFee);
        System.out.println("\nSavings Summary:");
        System.out.println("Total Savings: $" + savings.getTotalSavings());
        System.out.println("Individual Savings: $" + savings.getIndividualSavings());
        System.out.println("Group Delivery Fee: $" + savings.getTotalDeliveryFee());
        System.out.println("Number of Participants: " + savings.getNumberOfParticipants());

        // Step 11: Finalize the cart manually
        helpers.finalizeOrder(cartId);
        System.out.println("\nCart finalized. Current Status: " + helpers.getOrderStatus(cartId));

        // Step 12: Check auto-finalization (simulate deadline passing)
        Date currentTime = new Date(System.currentTimeMillis() + 2 * 3600 * 1000); // 2 hours later
        helpers.checkAndAutoFinalizeOrder(cartId, currentTime);
    }
}