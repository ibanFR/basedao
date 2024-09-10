package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Employee;
import jakarta.enterprise.inject.Produces;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
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

    @Produces
    SessionFactory produceH2SessionFactory(){
        Map<String, Object> settings = Map.of("hibernate.connection.url", "jdbc:h2:mem:testdb",
                              "hibernate.connection.driver_class", "org.h2.Driver",
                              "hibernate.hbm2ddl.auto", "update",
                              "hibernate.show_sql", "false",
                              "hibernate.current_session_context_class","thread");

        return getSessionFactory(settings, Employee.class);
    }

    protected SessionFactory produceSessionFactory(Map<String, Object> settings, Class<?>... annotatedClasses){
        return getSessionFactory(settings,annotatedClasses);
    }

    SessionFactory getSessionFactory(Map<String, Object> settings, Class<?>... annotatedClasses){

        //Build ServiceRegistry
        ServiceRegistry serviceRegistry = buildServiceRegistry(settings);
        //Create the Metadata object using the specified ServiceRegistry.
        Metadata metadata = buildMetadata(serviceRegistry, annotatedClasses);
        //Build the SessionFactory
        return buildSessionFactory(metadata);
    }

    /**
     *
     * @param settings
     * @return
     */
    ServiceRegistry buildServiceRegistry(Map<String, Object> settings) {

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
     * <a href="https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#bootstrap-native-metadata">Hibernate Employee Guide#Building the Metadata</a>
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
     * <a href="https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#bootstrap-native-SessionFactory">Hibernate Employee Guide#Building the SessionFactory</a>
     *
     * @param metadata
     * @return
     */
    SessionFactory buildSessionFactory(Metadata metadata){
        SessionFactory factory;
        factory = metadata.getSessionFactoryBuilder()
                          .build();
        return factory;
    }
}
