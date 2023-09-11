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

import entity.jpql_native.EmployeeNativeLiteral;
import entity.jpql_native.EmployeeNativeLiteral.Role;
import service.embed.SpecificDateTimeSetter;

public class NativeController {

	// Instantiate the instances
	static EmployeeNativeLiteral employeeNativeLiteral01;
	static EmployeeNativeLiteral employeeNativeLiteral02;
	static EmployeeNativeLiteral employeeNativeLiteral03;
	

	public static void main(String[] args) {	
//		insert();
		queryShowColumns();
		queryEqual();
		queryEqual2();
		queryLarger();
		queryEnum();
		queryDate();
		queryBoolean();
		queryASC();
		queryDESC();
		queryDISTINCT();
		queryInnerJoin();
//		queryInnerJoinError();
		

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
	
	public static void queryShowColumns() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
	    EntityManager em = emf.createEntityManager();
	    try {
	    	/*
				Hibernate: 
				    SELECT
				        e.id,
				        e.local_date,
				        e.name,
				        e.role,
				        e.salary,
				        e.valid 
				    FROM
				        employee_jpql_literal e 	    	 
	    	*/
	    	Query query = em.createNativeQuery("SELECT e.id, " + 
													  "e.local_date, " +
													  "e.name, " +
													  "e.role, " +
													  "e.salary, " +
													  "e.valid " +
													  "FROM employee_jpql_literal e ");
	        List<Object[]> rows = query.getResultList();	
	        // Java 1.7
	        /*
	        for (Object[] row : rows) {
	            Integer id = (Integer) row[0];
	            String local_date = ((Date) row[1]).toString();	            
	            String name = (String) row[2];
	            String role = (String) row[3];
	            Integer salary = (Integer) row[4];
	            Boolean valid = (Boolean) row[5];
	            System.out.printf("ID: %5d | Local_Date: %10s | Name: %8s | Role: %5s | Salary: %5d | Valid: %b\n", id, local_date, name, role, salary, valid);
	        }
	        */
	        // Java 1.8
	        rows.forEach(row -> System.out.printf("ID: %5d | Local_Date: %10s | Name: %8s | Role: %5s | Salary: %5d  \n", row[0], ((Date) row[1]).toString(), row[2], row[3], row[4], (Boolean)row[5]));	        
	    } catch (Exception e) {
	        e.printStackTrace();
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
	
	public static void queryInnerJoin() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
	    EntityManager em = emf.createEntityManager();
	    try {
	    	/*
				Hibernate: 
				    SELECT
				        e1.id AS e1_id,
				        e2.id AS e2_id,
				        e1.salary AS e1_salary,
				        e2.salary AS e2_salary 
				    FROM
				        employee_jpql_literal e1 
				    INNER JOIN
				        employee_native_literal e2 
				            ON e1.name = e2.name	    	
	    	*/
	        Query query = em.createNativeQuery("SELECT e1.id AS e1_id, " + 
	        										  "e1.salary AS e1_salary, " +
	        										  "e1.name AS e1_name, " +
	        										  "e2.id AS e2_id, " +
	        										  "e2.salary AS e2_salary, " +
	        										  "e2.name AS e2_name " +
	        										  "FROM employee_jpql_literal e1 " +
	        										  "INNER JOIN employee_native_literal e2 " +
	        										  "ON e1.name = e2.name");	        	         
	        List<Object[]> rows = query.getResultList();
	        // Java 1.7
	        /*
	        for (Object[] row : rows) {
	            Integer e1Id = (Integer) row[0];
	            Integer e1Salary = (Integer) row[1];
	            String e1Name = (String) row[2];
	            Integer e2Id = (Integer) row[3];
	            Integer e2Salary = (Integer) row[4];
	            String e2Name = (String) row[5];
	            System.out.printf("Id1: %5d | Salary1: %5d | Name1: %8s | Id2: %5d | Salary2: %5d | Name2: %8s \n", row[0], row[1], row[2], row[3], row[4], row[5]);
	        }
	        */
	        // Java 1.8 (same result as 1.7 version)
	        rows.forEach(row -> System.out.printf("Id1: %5d | Salary1: %5d | Name1: %8s | Id2: %5d | Salary2: %5d | Name2: %8s \n", row[0], row[1], row[2], row[3], row[4], row[5]));
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }
	}
	
	public static void queryInnerJoinError() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
	    EntityManager em = emf.createEntityManager();
	    try {
	    	/*
				The error you're encountering, "javax.persistence.PersistenceException: org.hibernate.loader.custom.NonUniqueDiscoveredSqlAliasException: 
				
				Encountered a duplicated sql alias [id] during auto-discovery of a native-sql query," 
				is occurring because both of your native SQL queries are returning columns with the same alias (id) in the result set. 
				
				Hibernate is trying to map the result set to the entity objects, but it can't determine which id column corresponds to which entity.	    	 
	    	*/
	    	Query query = em.createNativeQuery("SELECT e1.id, " + 
													  "e1.salary, " +
													  "e1.name, " +
													  "e2.id, " +
													  "e2.salary, " +
													  "e2.name " +
													  "FROM employee_jpql_literal e1 " +
													  "INNER JOIN employee_native_literal e2 " +
													  "ON e1.name = e2.name");
	        List<Object[]> rows = query.getResultList();
	        for (Object[] row : rows) {
	            Integer e1Id = (Integer) row[0];
	            Integer e1Salary = (Integer) row[1];
	            String e1Name = (String) row[2];
	            Integer e2Id = (Integer) row[3];
	            Integer e2Salary = (Integer) row[4];
	            String e2Name = (String) row[5];
	            System.out.printf("Id1: %5d | Salary1: %5d | Name1: %8s | Id2: %5d | Salary2: %5d | Name2: %8s \n", e1Id, e1Salary, e1Name, e2Id, e2Salary, e2Name);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }
	}
	
	
	
	
	
}
