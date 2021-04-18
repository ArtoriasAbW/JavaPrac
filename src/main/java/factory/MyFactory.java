package factory;

import org.hibernate.SessionFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MyFactory {
    public static SessionFactory factory;

    private MyFactory() {

    }

    // only one thread
    public static synchronized SessionFactory getFactory() {
        if (factory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        return factory;
    }
}
