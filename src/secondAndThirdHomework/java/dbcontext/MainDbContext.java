package dbcontext;

import entities.CategoryEntity;
import entities.ProductEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainDbContext {
    private MainDbContext() {}
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory()
    {
        if(sessionFactory == null)
        {
            Configuration configuration = new Configuration().configure();

            configuration.addAnnotatedClass(ProductEntity.class);
            configuration.addAnnotatedClass(CategoryEntity.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            return sessionFactory;
        }
        return sessionFactory;
    }
}
