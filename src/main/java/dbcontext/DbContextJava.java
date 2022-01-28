package dbcontext;

import entities.Category;
import entities.Company;
import entities.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DbContextJava {
    private DbContextJava() {}
    private static SessionFactory sessionFactory;

    public static SessionFactory getFactory()
    {
        if(sessionFactory == null)
        {
           Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Category.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());
            return sessionFactory;
        }
        return sessionFactory;
    }
}
