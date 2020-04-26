package com.bhadra.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.bhadra.entity.Employee;

public class HibernateConfig {

	private static StandardServiceRegistry registry = null;

	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;

		try {
			// Create registry builder
			StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

			// Hibernate settings equivalent to hibernate.cfg.xml's properties
			Map<String, String> settings = new HashMap<String, String>();
			settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
			settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hib_demo");
			settings.put(Environment.USER, "root");
			settings.put(Environment.PASS, "root");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

			// Apply settings
			registryBuilder.applySettings(settings);

			// Create registry
			registry = registryBuilder.build();

			// Create MetadataSources
			MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Employee.class);

			// Create Metadata
			Metadata metadata = sources.getMetadataBuilder().build();

			// Create SessionFactory
			sessionFactory = metadata.getSessionFactoryBuilder().build();
		} catch (Exception e) {
			e.printStackTrace();
			if (registry != null)
				StandardServiceRegistryBuilder.destroy(registry);
		}

		return sessionFactory;
	}

	public static void destroy() {
		if (registry != null)
			StandardServiceRegistryBuilder.destroy(registry);
	}
}
