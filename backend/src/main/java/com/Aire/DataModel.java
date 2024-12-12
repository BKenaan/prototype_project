package com.Aire;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class DataModel {

    public static class Cart {
        private String cartId;
        private String hostId;
        private List<String> participants; 
        private List<Item> items;
        private String status; 
        private double groupDeliveryFee;
        private Date deadline;

    public  Cart(String cartId, String hostId, List<String> participants, List<Item> items, String status, double groupDeliveryFee, Date deadline) {
        this.cartId = cartId;
        this.hostId = hostId;
        this.participants = participants;
        this.items = items;
        this.status = status;
        this.groupDeliveryFee = groupDeliveryFee;
        this.deadline = deadline;
    }

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

    public void addItem(Item item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public boolean removeItem(String itemId, String userId) {
        if (this.items == null) {
            return false; 
        }

        for (Item item : this.items) {
            if (item.getProductId().equals(itemId) && item.getUserId().equals(userId)) {
                this.items.remove(item);
                return true; 
            }
        }
        return false; 
    }

    public List<Item> viewItems() {
        return this.items;
    }
}



public static class Item {
    private String productId;
    private String name;
    private String userId;
    private int quantity;
    private double price;

    public Item(String productId, String name, String userId, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
    }

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

public static class Product {
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

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

public static class SavingsSummary {
    private double totalSavings;            
    private double individualSavings;       
    private double totalDeliveryFee;        
    private int numberOfParticipants;       

    public SavingsSummary(double totalSavings, double individualSavings, double totalDeliveryFee, int numberOfParticipants) {
        this.totalSavings = totalSavings;
        this.individualSavings = individualSavings;
        this.totalDeliveryFee = totalDeliveryFee;
        this.numberOfParticipants = numberOfParticipants;
    }

    public double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(double totalSavings) {
        this.totalSavings = totalSavings;
    }

    public double getIndividualSavings() {
        return individualSavings;
    }

    public void setIndividualSavings(double individualSavings) {
        this.individualSavings = individualSavings;
    }

    public double getTotalDeliveryFee() {
        return totalDeliveryFee;
    }

    public void setTotalDeliveryFee(double totalDeliveryFee) {
        this.totalDeliveryFee = totalDeliveryFee;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
}


public static class Student {
    private String studentId;
    private String password;

    public Student(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

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
