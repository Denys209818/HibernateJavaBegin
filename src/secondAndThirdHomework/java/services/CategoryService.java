package services;

import dbcontext.MainDbContext;
import entities.CategoryEntity;
import entities.ProductEntity;
import main.Main;
import main.MainFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryService {
    public static void InsertInto(CategoryEntity category)
    {
        MainFile.session.beginTransaction();
        MainFile.session.save(category);
        MainFile.session.getTransaction().commit();
    }

    public static void Update(int id, CategoryEntity category)
    {
        MainFile.session.beginTransaction();

        List<CategoryEntity> categories = MainFile.session.createQuery("FROM CategoryEntity WHERE Id = id").list();

        String newName = (category.getName() == null || category.getName().length() == 0) ?
                categories.get(0).getName() : category.getName();

        Query query = MainFile.session.createQuery("UPDATE CategoryEntity SET Name = :categoryName" +
                " WHERE Id = :categoryId");
        query.setParameter("categoryName", newName);
        query.setParameter("categoryId", id);
        query.executeUpdate();
        MainFile.session.getTransaction().commit();
    }

    public static List<CategoryEntity> Select()
    {
        Query query = MainFile.session.createQuery("FROM CategoryEntity");
        List<CategoryEntity> categories = query.list();
        return categories;
    }

    public static void Delete(int id)
    {
        MainFile.session.beginTransaction();
        Query query = MainFile.session.createQuery("DELETE CategoryEntity where Id =: id");
        query.setParameter("id", id);
        query.executeUpdate();
        MainFile.session.getTransaction().commit();
    }

    public static void ShowInConsole()
    {
        List<CategoryEntity> categories = Select();
        for (CategoryEntity cat : categories)
        {
            System.out.println("--------------------");
            System.out.println("Id: " + cat.getId());
            System.out.println("Name: " + cat.getName());
            System.out.println("--------------------");
        }
    }
}
