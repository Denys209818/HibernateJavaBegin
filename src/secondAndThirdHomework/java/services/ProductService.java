package services;

import dbcontext.MainDbContext;
import entities.CategoryEntity;
import entities.ProductEntity;
import main.MainFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductService {

    public static void InsertInto(ProductEntity product)
    {
        MainFile.session.beginTransaction();
        MainFile.session.save(product);
        MainFile.session.getTransaction().commit();
    }

    public static void Update(int id, ProductEntity product)
    {
        MainFile.session.beginTransaction();

        List<ProductEntity> products = MainFile.session.createQuery("FROM ProductEntity WHERE Id = id").list();

        String newTitle = product.getTitle().length() == 0 ? products.get(0).getTitle() : product.getTitle();
        String newDesc = product.getDescription().length() ==  0 ? products.get(0).getDescription() : product.getDescription();
        int newPrice = product.getPrice() == 0 ? products.get(0).getPrice() : product.getPrice();

        Query query = MainFile.session.createQuery("UPDATE ProductEntity SET Title = :productTitle" +
                ", Description = :productDescription"
                +", Price = :productPrice" +" WHERE Id = :id" );

        query.setParameter("productTitle", newTitle);
        query.setParameter("productDescription", newDesc);
        query.setParameter("productPrice", newPrice);
        query.setParameter("id", id);
        query.executeUpdate();
        MainFile.session.getTransaction().commit();
    }

    public static List<ProductEntity> Select()
    {
        Query query = MainFile.session.createQuery("FROM ProductEntity");
        List<ProductEntity> products = query.list();
        return products;
    }

    public static void Delete(int id)
    {
        MainFile.session.beginTransaction();
        Query query = MainFile.session.createQuery("DELETE ProductEntity where Id =: id");
        query.setParameter("id", id);
        query.executeUpdate();
        MainFile.session.getTransaction().commit();
    }

    public static void ShowInConsole()
    {
        List<ProductEntity> products = Select();
        for (ProductEntity product : products)
        {
            System.out.println("--------------------");
            System.out.println("Id: " + product.getId());
            System.out.println("Name: " + product.getTitle());
            System.out.println("--------------------");
        }
    }
}
