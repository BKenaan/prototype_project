package com;
import java.util.*;

public class datamodel {

    // Cart Model
public class Cart {
    private String cartId;
    private String hostId;
    private List<String> participants; // List of student IDs
    private List<Item> items;
    private String status; // e.g., "Open", "Finalized"
    private double groupDeliveryFee;
    private Date deadline;

    // Constructor
    public Cart(String cartId, String hostId, List<String> participants, List<Item> items, String status, double groupDeliveryFee, Date deadline) {
        this.cartId = cartId;
        this.hostId = hostId;
        this.participants = participants;
        this.items = items;
        this.status = status;
        this.groupDeliveryFee = groupDeliveryFee;
        this.deadline = deadline;
    }

    // Getters and Setters
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getGroupDeliveryFee() {
        return groupDeliveryFee;
    }

    public void setGroupDeliveryFee(double groupDeliveryFee) {
        this.groupDeliveryFee = groupDeliveryFee;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}

// Item Model
public class Item {
    private String itemId;
    private String productId;
    private String userId;
    private int quantity;
    private double price;

    // Constructor
    public Item(String itemId, String productId, String userId, int quantity, double price) {
        this.itemId = itemId;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

// Product Model
public class Product {
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    // Constructor
    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

// SavingsSummary Model
public class SavingsSummary {
    private double totalSavings;
    private Map<String, Double> userSavings;

    // Constructor
    public SavingsSummary(double totalSavings, Map<String, Double> userSavings) {
        this.totalSavings = totalSavings;
        this.userSavings = userSavings;
    }

    // Getters and Setters
    public double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(double totalSavings) {
        this.totalSavings = totalSavings;
    }

    public Map<String, Double> getUserSavings() {
        return userSavings;
    }

    public void setUserSavings(Map<String, Double> userSavings) {
        this.userSavings = userSavings;
    }
}

// Student Model
public class Student {
    private String studentId;
    private String password;

    // Constructor
    public Student(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
}