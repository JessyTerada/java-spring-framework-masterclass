package com.mastercard.hibernate.demo2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mastercard.hibernate.demo.entity.Student;
import com.mastercard.hibernate.demo.entity2.Instructor;
import com.mastercard.hibernate.demo.entity2.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		//create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			/*Instructor tempIntructor = 
					new Instructor("Chad", "Darby", "darby@wipro.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.wipro.com/youtube",
							"Udemy code!");
			*/
			
			Instructor tempIntructor = 
					new Instructor("Madhu", "Patel", "madhu@wipro.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.youtube.com/",
							"Guitar");
			
			//associate the objects
			tempIntructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save the instructor
			//Note: this will also save the detauls object becausa od cascadeType.ALL
			System.out.println("Saving instructor: " + tempIntructor);
			session.save(tempIntructor);
			
			//commit transaction
			session.getTransaction().commit();	
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}
