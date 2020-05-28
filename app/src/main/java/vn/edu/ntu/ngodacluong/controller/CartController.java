package vn.edu.ntu.ngodacluong.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.ngodacluong.model.Product;

public class CartController extends Application implements ICartController
{
    List<Product> productList = new ArrayList<>();
    List<Product> shoppingCart = new ArrayList<>();

    public CartController()
    {
        productList.add(new Product("Chuối Đà Lạt - Nha Trang", 25000, "Chuối không hạt"));
        productList.add(new Product("Xoài Cam Ranh", 22000, "Xoài hạt lép"));
        productList.add(new Product("Đậu phộng Quảng Nam", 30000, "Đậu phộng hạt bự"));
        productList.add(new Product("Dưa hấu Phú Quốc", 11000, "Không hạt"));
        productList.add(new Product("Nho Ninh Thuận", 56000, "Nho không có hột "));
        productList.add(new Product("Rau muống ", 67000, "Rau muống sông "));
        productList.add(new Product("Bơ Kon Tum", 87000, "Ăn thì ngon thôi rồi"));

    }

    @Override
    public List<Product> getAllProduct() {
        return this.productList;
    }

    @Override
    public boolean addToCart(Product p) {
        if(shoppingCart.contains(p))
            return false;

        shoppingCart.add(p);
        return true;
    }

    @Override
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }

}
