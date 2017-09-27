package com.mastercard.hibernate.demo4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mastercard.hibernate.demo.entity4.Instructor;
import com.mastercard.hibernate.demo.entity4.InstructorDetail;
import com.mastercard.hibernate.demo.entity4.Course;

public class FetchJoinDemo {

	public static void main(String[] args) {

		//create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			//get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Instructor: " + tempInstructor);
			
			//get courses for the instructor
			System.out.println("Courses: " + tempInstructor.getCourses());
			
			//commit transaction
			session.getTransaction().commit();	

			//close the session
			session.close();
			
			System.out.println("Wipro: The session is now closed!");
			
			//get courses for the instructor
			System.out.println("Wipro: Courses: " + tempInstructor.getCourses());
			
			System.out.println("Done!");
		}
		finally {
			//add clean up code
			session.close();
			
			factory.close();
		}
	}
}
