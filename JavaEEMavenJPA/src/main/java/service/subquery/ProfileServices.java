package service.subquery;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.subquery.Profile;

public class ProfileServices {

	// Create
	public static void create() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
	}
	
	// Insert
	public static void insert() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Profile p1 = new Profile("james", "R&D", 7892.0, 988909909);
			Profile p2 = new Profile("david", "R&D", 8280.0, 2098763);
			Profile p3 = new Profile("marry", "R&D", 9980.0, 92873134);
			Profile p4 = new Profile("tim", "R&D", 10540.0, 987424);
			Profile p5 = new Profile("tom", "R&D", 123480.0, 902342342);
			em.persist(p1);
			em.persist(p2);
			em.persist(p3);
			em.persist(p4);
			em.persist(p5);
			transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			em.close();
			emf.close();
		}		
	}
	
	// Query
	public static void queryProfilesWithMoreThanNumericNumbers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        profile0_.id as id1_19_,
			        profile0_.department as departme2_19_,
			        profile0_.name as name3_19_,
			        profile0_.phone_number as phone_nu4_19_,
			        profile0_.salary as salary5_19_ 
			    from
			        profile profile0_ 
			    where
			        (
			            select
			                count(profile0_.id) 
			            from
			                profile profile0_
			        )>5								 
		*/
		Query query = em.createQuery("SELECT p FROM Profile p "
								   + "WHERE (SELECT COUNT(pn) FROM p.phoneNumber pn) > 5 ");
		List<Profile> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("ID: %3d | Name: %8s | Dept: %8s | Salary: %6.6f | PhoneNumber: %11d\n", 
												  s.getId(), s.getName(), s.getDepartment(), s.getSalary(), s.getPhoneNumber()));
		em.close();
		emf.close();	
	}
	
	
	public static void queryProfilesWithSalaryUnderAverage() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        profile0_.id as col_0_0_,
			        profile0_.name as col_1_0_,
			        profile0_.department as col_2_0_,
			        profile0_.salary as col_3_0_ 
			    from
			        profile profile0_ 
			    where
			        profile0_.salary<(
			            select
			                avg(profile1_.salary) 
			            from
			                profile profile1_
			        )			 
		*/
		Query query = em.createQuery("SELECT p.id, p.name, p.department, p.salary "
								   + "FROM Profile p "
								   + "WHERE p.salary < (SELECT AVG(p2.salary) FROM Profile p2) ");
		List<Object[]> resultList = query.getResultList();		
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);
		em.close();
		emf.close();	
	}
	
	
	public static void queryProfilesWithSalaryAboveAverage() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        profile0_.id as col_0_0_,
			        profile0_.name as col_1_0_,
			        profile0_.department as col_2_0_,
			        profile0_.salary as col_3_0_ 
			    from
			        profile profile0_ 
			    where
			        profile0_.salary>(
			            select
			                avg(profile1_.salary) 
			            from
			                profile profile1_
			        )		 
		*/
		Query query = em.createQuery("SELECT p.id, p.name, p.department, p.salary FROM Profile p WHERE p.salary > (SELECT AVG(p2.salary) FROM Profile p2) ");
		List<Object[]> resultList = query.getResultList();		
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);
		em.close();
		emf.close();
	}
	
	
	
	
	
	
	
	
	
	
}
