package controller.jpql.where;
//import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import entity.jpql.where.EmployeeInfo;
import entity.jpql.where.EmployeeWhereLiteral;
import entity.jpql.where.EmployeeWhereLiteral.Role;
import entity.jpql.where.EmployeeWhereLiteralMatcher;
import javax.persistence.Tuple;
import service.embed.SpecificDateTimeSetter;

public class WhereController {

	// Instantiate the instances
	static EmployeeWhereLiteral employeeWhereLiteral01;
	static EmployeeWhereLiteral employeeWhereLiteral02;
	static EmployeeWhereLiteral employeeWhereLiteral03;
	
	/*
		Hibernate: 
		    
		    create table employee_where_literal (
		       id integer not null auto_increment,
		        local_date datetime(6),
		        name varchar(255),
		        role varchar(255),
		        salary integer,
		        valid bit,
		        primary key (id)
		    ) engine=InnoDB
	*/
	public static void main(String[] args) {	
		insert();
//		queryEqual();
//		queryLarger();
//		queryEnum();
//		queryDate();
//		queryBoolean();
//		queryASC();
//		queryDESC();
//		queryDISTINCT();
//		querySQLInjection();
//		queryBindVariable();
//		queryPosition();
//		queryMultiColumnsWithObjectArray();
//		queryMultiColumnsWithObjectArrayWithoutEntity();
		queryTuple();
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
			employeeWhereLiteral01 = new EmployeeWhereLiteral("Jim", Role.IT, 3000, localDate1, Boolean.TRUE);
			employeeWhereLiteral02 = new EmployeeWhereLiteral("ROSE", Role.ADMIN, 4000, localDate2, Boolean.FALSE);
			employeeWhereLiteral03 = new EmployeeWhereLiteral("Denise", Role.IT, 1500, localDate3, Boolean.TRUE);
			// 保存
			em.persist(employeeWhereLiteral01);
			em.persist(employeeWhereLiteral02);
			em.persist(employeeWhereLiteral03);			
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
			    select
			        employeewh0_.id as id1_3_,
			        employeewh0_.local_date as local_da2_3_,
			        employeewh0_.name as name3_3_,
				       employeewh0_.role as role4_3_,
				       employeewh0_.salary as salary5_3_,
				       employeewh0_.valid as valid6_3_ 
				   from
				       employee_where_literal employeewh0_ 
				   where
				       employeewh0_.name='Rose'						
		*/
		Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.name = 'Rose' ");
		List<EmployeeWhereLiteral> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName())); // Name: ROSE
		// org.junit.Assert.assertThat 斷言查詢結果的集合物件內，必含有 employeeWhereLiteral02 物件實例
		assertThat(resultList, hasItems(employeeWhereLiteral02));
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
			    select
			        employeewh0_.id as id1_3_,
			        employeewh0_.local_date as local_da2_3_,
			        employeewh0_.name as name3_3_,
			        employeewh0_.role as role4_3_,
			        employeewh0_.salary as salary5_3_,
			        employeewh0_.valid as valid6_3_ 
			    from
			        employee_where_literal employeewh0_ 
			    where
			        employeewh0_.salary>2000.0			 
		*/
		Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.salary > 2000.0 ");
		List<EmployeeWhereLiteral> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName())); 
		// org.junit.Assert.assertThat 斷言查詢結果的集合物件內，必含有 employeeWhereLiteral02 物件實例
		assertThat(resultList, hasItems(employeeWhereLiteral02));
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
		/*
			Hibernate: 
			    select
			        employeewh0_.id as id1_3_,
			        employeewh0_.local_date as local_da2_3_,
			        employeewh0_.name as name3_3_,
			        employeewh0_.role as role4_3_,
			        employeewh0_.salary as salary5_3_,
			        employeewh0_.valid as valid6_3_ 
			    from
			        employee_where_literal employeewh0_ 
			    where
			        employeewh0_.role=?		 
		*/
		Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.role = 'IT' ");
		List<EmployeeWhereLiteral> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName())); 
		// org.junit.Assert.assertThat 斷言查詢結果的集合物件內，必含有 employeeWhereLiteral02 物件實例
		assertThat(resultList, hasItems(employeeWhereLiteral02));
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
			    select
			        employeewh0_.id as id1_3_,
			        employeewh0_.local_date as local_da2_3_,
			        employeewh0_.name as name3_3_,
			        employeewh0_.role as role4_3_,
				       employeewh0_.salary as salary5_3_,
			        employeewh0_.valid as valid6_3_ 
			    from
			        employee_where_literal employeewh0_ 
			    where
			        employeewh0_.local_date>'2016-05-01'			 
		*/
		Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.date > '2016-05-01' ");
		List<EmployeeWhereLiteral> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName())); 
		// org.junit.Assert.assertThat 斷言查詢結果的集合物件內，必含有 employeeWhereLiteral02 物件實例
		assertThat(resultList, hasItems(employeeWhereLiteral02));
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
				   select
			        employeewh0_.id as id1_3_,
			        employeewh0_.local_date as local_da2_3_,
			        employeewh0_.name as name3_3_,
			        employeewh0_.role as role4_3_,
			        employeewh0_.salary as salary5_3_,
			        employeewh0_.valid as valid6_3_ 
			    from
			        employee_where_literal employeewh0_ 
			    where
			        employeewh0_.valid=0			 
		*/
		Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.valid = false ");
		List<EmployeeWhereLiteral> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName()));
		// org.junit.Assert.assertThat 斷言查詢結果的集合物件內，必含有 employeeWhereLiteral02 物件實例
		assertThat(resultList, hasItems(employeeWhereLiteral02));
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
			    select
			        employeewh0_.id as id1_3_,
			        employeewh0_.local_date as local_da2_3_,
				       employeewh0_.name as name3_3_,
				       employeewh0_.role as role4_3_,
				       employeewh0_.salary as salary5_3_,
				       employeewh0_.valid as valid6_3_ 
				   from
				       employee_where_literal employeewh0_ 
				   order by
			        employeewh0_.salary			 
		*/
		Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e ORDER BY e.salary "); // ORDER BY default is ASC
		List<EmployeeWhereLiteral> resultList = query.getResultList();
		/*
			Name: Denise
			Name: Jim
			Name: ROSE 
		*/
		resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName())); 
		List<EmployeeWhereLiteral> expectedItems = Arrays.asList(employeeWhereLiteral03, employeeWhereLiteral01, employeeWhereLiteral02);
		/*
			Denise
			Jim
			ROSE		 
		*/
		expectedItems.forEach(s -> System.out.println(s.getName()));
		assertThat(resultList, EmployeeWhereLiteralMatcher.containsInOrder(expectedItems));
		
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
			    select
			        employeewh0_.id as id1_3_,
			        employeewh0_.local_date as local_da2_3_,
			        employeewh0_.name as name3_3_,
			        employeewh0_.role as role4_3_,
			        employeewh0_.salary as salary5_3_,
			        employeewh0_.valid as valid6_3_ 
			    from
			        employee_where_literal employeewh0_ 
			    order by
			        employeewh0_.salary DESC			 
		*/
		Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e ORDER BY e.salary DESC "); 
		List<EmployeeWhereLiteral> resultList = query.getResultList();
		/*
			Name: ROSE
			Name: Jim
			Name: Denise
		*/
		resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName())); 
		List<EmployeeWhereLiteral> expectedItems = Arrays.asList(employeeWhereLiteral02, employeeWhereLiteral01, employeeWhereLiteral03);
		/*
			ROSE
			Jim
			Denise								
		*/
		expectedItems.forEach(s -> System.out.println(s.getName()));
		assertThat(resultList, EmployeeWhereLiteralMatcher.containsInOrder(expectedItems));
		
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
			    select
			        distinct employeewh0_.name as col_0_0_ 
			    from
			        employee_where_literal employeewh0_		 
		*/
		Query query = em.createQuery("SELECT DISTINCT e.name FROM EmployeeWhereLiteral e"); 
		List<String> resultList = query.getResultList();
		resultList.forEach(s -> System.out.printf("Name: %s\n", s)); 		
		assertThat(resultList, hasItems("Jim"));
		assertThat(resultList, hasItems("ROSE"));
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();	
		}	
	}
	
	// SQL Injection
	public static void querySQLInjection() {	
		String name = "Unreliable input";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.name = '" + name + "'");
			List<String> resultList = query.getResultList();
			resultList.forEach(s -> System.out.printf("Name: %s\n", s));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	// Avoid SQL Injection - Binding Variables (Named Parameters): 
	// When using named parameters in JPQL, you can bind variables using the setParameter method of the Query object. 
	public static void queryBindVariable() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        employeewh0_.id as id1_3_,
				        employeewh0_.local_date as local_da2_3_,
				        employeewh0_.name as name3_3_,
				        employeewh0_.role as role4_3_,
				        employeewh0_.salary as salary5_3_,
				        employeewh0_.valid as valid6_3_ 
				    from
				        employee_where_literal employeewh0_ 
				    where
				        employeewh0_.name=? 
				        and employeewh0_.salary=?			 
			*/
			Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.name = :name AND e.salary = :salary ");
			query.setParameter("name", "ROSE");
			query.setParameter("salary", 4000);
			List<EmployeeWhereLiteral> resultList = query.getResultList();
			resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName()));
			assertThat(resultList, hasItems(employeeWhereLiteral02));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
		
	// Avoid SQL Injection - Position-Based Parameters: 
	// When using position-based parameters, you can bind variables using the setParameter method with an index. 
	public static void queryPosition() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        employeewh0_.id as id1_3_,
				        employeewh0_.local_date as local_da2_3_,
				        employeewh0_.name as name3_3_,
				        employeewh0_.role as role4_3_,
				        employeewh0_.salary as salary5_3_,
				        employeewh0_.valid as valid6_3_ 
				    from
				        employee_where_literal employeewh0_ 
				    where
				        employeewh0_.name=? 
				        and employeewh0_.salary=?						 
			*/
			Query query = em.createQuery("SELECT e FROM EmployeeWhereLiteral e WHERE e.name = ?1 AND e.salary = ?2 ");
			query.setParameter(1, "ROSE");
			query.setParameter(2, 4000);
			List<EmployeeWhereLiteral> resultList = query.getResultList();
			resultList.forEach(s -> System.out.printf("Name: %s\n", s.getName()));
			assertThat(resultList, hasItems(employeeWhereLiteral02));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	// JPQL 使用 non-Entity 類別的建構子將 SELECT 敘述中查詢到的欄位群組，以封裝後回傳 Java 物件
	public static void queryMultiColumnsWithObjectArray() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        employeewh0_.name as col_0_0_,
				        employeewh0_.salary as col_1_0_ 
				    from
				        employee_where_literal employeewh0_
			 */
			Query query = em.createQuery("SELECT e.name, e.salary FROM EmployeeWhereLiteral e "); 
			List<Object[]> resultList = query.getResultList();
			resultList.forEach(s -> System.out.println(Arrays.toString(s)));
			List<String> actualValues = resultList.stream().map(s -> Arrays.toString(s)).collect(Collectors.toList());
			actualValues.forEach(System.out::println);	
