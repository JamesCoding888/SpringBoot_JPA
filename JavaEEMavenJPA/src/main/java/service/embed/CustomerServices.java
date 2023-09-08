package service.embed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import entity.embed.Customer;
import entity.embed.CustomerItemQty;

public class CustomerServices {
	
	public static void create(String persistence_unit_name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit_name);
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
	}
	
	public static void insertCustomerEmbeddedCustomerItemQtyMap() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();		
		try {
			// Transaction begin
			transaction.begin();
			// Create instance of Customer
			Customer customer = new Customer();
			// Create instance of Many CustomerItemQty
			CustomerItemQty c1 = new CustomerItemQty();
			CustomerItemQty c2 = new CustomerItemQty();
			// 新增 Customer
			customer.setName("david");
			// 新增 CustomerItemQty
			c1.setItemName("Salad");
			c1.setItemQty(1);
			c2.setItemName("tea");
			c2.setItemQty(2);
			// 新增 Many CustomerItemQty 至 Customer
			Map<String, Integer> itemQtyMap = new HashMap<>();
			itemQtyMap.put(c1.getItemName(), c1.getItemQty());
			itemQtyMap.put(c2.getItemName(), c2.getItemQty());
			customer.setItemQtyMap(itemQtyMap);
			// 執行保存，將 EntityManager 管控的 Entity 釋放，但仍可 Roll-back
			em.persist(customer);
			// Flush to Database, and cannot Roll-back
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
	
	public static void queryCustomerEmbeddedCustomerItemQty() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        itemqtymap1_.itemQtyMap_KEY as col_0_0_,
				        itemqtymap1_.itemQtyMap as col_1_0_ 
				    from
				        customer_embedded customer0_ 
				    inner join
				        customer_item_qty_map itemqtymap1_ 
				            on customer0_.id=itemqtymap1_.customer_id			 
			*/
			// In JPQL, when you use 'JOIN' without specifying a join type, it is treated as an 'INNER JOIN' by default. 
			TypedQuery<Map.Entry> resultList = em.createQuery("SELECT ENTRY(mapEntry) FROM Customer c JOIN c.itemQtyMap mapEntry", Map.Entry.class);
			List<Map.Entry> entryList = resultList.getResultList();
			List<String> stringList = entryList.stream().map(entry -> entry.getKey() + ", " + entry.getValue()).collect(Collectors.toList());
			stringList.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryTuple() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*			 	
				Hibernate: 
				    select
				        customer0_.name as col_0_0_,
				        itemqtymap1_.itemQtyMap_KEY as col_1_0_,
				        itemqtymap1_.itemQtyMap as col_2_0_ 
				    from
				        customer_embedded customer0_ 
				    inner join
				        customer_item_qty_map itemqtymap1_ 
				            on customer0_.id=itemqtymap1_.customer_id						 
			*/			
			TypedQuery<Tuple> resultList = em.createQuery("SELECT c.name, KEY(mapEntry), VALUE(mapEntry) FROM Customer c JOIN c.itemQtyMap mapEntry", Tuple.class);
			List<Tuple> entryList = resultList.getResultList();
			List<String> stringList = entryList.stream().map(entry -> entry.get(0) + ", " + entry.get(1) + ", " + entry.get(2)).collect(Collectors.toList());
			stringList.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryKEY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
			 	The JPQL query provided is used to retrieve instances of the "Customer" entity 
			 	that are associated with items in the "itemQtyMap" where the key (the item name) contains the substring "Salad."
			 	 
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_ 
				    from
				        customer_embedded customer0_ 
				    inner join
				        customer_item_qty_map itemqtymap1_ 
				            on customer0_.id=itemqtymap1_.customer_id 
				    where
				        itemqtymap1_.itemQtyMap_KEY like '%Salad%'
				Hibernate: 
				    select
				        itemqtymap0_.customer_id as customer1_3_0_,
				        itemqtymap0_.itemQtyMap as itemQtyM2_3_0_,
				        itemqtymap0_.itemQtyMap_KEY as itemQtyM3_0_ 
				    from
				        customer_item_qty_map itemqtymap0_ 
				    where
				        itemqtymap0_.customer_id=?				
			*/			
			TypedQuery<Customer> resultList = em.createQuery("SELECT c FROM Customer c JOIN c.itemQtyMap mapEntry WHERE KEY(mapEntry) LIKE '%Salad%' ", Customer.class);
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s, Key: %s, Value: %s\n", 
												s.getId(), 
												s.getName(), 
												s.getItemQtyMap().entrySet().iterator().next().getKey(), 
												s.getItemQtyMap().entrySet().iterator().next().getValue()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	
	
	
	
}
