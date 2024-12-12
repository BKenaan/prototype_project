package com.Aire;

public class AddItemRequest {
    private String cartId;
    private String userId;
    private DataModel.Item item;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DataModel.Item getItem() {
        return item;
    }

    public void setItem(DataModel.Item item) {
        this.item = item;
    }
    
}
