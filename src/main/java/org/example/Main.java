package org.example;

import org.example.models.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String productName ="";
        String productDetails = "";
        float productPrice;
        Scanner scanner = new Scanner(System.in);
        int isOn = 0;
        String buffer = "";

        while (isOn < 1){
            System.out.println("\n\nWhat would you like to do? \n\n1) Create new product. \n2) Update existing product. " +
                    "\n3) Delete product. \n4) Get list of all products. \n5) End.");
        try{

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\n\n");
                    System.out.println("Please enter product name:");
                    productName = scanner.nextLine();

                    System.out.println("Please enter product details:");
                    productDetails = scanner.nextLine();

                    System.out.println("Please enter product price:");
                    productPrice = scanner.nextFloat();
                    System.out.println("Creating product...\n\n");

                    Product.createProduct(productName, productDetails, productPrice);
                    break;
                case 2:
                    System.out.println("\n\n");
                    System.out.println("Which product would you like to update?");
                    String updateTarget = scanner.nextLine();
                    Product.updateByName(updateTarget);
                    System.out.println("Updating product...\n\n");
                    break;
                case 3:
                    System.out.println("\n\n");
                    System.out.println("Which product would you like to delete?");
                    String deleteTarget = scanner.nextLine();
                    System.out.println("Deleting product...\n\n");
                    Product.deleteByName(deleteTarget);
                    break;
                case 4:
                    System.out.println("\n\n");
                    List<Product> list = Product.findAll();
                    for (Product p : list){
                        System.out.println( p.getProductName() + ", " + p.getProductDetails() + ", " + p.getProductPrice());
                    }
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Thank you, please come again.");
                    isOn ++;
                    break;
            }
        } catch (InputMismatchException e){
            e.printStackTrace();
            System.exit(0);
        }
        }
    }
}