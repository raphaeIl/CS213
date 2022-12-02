package edu.rutgers.rupizzeria.ui.cart;

public class CartItem {

    private int cartItemImageResId;

    private String cartItemName;
    private String cartItemDescription;
    private String cartItemPrice;

    public CartItem(int cartItemImageResId, String cartItemName, String cartItemDescription, String cartItemPrice) {
        this.cartItemImageResId = cartItemImageResId;
        this.cartItemName = cartItemName;
        this.cartItemDescription = cartItemDescription;
        this.cartItemPrice = cartItemPrice;
    }

    public int getCartItemImageResId() {
        return cartItemImageResId;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public String getCartItemDescription() {
        return cartItemDescription;
    }

    public String getCartItemPrice() {
        return cartItemPrice;
    }
}
