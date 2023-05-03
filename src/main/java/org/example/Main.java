package org.example;

import org.example.models.Product;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String productName ="";
        String productDetails = "";
        float productPrice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter product name:");
        productName = scanner.nextLine();

        System.out.println("Please enter product details:");
        productDetails = scanner.nextLine();

        System.out.println("Please enter product price:");
        productPrice = scanner.nextFloat();

        Product.createProduct(productName, productDetails, productPrice);


        List<Product> list = Product.findAll();
        for (Product p : list){
            System.out.println("\n\n" + p.getProductName() + ", " + p.getProductDetails() + ", " + p.getProductPrice());
        }

        Product.updateByName("Apple");

        Product.deleteByName(productName);

        List<Product> list2 = Product.findAll();
        for (Product p : list2){
            System.out.println("\n\n" + p.getProductName() + ", " + p.getProductDetails() + ", " + p.getProductPrice());
        }


    }


}