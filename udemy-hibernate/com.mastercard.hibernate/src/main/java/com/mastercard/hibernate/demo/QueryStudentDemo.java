package com.mastercard.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mastercard.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			//display the students
			displayStudents(theStudents);
			
			//query students: lastName='Doe'
			theStudents = session.createQuery("from Students s where s.lastName='Doe'").list();
			
			//display the students
			System.out.println("\n\nStudent who have last name of Doe");
			displayStudents(theStudents);
			
			//query students: lastName='Doe' OR firstName='Daffy'
			theStudents = session.createQuery("from Student s where"
					+ "s.lastName='Doe' OR s.firstName='Daffy'").list();
			
			System.out.println("\n\nStudent who have last name of Doe or first name of Daffy");
			displayStudents(theStudents);
			
			
			//query students where email LIKE '%wipro.com'
			theStudents = session.createQuery("from Student s where s.email"
					+ "LIKE '%wipro.com'").list();
			
			System.out.println("\n\nStudent who email ends with wipro.com");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
