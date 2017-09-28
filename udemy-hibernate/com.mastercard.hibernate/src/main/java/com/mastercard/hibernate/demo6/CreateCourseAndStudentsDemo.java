package com.mastercard.hibernate.demo6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mastercard.hibernate.demo.entity6.Instructor;
import com.mastercard.hibernate.demo.entity6.InstructorDetail;
import com.mastercard.hibernate.demo.entity6.Review;
import com.mastercard.hibernate.demo.entity6.Student;
import com.mastercard.hibernate.demo.entity6.Course;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		//create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse= new Course("Pacman - How to Score One Million Points");
			
			//save the course
			System.out.println("\nSaving the course....");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
			//create the students
			Student tempStudent1 = new Student("John", "Doe", "john@wipro.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@wipro.com");
			
			//add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			//save the students
			System.out.println("\nSaving students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Save students: " + tempCourse.getStudents());
			
			//commit transaction
			session.getTransaction().commit();	
			
			System.out.println("Done!");
		}
		finally {
			//add clean up code
			session.close();
			
			factory.close();
		}
	}
}
