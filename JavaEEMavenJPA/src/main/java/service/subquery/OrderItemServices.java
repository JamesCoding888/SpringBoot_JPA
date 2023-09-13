package service.subquery;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.subquery.OrderItem;
import entity.subquery.ProductInventory;


public class OrderItemServices {

	
	// Create
	public static void create() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
	}
	
	// Insert
	public static void insertOrder() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			OrderItem o1 = new OrderItem("apple", 20);
			OrderItem o2 = new OrderItem("apple", 12);		
			ProductInventory pi1 = new ProductInventory("apple", 10);
			ProductInventory pi2 = new ProductInventory("apple", 11);
			ProductInventory pi3 = new ProductInventory("apple", 12);
			ProductInventory pi4 = new ProductInventory("apple", 13);
			ProductInventory pi5 = new ProductInventory("apple", 14);
			em.persist(o1);
			em.persist(o2);
			em.persist(pi1);
			em.persist(pi2);
			em.persist(pi3);
			em.persist(pi4);
			em.persist(pi5);
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
	public static void queryALL() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {			
			/*
				Description:
					This code is used to retrieve all OrderItem objects (likely representing items in an order) where the 'quantity' of the item is greater than the maximum 'quantity' value in the 'ProductInventory' for the same product. 
					In other words, it's finding all order items where the quantity ordered is greater than the quantity available in inventory for the same product.
					   
				Application:
					For example, this could be used in an e-commerce application to identify items that are in high demand and possibly need to be restocked. 
					
					If the quantity in the OrderItem is greater than the maximum quantity in the ProductInventory for that product, it might indicate a need to reorder or take some other inventory management action.
					The ALL keyword in the query ensures that the condition holds for all matching records in the subquery, not just some of them.
					
				=======================================================================================================================================================================================================================================
				
				Hibernate: 
				    select
				        orderitem0_.id as id1_9_,
				        orderitem0_.product_name as product_2_9_,
				        orderitem0_.quantity as quantity3_9_ 
				    from
				        order_item orderitem0_ 
				    where
				        orderitem0_.quantity>all (
				            select
				                productinv1_.quantity 
				            from
				                product_inventory productinv1_ 
				            where
				                productinv1_.product_name=orderitem0_.product_name
				        )						
			*/
			Query query = em.createQuery("SELECT oi FROM OrderItem oi WHERE oi.quantity > "
									   + "ALL ( SELECT pi.quantity FROM ProductInventory pi "
									   + "WHERE pi.productName = oi.productName )");
			List<OrderItem> resultList = query.getResultList();
			resultList.forEach(oi -> System.out.printf("ID: %d | ProductName: %s | Quantity: %d\n", oi.getId(), oi.getProductName(), oi.getQuantity()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void queryANY() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
		EntityManager em = emf.createEntityManager();
		try {			
			/*
				Description:
					Let's explain the difference, 
					1) In the previous code using ALL, it selected records from OrderItem where the quantity of the item was greater than all values returned by the subquery. 
					   This means that the condition had to hold for every record in the subquery's result.
					2) In the current code using ANY, it selects records from OrderItem where the quantity of the item is greater than at least one value returned by the subquery. 
					   This means that the condition only needs to hold for one or more records in the subquery's result.  
				Application:
					In practical terms, this query might be used to find items in an order that are out of stock for at least one product, even if there are other products in the order with sufficient stock. 
					It can be used for inventory management and alerting when certain products are running low or out of stock.
					
				=======================================================================================================================================================================================================================================
				
				Hibernate: 
				    select
				        orderitem0_.id as id1_9_,
				        orderitem0_.product_name as product_2_9_,
				        orderitem0_.quantity as quantity3_9_ 
				    from
				        order_item orderitem0_ 
				    where
				        orderitem0_.quantity<any (
				            select
				                productinv1_.quantity 
				            from
				                product_inventory productinv1_ 
				            where
				                productinv1_.product_name=orderitem0_.product_name
				        )										
			*/
			Query query = em.createQuery("SELECT oi FROM OrderItem oi WHERE oi.quantity < "
									   + "ANY ( SELECT pi.quantity FROM ProductInventory pi "
									   + "WHERE pi.productName = oi.productName )");
			List<OrderItem> resultList = query.getResultList();
			resultList.forEach(oi -> System.out.printf("ID: %d | ProductName: %s | Quantity: %d\n", oi.getId(), oi.getProductName(), oi.getQuantity()));
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	
	
	
}
