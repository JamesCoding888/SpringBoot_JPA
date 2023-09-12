package service.aggregation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.aggregation.PurchasedOrder;

public class PurchasedOrderServices {

	// Create
	public static void create(String persistence_unit_name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit_name);
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
			// insert purchasedOrder
			PurchasedOrder po1 = new PurchasedOrder("iPhone15", 3000.0);			
			PurchasedOrder po2 = new PurchasedOrder("iPhone14", 2500.0);
			PurchasedOrder po3 = new PurchasedOrder("iPhone13", 1500.0);
			PurchasedOrder po4 = new PurchasedOrder("iPhone12", 1000.0);
			PurchasedOrder po5 = new PurchasedOrder("iPhone15", 3183.0);
			em.persist(po1);
			em.persist(po2);
			em.persist(po3);
			em.persist(po4);
			em.persist(po5);
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
	public static void queryByCOUNT() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        count(purchasedo0_.id) as col_0_0_ 
			    from
			        purchased_order purchasedo0_		
		*/
		Query query = em.createQuery("SELECT COUNT(po) FROM PurchasedOrder po");
		long result = (long)query.getSingleResult();
		System.out.println(result);
		em.close();
		emf.close();				
	}
	
	
	public static void queryByAVG() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        avg(purchasedo0_.id) as col_0_0_ 
			    from
			        purchased_order purchasedo0_	
		*/
		Query query = em.createQuery("SELECT AVG(po) FROM PurchasedOrder po");
		Double result = (Double)query.getSingleResult();
		System.out.println(result);
		em.close();
		emf.close();				
	}
	
	public static void queryByMAX() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        max(purchasedo0_.price) as col_0_0_ 
			    from
			        purchased_order purchasedo0_	
		*/
		Query query = em.createQuery("SELECT MAX(po.price) FROM PurchasedOrder po");
		Double result = (Double)query.getSingleResult();
		System.out.println(result);
		em.close();
		emf.close();				
	}
	
	public static void queryByMIN() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        min(purchasedo0_.price) as col_0_0_ 
			    from
			        purchased_order purchasedo0_	
		*/
		Query query = em.createQuery("SELECT MIN(po.price) FROM PurchasedOrder po");
		Double result = (Double)query.getSingleResult();
		System.out.println(result);
		em.close();
		emf.close();				
	}
	
	public static void queryBySUM() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        sum(purchasedo0_.price) as col_0_0_ 
			    from
			        purchased_order purchasedo0_	
		*/
		Query query = em.createQuery("SELECT SUM(po.price) FROM PurchasedOrder po");
		Double result = (Double)query.getSingleResult();
		System.out.println(result);
		em.close();
		emf.close();				
	}
	
	
	public static void queryByGROUPBY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        purchasedo0_.price as col_0_0_,
			        count(purchasedo0_.id) as col_1_0_ 
			    from
			        purchased_order purchasedo0_ 
			    group by
			        purchasedo0_.price
		*/
		Query query = em.createQuery("SELECT po.price, COUNT(po) from PurchasedOrder po GROUP BY po.price");
		List<Object[]> resultList = query.getResultList();
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);		
		em.close();
		emf.close();				
	}
	
	
	public static void queryByAvgGROUPBY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        purchasedo0_.name as col_0_0_,
			        avg(purchasedo0_.price) as col_1_0_ 
			    from
			        purchased_order purchasedo0_ 
			    group by
			        purchasedo0_.name
		*/
		Query query = em.createQuery("SELECT po.name, AVG(po.price) from PurchasedOrder po GROUP BY po.name");
		List<Object[]> resultList = query.getResultList();
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);		
		em.close();
		emf.close();				
	}
	
	public static void queryByCountGROUPBY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        purchasedo0_.name as col_0_0_,
			        count(purchasedo0_.id) as col_1_0_ 
			    from
			        purchased_order purchasedo0_ 
			    group by
			        purchasedo0_.name
		*/
		Query query = em.createQuery("SELECT po.name, COUNT(po) from PurchasedOrder po GROUP BY po.name");
		List<Object[]> resultList = query.getResultList();
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);		
		em.close();
		emf.close();				
	}
	
	public static void queryByMaxGROUPBY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        purchasedo0_.name as col_0_0_,
			        max(purchasedo0_.price) as col_1_0_ 
			    from
			        purchased_order purchasedo0_ 
			    group by
			        purchasedo0_.name
		*/
		Query query = em.createQuery("SELECT po.name, MAX(po.price) from PurchasedOrder po GROUP BY po.name");
		List<Object[]> resultList = query.getResultList();
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);		
		em.close();
		emf.close();				
	}
	
	public static void queryBySumGROUPBY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        purchasedo0_.name as col_0_0_,
			        sum(purchasedo0_.price) as col_1_0_ 
			    from
			        purchased_order purchasedo0_ 
			    group by
			        purchasedo0_.name
		*/
		Query query = em.createQuery("SELECT po.name, SUM(po.price) from PurchasedOrder po GROUP BY po.name");
		List<Object[]> resultList = query.getResultList();
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);		
		em.close();
		emf.close();				
	}
	
	
	public static void queryByHavingMaxGROUPBY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        purchasedo0_.name as col_0_0_,
			        max(purchasedo0_.price) as col_1_0_ 
			    from
			        purchased_order purchasedo0_ 
			    group by
			        purchasedo0_.name 
			    having
			        purchasedo0_.name in (
			            'iPhone15' , 'iPhone14' , 'iPhone13' , 'iPhone9'
			        )
		*/
		Query query = em.createQuery("SELECT po.name, MAX(po.price) from PurchasedOrder po "
								   + "GROUP BY po.name "
								   + "HAVING po.name IN ('iPhone15', 'iPhone14', 'iPhone13', 'iPhone9')");
		List<Object[]> resultList = query.getResultList();
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);		
		em.close();
		emf.close();				
	}
	
	public static void queryByHavingMaxGROUPBYOrderBy() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		/*
			Hibernate: 
			    select
			        purchasedo0_.name as col_0_0_,
			        max(purchasedo0_.price) as col_1_0_ 
			    from
			        purchased_order purchasedo0_ 
			    group by
			        purchasedo0_.name 
			    having
			        purchasedo0_.name in (
			            'iPhone15' , 'iPhone14' , 'iPhone13' , 'iPhone9'
			        ) 
			    order by
			        purchasedo0_.name DESC			
		*/
		Query query = em.createQuery("SELECT po.name, MAX(po.price) from PurchasedOrder po "
								   + "GROUP BY po.name "
								   + "HAVING po.name IN ('iPhone15', 'iPhone14', 'iPhone13', 'iPhone9')"
								   + "ORDER BY po.name DESC");
		List<Object[]> resultList = query.getResultList();
		List<String> stringList = resultList.stream().map(o -> Arrays.toString(o)).collect(Collectors.toList());
		stringList.forEach(System.out::println);		
		em.close();
		emf.close();				
	}
	
	
}
