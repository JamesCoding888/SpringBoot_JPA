package service.subquery;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.subquery.JobInfo;
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
	public static void insertProfile() {
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
			Profile p6 = new Profile("zoy", "Sales", 2892.0, 188909909);
			Profile p7 = new Profile("Judy", "Sales", 8280.0, 32098763);
			Profile p8 = new Profile("Neo", "ME", 9980.0, 92873134);
			Profile p9 = new Profile("ted", "ME", 15540.0, 87424);
			Profile p10 = new Profile("Linda", "Logistics", 12324.0, 802342342);
			em.persist(p1);
			em.persist(p2);
			em.persist(p3);
			em.persist(p4);
			em.persist(p5);
			em.persist(p6);
			em.persist(p7);
			em.persist(p8);
			em.persist(p9);
			em.persist(p10);
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
	
	public static void insertJobInfo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			JobInfo p1 = new JobInfo("R&D", "Developer");
			JobInfo p2 = new JobInfo("Sales", "Marketing Development");
			JobInfo p3 = new JobInfo("ME", "Developer");
			JobInfo p4 = new JobInfo("Logistics", "Logistics for Warehouse");
			em.persist(p1);
			em.persist(p2);
			em.persist(p3);
			em.persist(p4);
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
	
	
	public static void queryAverageSalaryByDepartment() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        profile0_.department as col_0_0_,
			        avg(profile0_.salary) as col_1_0_ 
			    from
			        profile profile0_ 
			    group by
			        profile0_.department			
		*/
		Query query = em.createQuery("SELECT p.department, AVG(p.salary) FROM Profile p GROUP BY p.department ");
		List<Object[]> resultList = query.getResultList();		
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);
		em.close();
		emf.close();
	}
	
	
	public static void queryDepartmentsWithAboveAverageSalary() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        profile0_.department as col_0_0_,
			        avg(profile0_.salary) as col_1_0_ 
			    from
			        profile profile0_ 
			    group by
			        profile0_.department 
			    having
			        avg(profile0_.salary)>(
			            select
			                avg(profile1_.salary) 
			            from
			                profile profile1_
			        )				
		*/
		Query query = em.createQuery(
							// 部門平均薪資
							"SELECT p.department, AVG(p.salary) FROM Profile p GROUP BY p.department "
							// 部門平均薪資 > 員工平均薪資
						  + "HAVING AVG(p.salary) > ( SELECT AVG(p2.salary) FROM Profile p2 ) ");
		List<Object[]> resultList = query.getResultList();		
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);
		em.close();
		emf.close();
	}
	
	public static void queryProfilesWithMatchingJobInfo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Description:
				The code provided is a database query designed to retrieve profiles based on the existence of related job information records with matching department values. 
				
				There are several reasons why a developer might need to implement such a query:				
					1) Data Validation and Consistency: 
					   This code can be used to ensure data consistency in the database. 
					   It helps make sure that profiles are associated with valid departments by checking for the existence of corresponding job information records.
	
					2) Data Retrieval: 
					   It allows the retrieval of profiles with specific criteria. 
					   In this case, it retrieves profiles that have at least one job info record associated with them in the same department. 
					   This might be useful for generating reports or aggregating data related to profiles and job information.
					
					3) Authorization and Access Control: 
					   In some applications, access to certain data may be restricted based on specific criteria. 
					   This query could be part of a security mechanism to determine which profiles a user has access to based on their department or role.
					
					4) Data Synchronization: 
					   If the application allows for data synchronization between different tables or systems, queries like this can help identify profiles that require synchronization with other systems based on their department.
					
					5) Business Logic and Analysis: 
					   Depending on the application's business logic, you might want to analyze or perform operations on profiles that have certain relationships with other entities (in this case, job information). 
					   This query helps filter the data for such analysis.
					   
			=======================================================================================================================================================================================================================================
			
			Hibernate: 
			    select
			        profile0_.id as id1_20_,
			        profile0_.department as departme2_20_,
			        profile0_.name as name3_20_,
			        profile0_.phone_number as phone_nu4_20_,
			        profile0_.salary as salary5_20_ 
			    from
			        profile profile0_ 
			    where
			        exists (
			            select
			                jobinfo1_.id 
			            from
			                job_info jobinfo1_ 
			            where
			                jobinfo1_.department=profile0_.department
			        )							
		*/		
		Query query = em.createQuery("SELECT p FROM Profile p "
						   	  	   + "WHERE EXISTS "
						   	  	   + "( SELECT j FROM JobInfo j WHERE j.department = p.department  )");
		List<Profile> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("ID: %3d | Name: %8s | Dept: %8s | Salary: %6.6f | PhoneNumber: %11d\n", 
												  s.getId(), s.getName(), s.getDepartment(), s.getSalary(), s.getPhoneNumber()));		
		em.close();
		emf.close();		
	}
	
	public static void queryProfilesWithNotMatchingJobInfo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Description:
				This code is essentially looking for profiles where there are no corresponding job information records with matching department values. 
				In other words, it retrieves profiles that do not have any associated job information records in the same department. 
				
				There are several reasons why a developer might need to implement such a query:				
					1) Identifying Unassigned or Incomplete Profiles: 
					   It can help identify profiles that have not been assigned to any department or have incomplete information (in terms of job information). These profiles may require further attention or assignment.
	
					2) Data Cleanup: 
					   In some cases, you might use such a query to identify orphaned or outdated profiles that no longer have relevant job information associated with them. This information can be used for data cleanup or archiving.
					
					3) Audit and Monitoring: 
					   It can be part of an auditing or monitoring system to ensure that profiles have the expected relationships with job information records. Deviations from this expectation may trigger alerts or further investigation.
					
					4) Data Validation: 
					   Similar to the original query, this query can also serve as a form of data validation, ensuring that profiles are not associated with job information records in the same department when they shouldn't be.
				   
			=======================================================================================================================================================================================================================================
			
			Hibernate: 
			    select
			        profile0_.id as id1_20_,
			        profile0_.department as departme2_20_,
			        profile0_.name as name3_20_,
			        profile0_.phone_number as phone_nu4_20_,
			        profile0_.salary as salary5_20_ 
			    from
			        profile profile0_ 
			    where
			        not (exists (select
			            jobinfo1_.id 
			        from
			            job_info jobinfo1_ 
			        where
			            jobinfo1_.department=profile0_.department))							
		*/		
		Query query = em.createQuery("SELECT p FROM Profile p "
						   	  	   + "WHERE NOT EXISTS "
						   	  	   + "( SELECT j FROM JobInfo j WHERE j.department = p.department  )");
		List<Profile> resultList = query.getResultList();
		if(resultList.isEmpty()) {
			System.out.println("oops! ");
			return;
		}
		resultList.forEach(s -> System.out.printf("ID: %3d | Name: %8s | Dept: %8s | Salary: %6.6f | PhoneNumber: %11d\n", 
												  s.getId(), s.getName(), s.getDepartment(), s.getSalary(), s.getPhoneNumber()));		
		em.close();
		emf.close();		
	}
	
	
	
}
