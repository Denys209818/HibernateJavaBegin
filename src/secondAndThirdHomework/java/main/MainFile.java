package main;


import dbcontext.MainDbContext;
import entities.CategoryEntity;
import entities.ProductEntity;
import org.hibernate.Session;
import services.CategoryService;
import services.ProductService;

import java.util.Scanner;

public class MainFile {
    public static Session session = MainDbContext.getSessionFactory().openSession();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String categoryName;

        System.out.print("Ведіть назву категорії: ");
        categoryName = scanner.nextLine();

        CategoryEntity category = new CategoryEntity();
        category.setName(categoryName);

        CategoryService.InsertInto(category);
        System.out.println("Оберіть ідентифікатор категорії, яку необхідно редагувати: ");
        CategoryService.ShowInConsole();
        if(!scanner.hasNextInt())
        {
            System.out.println("Дані введено не коректно!");
            return;
        }
        int editDeleteCatId = Integer.parseInt(scanner.nextLine());
        System.out.println("Ведіть назву нової категорії: ");
        categoryName = scanner.nextLine();

        CategoryEntity newEntity = new CategoryEntity();
        newEntity.setName(categoryName);
        CategoryService.Update(editDeleteCatId, newEntity);



        String productName, productDescription;
        int productPrice;

        System.out.print("Ведіть назву продукту: ");
        productName = scanner.nextLine();
        System.out.print("Ведіть опис продукту: ");
        productDescription = scanner.nextLine();
        System.out.print("Ведіть ціну продукту: ");
        productPrice = Integer.parseInt(scanner.nextLine());

        ProductEntity product = new ProductEntity();
        product.setTitle(productName);
        product.setDescription(productDescription);
        product.setPrice(productPrice);
        product.setCategory(category);

        ProductService.InsertInto(product);
        System.out.println("Оберіть ідентифіктаор продукту, який необхідно редагувати: ");
        ProductService.ShowInConsole();
        if(!scanner.hasNextInt())
        {
            System.out.println("Дані введено не коректно!");
            return;
        }
        int editDeleteProdId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ведіть назву продукту: ");
        productName = scanner.nextLine();
        System.out.print("Ведіть опис продукту: ");
        productDescription = scanner.nextLine();
        System.out.print("Ведіть ціну продукту: ");

        String tempPrice = scanner.nextLine();
        if(tryParseInt(tempPrice))
        {
            productPrice = Integer.parseInt(tempPrice);
        }
        else
        {
            productPrice = 0;
        }
        ProductEntity newProduct = new  ProductEntity();
        newProduct.setTitle(productName);
        newProduct.setDescription(productDescription);
        newProduct.setPrice(productPrice);

        ProductService.Update(editDeleteProdId, newProduct);

        //Видалення продуктів
        ProductService.Delete(editDeleteProdId);
        CategoryService.Delete(editDeleteCatId);

        session.close();
    }

    static boolean  tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
