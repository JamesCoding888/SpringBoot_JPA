package service.embed;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
	
	// Insert
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
			customer.setName("Tom");			
			// 新增 CustomerItemQty			
			c1.setItemName("Bread");			
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
	
	
	public static void insertCustomer() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();		
		try {
			// Transaction begin
			transaction.begin();
			// Create instance of Customer
			Customer customer = new Customer();
			// 新增 Customer
			customer.setName("ATim");
			LocalDate purchasedDate = LocalDate.of(2023, 9, 8);
	        Date pd = Customer.convertToLocalDateToDate(purchasedDate);
	        LocalDate receivedDate = LocalDate.of(2023, 9, 25);
	        Date rd = Customer.convertToLocalDateToDate(receivedDate);
	        customer.setPurchasedDate(pd);		
	        customer.setReceivedDate(rd);					
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
	
	// Query
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
	
	public static void queryBETWEENByNumber() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.id between 2 and 4			 					
			*/			
			TypedQuery<Customer> resultList = em.createQuery("SELECT c FROM Customer c WHERE c.id BETWEEN 2 AND 4 ", Customer.class);
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	
	public static void queryBETWEENByDate() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.purchased_date between ? and ?							 					
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.purchasedDate BETWEEN :startDate AND :endDate ");
			resultList.setParameter("startDate", Customer.convertToLocalDateToDate(LocalDate.of(2023, 7, 7)));
			resultList.setParameter("endDate", Customer.convertToLocalDateToDate(LocalDate.of(2023, 9, 15)));
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryNOTBETWEENByDate() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.purchased_date not between ? and ?							 					
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.purchasedDate NOT BETWEEN :startDate AND :endDate ");
			resultList.setParameter("startDate", Customer.convertToLocalDateToDate(LocalDate.of(2023, 5, 7)));
			resultList.setParameter("endDate", Customer.convertToLocalDateToDate(LocalDate.of(2023, 6, 15)));
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIN() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name in (
				            'Tim' , 'Tom'
				        )										 					
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name IN('Tim', 'Tom') ");
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	
	public static void queryIN4BindingVariable() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name in (
				            ? , ? , ?
				        ) 									 					
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name IN :customerNames ");						
			resultList.setParameter("customerNames", Arrays.asList("Tim", "Tom", "James"));
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	
	public static void queryLIKEWithWildCard() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Description: 
					The underscore '_' represents a single character wildcard. 
					This means that it will match any single character in the name column, followed by "Tom." 
				
				Assuming you have the following data in the table of 'customer_embedded':				
					id	name
					1	Tom
					2	ATom
					3	BTom
					4	TTom			
				
				Console will be:
					ID: 2, Name: ATom
					ID: 3, Name: BTom
					ID: 4, Name: TTom	
				
					
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name like '_Tom' 
				    order by
				        customer0_.id							 				
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE '_Tom' ORDER BY c.id ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryNOTLIKEWithWildCard() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*
				Description: 
					The underscore '_' represents a single character wildcard. 
					This means that it will match any single character in the name column, followed by "Tom." 
				
				Assuming you have the following data in the table of 'customer_embedded':				
					id	name
					1	Tom
					2	ATom
					3	BTom
					4	TTom			
					5	James
				
				Console will be:
					ID: 1, Name: Tom
					ID: 5, Name: James
						
										
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name not like '_Tom' 
				    order by
				        customer0_.id				    							 				
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name NOT LIKE '_Tom' ORDER BY c.id ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryLIKEWithEscapeWildCard() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Assuming you have the following data in the table of 'customer_embedded':				
					id	name
					1	Tom
					2	ATom
					3	BTom
					4	TTom			
					
				Console will be:
					ID: 1, Name: Tom
						
						
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name like '_Tom' escape '_' 
				    order by
				        customer0_.id									 				
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE '_Tom' ESCAPE '_' ORDER BY c.id ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	
	public static void queryNOTLIKEWithEscapeWildCard() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Assuming you have the following data in the table of 'customer_embedded':				
					id	name
					1	Tom
					2	ATom
					3	BTom
					4	TTom	
					5   James		
					
				Console will be:
					ID: 2, Name: ATom
					ID: 3, Name: BTom
					ID: 4, Name: TTom
					ID: 5, Name: James
						
												
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name not like '_Tom' escape '_' 
				    order by
				        customer0_.id								 				
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name NOT LIKE '_Tom' ESCAPE '_' ORDER BY c.id ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryLIKEWithWildCard02() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Description:
					This query will retrieve all customers whose names start with the letter 'T'. 
					It uses the % wildcard to match any sequence of characters that follows 'T'. 
					
				Assuming you have the following data in the table of 'customer_embedded':				
					id	name
					1	Tom
					2	ATom
					3	BTom
					4	TTom	
					5   James		
					
				Console will be:
					ID: 1, Name: Tom
					ID: 4, Name: TTom
					
																	
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name like 'T%'									 				
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE 'T%' ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryLIKEWithWildCard03() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Description:
					This query is looking for customers whose names end with an "o" character followed by any single character (underscore "_").
					
				Assuming you have the following data in the table of 'customer_embedded':				
					id	name
					1	Tom
					2	ATom
					3	BTom
					4	TTom	
					5   James		
					
				Console will be:
					ID: 1, Name: Tom
					ID: 2, Name: ATom
					ID: 3, Name: BTom
					ID: 4, Name: TTom
					
																	
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name like '%o_'
											
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE '%o_' ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryLIKEWithWildCard04() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Description:
					This query is looking for customers whose names contain an "o" character followed by exactly one character (underscore "_") at any position within the name. 
					
					
				Assuming you have the following data in the table of 'customer_embedded':				
					id	name
					1	Tom
					2	ATom
					3	BTom
					4	TTom	
					5   James		
					
				Console will be:
					ID: 1, Name: Tom
					ID: 2, Name: ATom
					ID: 3, Name: BTom
					ID: 4, Name: TTom
					
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name like '%T_%'																	
	
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE '%o_%' ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIsNULL() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name is null																				
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name IS NULL ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	// 出錯!
	public static void queryIsNULL4CollectionTypeError() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				If you're attempting to run a query on a collection field in JPA, such as itemQtyMap, 
				it's important to note that JPA JPQL doesn't support querying collections directly. 
				
				ERROR: 
					You have an error in your SQL syntax;
					javax.persistence.PersistenceException: org.hibernate.exception.SQLGrammarException: could not extract ResultSet
				
				To query elements within a collection, you typically need to perform joins or utilize subqueries. 																			
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.itemQtyMap IS NULL ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIsNotNULL4CollectionType() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Hibernate: 
				    select
				        distinct customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    inner join
				        customer_item_qty_map itemqtymap1_ 
				            on customer0_.id=itemqtymap1_.customer_id 
				    where
				        itemqtymap1_.itemQtyMap_KEY is not null																	
			*/			
			Query resultList = em.createQuery("SELECT DISTINCT c FROM Customer c INNER JOIN c.itemQtyMap mapEntry WHERE KEY(mapEntry) IS NOT NULL  ");
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIsNotNULL() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        customer0_.name is not null																				
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name IS NOT NULL ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIsEmpty() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        not (exists (select
				            customer0_.id 
				        from
				            customer_embedded customer0_))																						
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name IS EMPTY ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIsNotEmpty() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        exists (
				            select
				                customer0_.id 
				            from
				                customer_embedded customer0_
				        )																									
			*/			
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.name IS NOT EMPTY ");									
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIsEmpty4CollectionType() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        (
				            select
				                count(*) 
				            from
				                customer_item_qty_map itemqtymap1_ 
				            where
				                customer0_.id=itemqtymap1_.customer_id
				        )=0																												
			*/			
//			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE (SELECT COUNT(*) FROM c.itemQtyMap) = 0");
			/*
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        not (exists (select
				            itemqtymap1_.itemQtyMap 
				        from
				            customer_item_qty_map itemqtymap1_ 
				        where
				            customer0_.id=itemqtymap1_.customer_id))	
				
				Note:
				此 'EMPTY' 語法，需更改 pom.xml 的 dependency 版本，如下
				  
				<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
				<dependency>
					<groupId>org.hibernate</groupId>
					<artifactId>hibernate-core</artifactId>
					<version>5.5.5.Final</version> 
				</dependency>
				  
				<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
				<dependency>
					<groupId>org.hibernate</groupId>
					<artifactId>hibernate-entitymanager</artifactId>
					<version>5.5.5.Final</version>
				</dependency>
				
				====================================================================================================================================================================================
				若不修改 dependency 版本，會遇到此錯誤訊息:
				'java.lang.IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: unexpected end of subtree [SELECT c FROM Customer c WHERE c.itemQtyMap IS EMPTY  ]'		 
			*/
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.itemQtyMap IS EMPTY");
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryIsNotEmpty4CollectionType() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {
			/*				
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        (
				            select
				                count(*) 
				            from
				                customer_item_qty_map itemqtymap1_ 
				            where
				                customer0_.id=itemqtymap1_.customer_id
				        )<>0	
				        
				
				Note:				
				The condition '<> 0' in the WHERE clause checks if the count of rows from the subquery is not equal to zero, which is equivalent to the JPQL query's condition (SELECT COUNT(*) FROM c.itemQtyMap) != 0.
				
			*/			
//			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE (SELECT COUNT(*) FROM c.itemQtyMap) != 0");
			/*
				Hibernate: 
				    select
				        customer0_.id as id1_2_,
				        customer0_.name as name2_2_,
				        customer0_.purchased_date as purchase3_2_,
				        customer0_.received_date as received4_2_ 
				    from
				        customer_embedded customer0_ 
				    where
				        exists (
				            select
				                itemqtymap1_.itemQtyMap 
				            from
				                customer_item_qty_map itemqtymap1_ 
				            where
				                customer0_.id=itemqtymap1_.customer_id
				        )	
				  
				====================================================================================================================================================================================
				Note:
				此 'EMPTY' 語法，需更改 pom.xml 的 dependency 版本，如下
				  
				<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
				<dependency>
				  <groupId>org.hibernate</groupId>
			      <artifactId>hibernate-core</artifactId>
				  <version>5.5.5.Final</version> 
				</dependency>
				  
				<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
				<dependency>
				  <groupId>org.hibernate</groupId>
				  <artifactId>hibernate-entitymanager</artifactId>
				  <version>5.5.5.Final</version>
				</dependency>		
				  
				====================================================================================================================================================================================
				若不修改 dependency 版本，會遇到此錯誤訊息:
				'java.lang.IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: unexpected end of subtree [SELECT c FROM Customer c WHERE c.itemQtyMap IS NOT EMPTY  ]'
			*/
			Query resultList = em.createQuery("SELECT c FROM Customer c WHERE c.itemQtyMap IS NOT EMPTY");
			List<Customer> list = resultList.getResultList();
			list.forEach(s -> System.out.printf("ID: %d, Name: %s\n", s.getId(), s.getName()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
}
