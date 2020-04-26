package com.bhadra.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bhadra.config.HibernateConfig;
import com.bhadra.entity.Employee;

public class EmployeeDao {

	public void saveEmp(Employee emp) {
		SessionFactory sf = null;
		Session session = null;
		try {
			sf = HibernateConfig.getSessionFactory();
			session = sf.openSession();
			Transaction tx = session.beginTransaction();

			session.save(emp);

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			if (sf != null) {
				sf.close();
			}
			HibernateConfig.destroy();
		}
	}
}
