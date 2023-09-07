package controller.jpql_native;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.japl_native.EmployeeNativeLiteral;
import entity.japl_native.EmployeeNativeLiteral.Role;
import service.embed.SpecificDateTimeSetter;

public class NativeController {

	// Instantiate the instances
	static EmployeeNativeLiteral employeeNativeLiteral01;
	static EmployeeNativeLiteral employeeNativeLiteral02;
	static EmployeeNativeLiteral employeeNativeLiteral03;
	

	public static void main(String[] args) {	
		insert();
		queryEqual();
		queryEqual2();
		queryLarger();
		queryEnum();
		queryDate();
		queryBoolean();
		queryASC();
		queryDESC();
		queryDISTINCT();

	}

	public static void insert() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			// 取得 SpecificDateTimeSetter instance
			SpecificDateTimeSetter date1 = new SpecificDateTimeSetter();
			date1.setSpecificDateTime(2015, 11, 15, 0, 0, 0, 0);
			Date localDate1 = date1.getSpecificDateTime();
			SpecificDateTimeSetter date2 = new SpecificDateTimeSetter();
			date2.setSpecificDateTime(2017, 5, 1, 0, 0, 0, 0);
			Date localDate2 = date2.getSpecificDateTime();
			SpecificDateTimeSetter date3 = new SpecificDateTimeSetter();
			date3.setSpecificDateTime(2016, 1, 10, 0, 0, 0, 0);
			Date localDate3 = date3.getSpecificDateTime();
			// 新增
			employeeNativeLiteral01 = new EmployeeNativeLiteral("Jim", Role.IT, 3000, localDate1, Boolean.TRUE);
			employeeNativeLiteral02 = new EmployeeNativeLiteral("ROSE", Role.ADMIN, 4000, localDate2, Boolean.FALSE);
			employeeNativeLiteral03 = new EmployeeNativeLiteral("Denise", Role.IT, 1500, localDate3, Boolean.TRUE);
			// 保存
			em.persist(employeeNativeLiteral01);
			em.persist(employeeNativeLiteral02);
			em.persist(employeeNativeLiteral03);			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			em.close();
			emf.close();
		}
	}	
	
	public static void queryEqual() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		// 查詢 (使用字面常量使用字串)
		/*
			Hibernate: 
			    SELECT
			        name,
			        salary 
			    from
			        employee_native_literal 					
		*/
		Query query = em.createNativeQuery("SELECT e.name FROM employee_native_literal e WHERE e.name = 'ROSE' ");
		List<Object> rows = query.getResultList();
		rows.forEach(System.out::println);
		} catch(Exception e) {			
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}		
	}
	
	
	public static void queryEqual2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		// 查詢 (使用字面常量使用字串)
		/*
			Hibernate: 
			    SELECT
			        name,
			        salary 
			    from
			        employee_native_literal 					
		*/
		Query query = em.createNativeQuery("SELECT name, salary FROM employee_native_literal ");
		List<Object> rows = query.getResultList();
		List<String> columns = rows.stream().map(o -> Arrays.toString((Object[])o)).collect(Collectors.toList());
		columns.forEach(System.out::println);		
		} catch(Exception e) {			
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}		
	}
	
	
	
	public static void queryLarger() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		/*
			Hibernate: 
			    SELECT
			        e.id,
			        e.name,
			        e.salary 
			    FROM
			        employee_native_literal e 
			    WHERE
			        e.salary > 2000.0 		 
		*/
		Query query = em.createNativeQuery("SELECT e.id, e.name, e.salary FROM employee_native_literal e WHERE e.salary > 2000.0 ");
		List<Object> rows = query.getResultList();
		List<String> columns = rows.stream().map(o -> Arrays.toString((Object[])o)).collect(Collectors.toList());
		columns.forEach(System.out::println);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}
	}
	
	public static void queryEnum() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		// 查詢 (使用字面常量使用字串)
		/*
			Hibernate: 
			    SELECT
			        e.role 
			    FROM
			        employee_native_literal e 
			    WHERE
			        e.role = 'IT' 		
		*/
		Query query = em.createNativeQuery("SELECT e.role FROM employee_native_literal e WHERE e.role = 'IT' ");
		List<Object> rows = query.getResultList();
		rows.forEach(System.out::println);
		} catch(Exception e) {			
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}		
	}

	public static void queryDate() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		/*
			Hibernate: 
			    SELECT
			        e.local_date 
			    FROM
			        employee_native_literal e 
			    WHERE
			        e.local_date > '2016-05-01'			 
		*/
		Query query = em.createNativeQuery("SELECT e.local_date FROM employee_native_literal e WHERE e.local_date > '2016-05-01'  ");
		List<Object> rows = query.getResultList();
		rows.forEach(System.out::println);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}
	}
	
	public static void queryBoolean() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		/*
			Hibernate: 
			    SELECT
			        e.valid 
			    FROM
			        employee_native_literal e 
			    WHERE
			        e.valid = false 	 
		*/
		Query query = em.createNativeQuery("SELECT e.valid FROM employee_native_literal e WHERE e.valid = false ");
		List<Object> rows = query.getResultList();
		rows.forEach(System.out::println);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}
	}
	
	public static void queryASC() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		/*
			Hibernate: 
			    SELECT
			        e.name 
			    FROM
			        employee_native_literal e 
			    ORDER BY
			        e.salary
		*/
		Query query = em.createNativeQuery("SELECT e.name FROM employee_native_literal e ORDER BY e.salary "); // ORDER BY default is ASC
		List<Object> rows = query.getResultList();
		/*
			Name: Denise
			Name: Jim
			Name: ROSE 
		*/
		rows.forEach(o -> System.out.printf("Name: %s\n", o.toString()));		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}	
	}

	
	public static void queryDESC() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		/*
			Hibernate: 
			    SELECT
			        e.name 
			    FROM
			        employee_native_literal e 
			    ORDER BY
			        e.salary DESC 					 
		*/
		Query query = em.createNativeQuery("SELECT e.name FROM employee_native_literal e ORDER BY e.salary DESC "); 
		List<Object> rows = query.getResultList();
		/*
			Name: ROSE
			Name: Jim
			Name: Denise
		*/
		rows.forEach(o -> System.out.printf("Name: %s\n", o.toString())); 		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}	
	}
	
	
	public static void queryDISTINCT() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
		/*
			Hibernate: 
			    SELECT
			        DISTINCT e.name 
			    FROM
			        employee_native_literal e	 
		*/
		Query query = em.createNativeQuery("SELECT DISTINCT e.name FROM employee_native_literal e"); 
		List<Object> rows = query.getResultList();
		rows.forEach(o -> System.out.printf("Name: %s\n", o.toString())); 				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}	
	}
	
	
}
