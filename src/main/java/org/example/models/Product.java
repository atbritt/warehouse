package org.example.models;

import org.example.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {

    private String productName = "";
    private int productId;
    private float productPrice;
    private String productDetails = "";

    public Product(String productName, int productId, float productPrice, String productDetails){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDetails = productDetails;
    }

    public Product() {

    }

    public static void createProduct(String productName, String productDetails, float productPrice){
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection();



            //Statement stmt = connection.createStatement();
      ) {
                //String sql1 = "CREATE TABLE PRODUCT ( product_name varchar(255) not NULL, product_details VARCHAR(255), product_price FLOAT, PRIMARY KEY ( product_name ))";

                //stmt.executeUpdate(sql1);

            String sql = "insert into PRODUCT (product_name, product_details, product_price) values (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productDetails);
            preparedStatement.setFloat(3, productPrice);

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException("product not added to db");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Product> findAll(){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Product> products = new ArrayList<>();

            String sql = "select * from PRODUCT";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                products.add(convertSqlInfoIntoProduct(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteByName(String name){
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "delete from PRODUCT where product_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException("product not added to db");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateByName(String name){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the new product details?");
        String newDetails = scanner.nextLine();
        System.out.println("What is the new product price?");
        Float newPrice = scanner.nextFloat();

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "UPDATE PRODUCT set  product_details = ?, product_price = ? where product_name = ?" ;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newDetails);
            preparedStatement.setFloat(2, newPrice);
            preparedStatement.setString(3, name);


            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException("product not added to db");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    private static Product convertSqlInfoIntoProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setProductName(resultSet.getString("product_name"));
        product.setProductDetails(resultSet.getString("product_details"));
        product.setProductPrice(resultSet.getFloat("product_price"));

        return product;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getProductDetails() {
        return productDetails;
    }
}
