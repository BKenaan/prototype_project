package com.Aire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/helpers") 
@CrossOrigin(origins = "http://localhost:4200")

public class HelpersController {
    private final Helpers helpers;

    @Autowired
    public HelpersController(Helpers helpers) {
        this.helpers = helpers;
    }

   @PostMapping("/create-cart")
public ResponseEntity<Map<String, String>> createSharedCart(@RequestBody CreateCartRequest request) {
    String cartId = helpers.createSharedCart(request.getHostId(), request.getParticipants(), request.getDeadline());
    Map<String, String> response = new HashMap<>();
    response.put("cartId", cartId);
    return ResponseEntity.ok(response);
}


    @PostMapping("/add-item")
    public void addItemToCart(@RequestBody AddItemRequest request) {
        helpers.addItemToCart(request.getCartId(), request.getUserId(), request.getItem());
    }

    @GetMapping("/view-cart/{cartId}")
    public List<DataModel.Item> viewCartDetails(@PathVariable String cartId) {
        return helpers.viewCartDetails(cartId);
    }

    @GetMapping("/calculate-total/{cartId}")
    public double calculateTotalCost(@PathVariable String cartId) {
        return helpers.calculateTotalCost(cartId);
    }

    @GetMapping("/individual-costs/{cartId}")
    public Map<String, Double> calculateIndividualCosts(@PathVariable String cartId) {
        return helpers.calculateIndividualCosts(cartId);
    }

    @GetMapping("/delivery-fee-split")
    public double calculateDeliveryFeeSplit(@RequestParam double groupDeliveryFee, @RequestParam int participants) {
        return helpers.calculateDeliveryFeeSplit(groupDeliveryFee, participants);
    }

    @GetMapping("/calculate-savings")
    public double calculateSavings(
            @RequestParam double individualDeliveryFee,
            @RequestParam double groupDeliveryFee,
            @RequestParam int participants) {
        return helpers.calculateSavings(individualDeliveryFee, groupDeliveryFee, participants);
    }

    @GetMapping("/savings-details/{cartId}")
    public DataModel.SavingsSummary getSavingsDetails(
            @PathVariable String cartId,
            @RequestParam double individualDeliveryFee,
            @RequestParam double groupDeliveryFee) {
        return helpers.getSavingsDetails(cartId, individualDeliveryFee, groupDeliveryFee);
    }

    @GetMapping("/recent-activities")
public List<Map<String, String>> getRecentActivities() {
    List<Map<String, String>> activities = new ArrayList<>();
    Map<String, String> activity1 = new HashMap<>();
    activity1.put("description", "Added a new item to the cart");
    activity1.put("date", "2024-12-11");
    activity1.put("status", "Completed");
    activities.add(activity1);

    Map<String, String> activity2 = new HashMap<>();
    activity2.put("description", "Removed an item from the cart");
    activity2.put("date", "2024-12-10");
    activity2.put("status", "Pending");
    activities.add(activity2);

    return activities;
}

    @PostMapping("/finalize-order/{cartId}")
    public void finalizeOrder(@PathVariable String cartId) {
        helpers.finalizeOrder(cartId);
    }

    @GetMapping("/order-status/{cartId}")
    public String getOrderStatus(@PathVariable String cartId) {
        return helpers.getOrderStatus(cartId);
    }

    @PostMapping("/auto-finalize/{cartId}")
    public void checkAndAutoFinalizeOrder(@PathVariable String cartId, @RequestParam Date currentTime) {
        helpers.checkAndAutoFinalizeOrder(cartId, currentTime);
    }

    @GetMapping("/fetch-products")
    public List<DataModel.Product> fetchProductsFromApi(@RequestParam String query) {
        return helpers.fetchProductsFromApi(query);
    }

    @GetMapping("/validate-cart/{cartId}")
    public boolean validateCartContents(@PathVariable String cartId) {
        return helpers.validateCartContents(cartId);
    }

    @PostMapping("/parse-api-response")
    public List<DataModel.Product> parseApiResponse(@RequestBody String response) {
        return helpers.parseApiResponse(response);
    }
 
}