//			assertThat(actualValues, containsInAnyOrder("[ROSE, 4000]"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}		
	}
	
	// JPQL 使用 non-Entity 類別的建構子將 SELECT 敘述中查詢到的欄位群組，以封裝後回傳 Java 物件
	public static void queryMultiColumnsWithObjectArrayWithoutEntity() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        employeewh0_.name as col_0_0_,
				        employeewh0_.salary as col_1_0_ 
				    from
				        employee_where_literal employeewh0_			 
			*/
			Query query = em.createQuery("SELECT NEW entity.jpql.where.EmployeeInfo(e.name, e.salary) FROM EmployeeWhereLiteral e"); 
			List<EmployeeInfo> resultList = query.getResultList();
			resultList.forEach(System.out::println);	
//			assertThat(resultList, containsInAnyOrder(employeeWhereLiteral01, employeeWhereLiteral02, employeeWhereLiteral03));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}		
	}
	
	// JPQL 使用 JPA 2.0 提供的 Interface (for extracting the elements of a query result tuple) 
	// 本方法功能類似使用 non-Entity 類別的建構子將 SELECT 敘述中查詢到的欄位群組，以封裝後回傳 Java 物件
	public static void queryTuple() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        employeewh0_.name as col_0_0_,
				        employeewh0_.salary as col_1_0_ 
				    from
				        employee_where_literal employeewh0_			 
			*/
			TypedQuery<Tuple> query = em.createQuery("SELECT e.name, e.salary, e.role FROM EmployeeWhereLiteral e", Tuple.class);
			List<Tuple> resultList = query.getResultList();
			System.out.println(resultList);
			for(Tuple t: resultList) {
				String name = t.get(0, String.class);
				Integer salary = t.get(1, Integer.class);
				Role role = t.get(2, Role.class);
				System.out.printf("name: %s | salary: %d | role: %s\n", name, salary, role);
			}
			List<String> actualValues = resultList.stream().map(s -> s.get(0, String.class) + "_" 
																   + s.get(1, Integer.class) + "_" 
																   + s.get(2, Role.class)).collect(Collectors.toList());
			actualValues.forEach(System.out::println);	
//			assertThat(actualValues, containsInAnyOrder("Jim_3000_IT", "ROSE_4000_ADMIN", "Denise_1500_IT"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}		
	}
	
}
