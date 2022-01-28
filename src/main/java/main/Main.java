package main;

import dbcontext.DbContextJava;
import entities.Category;
import entities.Company;
import entities.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Session session = DbContextJava.getFactory().openSession();

        Scanner scanner = new Scanner(System.in);

        session.beginTransaction();

        String companyName, productName, categoryName;
        List<Product> products = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        System.out.print("Ведіть назву компанії: ");
        companyName = scanner.nextLine();
        Company company = new Company();
        company.setName(companyName);
        session.save(company);

        for(int i = 0; i< 2; i++) {
            System.out.print("Ведіть назву продукта " + (i+1) + ": ");
            productName = scanner.nextLine();
            Product prod = new Product();
            prod.setName(productName);
            prod.setCompany(company);
            products.add(prod);
        }

        for(int i = 0; i< 2; i++) {
            System.out.print("Ведіть назву категорії " + (i+1)+": ");
            categoryName = scanner.nextLine();

            Category category = new Category();
            category.setTitle(categoryName);
            categories.add(category);
        }

        products.get(0).setCategories(categories);
        products.get(1).setCategories(categories);

        categories.get(0).setProducts(products);
        categories.get(1).setProducts(products);

        session.save(products.get(0));
        session.save(products.get(1));
        session.save(categories.get(0));
        session.save(categories.get(1));

        session.getTransaction().commit();
        session.close();
    }

}
