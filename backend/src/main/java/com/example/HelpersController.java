package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public String createSharedCart(@RequestBody CreateCartRequest request) {
        return helpers.createSharedCart(request.getHostId(), request.getParticipants(), request.getDeadline());
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
