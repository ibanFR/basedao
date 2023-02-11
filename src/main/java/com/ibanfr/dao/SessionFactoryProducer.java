package com.ibanfr.dao;

import com.ibanfr.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Map;

/**
 *
 * Uses hibernate native bootstrapping mechanism
 *
 * <a href="https://docs.jboss.org/hibernate/orm/5.4/topical/html_single/bootstrap/NativeBootstrapping.html">Native bootstrapping</a>
 */
public class SessionFactoryProducer {

    //public SessionFactory produceSessionFactory(){
    //    StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
    //            .configure( "org/hibernate/example/MyCfg.xml" )
    //            .build();
    //
    //    Metadata metadata = new MetadataSources(standardRegistry )
    //            .addAnnotatedClass( MyEntity.class )
    //            .addAnnotatedClassName( "org.hibernate.example.Customer" )
    //            .addResource( "org/hibernate/example/Order.hbm.xml" )
    //            .addResource( "org/hibernate/example/Product.orm.xml" )
    //            .getMetadataBuilder()
    //            .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
    //            .build();
    //
    //    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
    //                                            .applyBeanManager( getBeanManagerFromSomewhere() )
    //                                            .build();
    //}

    SessionFactory produceH2SessionFactory(){
        Map settings = Map.of("hibernate.connection.url", "jdbc:h2:mem:testdb",
                              "hibernate.connection.driver_class", "org.h2.Driver",
                              "hibernate.hbm2ddl.auto", "update",
                              "hibernate.show_sql", "false");

        return getSessionFactory(settings, User.class);
    }

    SessionFactory getSessionFactory(Map settings, Class<?>... annotatedClasses){

        //Build ServiceRegistry
        ServiceRegistry serviceRegistry = buildServiceRegistry(settings);
        //Create the Metadata object using the specified ServiceRegistry.
        Metadata metadata = buildMetadata(serviceRegistry, annotatedClasses);
        //Build the SessionFactory
        SessionFactory sessionFactory = buildSessionFactory(metadata);

        return sessionFactory;
    }

    /**
     *
     * @param settings
     * @return
     */
    ServiceRegistry buildServiceRegistry(Map settings) {

        //Build ServiceRegistry
        ServiceRegistry serviceRegistry;
        serviceRegistry = new StandardServiceRegistryBuilder()/* .configure() */
                                                              .applySettings(settings)
                                                              .build();
        return serviceRegistry;
    }

    /**
     * Create a Metadata object containing the parsed representations of the application domain model and its mapping
     * to the database.
     * <p>
     * See
     * <a href="https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#bootstrap-native-metadata">Hibernate User Guide#Building the Metadata</a>
     *
     * @param serviceRegistry
     * @return
     */
    Metadata buildMetadata(ServiceRegistry serviceRegistry, Class<?>... annotatedClasses){
        Metadata metadata;
        metadata = new MetadataSources(serviceRegistry).addAnnotatedClasses(annotatedClasses)
                                                       .getMetadataBuilder()
                                                       .build();
        return metadata;
    }

    /**
     * Build the SessionFactory instance.
     * <p>
     * See
     * <a href="https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#bootstrap-native-SessionFactory">Hibernate User Guide#Building the SessionFactory</a>
     *
     * @param metadata
     * @return
     */
    SessionFactory buildSessionFactory(Metadata metadata){
        SessionFactory factory = metadata.getSessionFactoryBuilder()
                                         .build();
        return factory;
    }
}
