package service.jpql_function;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import entity.jpql_function.Stock;


public class StockServices {
		
		// Create
		public static void create() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			em.close();
			emf.close();
		}
		
		// Insert
		public static void insertStock() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();
				Stock stock1 = new Stock("2330", "TSMC", 650.0);
				Stock stock2 = new Stock("2303", "UMC", 47.3);
				Stock stock3 = new Stock("00900", "Fubon Taiwan Index high dividend 30 ETF", 12.72);	
				em.persist(stock1);
				em.persist(stock2);
				em.persist(stock3);				
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
		public static void queryCONCAT() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {
				/*
					Hibernate: 
					    select
					        concat(stock0_.symbol,
					        '_',
					        stock0_.name) as col_0_0_ 
					    from
					        stock stock0_				 
				*/
				Query query = em.createQuery("SELECT CONCAT( s.symbol, '_', s.name ) FROM Stock s");
				List<String> resultList = query.getResultList();
				resultList.forEach(System.out::println);				
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				em.close();
				emf.close();
			}
		}

		public static void queryLENGTH() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {
				/*
					Hibernate: 
					    select
					        length(concat(stock0_.symbol,
					        '_',
					        stock0_.name)) as col_0_0_ 
					    from
					        stock stock0_				 
				*/
				Query query = em.createQuery("SELECT LENGTH( CONCAT( s.symbol, '_', s.name ) ) FROM Stock s");
				List<Integer> resultList = query.getResultList();
				resultList.forEach(System.out::println);				
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				em.close();
				emf.close();
			}
		}
	
		
		public static void queryMAXLENGTH() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {
				/*
					Description:
						The code appears to retrieve records from the Stock entity where the length of the concatenated symbol and name 
						for a particular record is equal to the maximum length of the concatenated symbol and name across all records in the same table.
						
						In practical terms, this query can be used to find the stock with the longest concatenated symbol and name value in the Stock table. 
						It may be used for various purposes, such as identifying the stock with the longest name or symbol for reporting or analysis.	
						
					=======================================================================================================================================================================================================================================	
					
					Hibernate: 
					    select
					        stock0_.id as id1_26_,
					        stock0_.name as name2_26_,
					        stock0_.price as price3_26_,
					        stock0_.symbol as symbol4_26_ 
					    from
					        stock stock0_ 
					    where
					        length(concat(stock0_.symbol, '_', stock0_.name))=(
					            select
					                max(length(concat(stock1_.symbol,
					                '_',
					                stock1_.name))) 
					            from
					                stock stock1_
					        )								
				*/
				Query query = em.createQuery("SELECT s FROM Stock s "
										   + "WHERE LENGTH(CONCAT(s.symbol, '_', s.name)) = "
										   + " ( SELECT MAX(LENGTH(CONCAT(s2.symbol, '_', s2.name))) FROM Stock s2) ");
				List<Stock> resultList = query.getResultList();
				resultList.forEach(System.out::println);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
				emf.close();
			}
		}
		
		
		public static void queryLOWER() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {
				/*
					Hibernate: 
					    select
					        stock0_.id as id1_26_,
					        stock0_.name as name2_26_,
					        stock0_.price as price3_26_,
					        stock0_.symbol as symbol4_26_ 
					    from
					        stock stock0_ 
					    where
					        lower(stock0_.name)='tsmc'				 
				*/
				Query query = em.createQuery("SELECT s FROM Stock s WHERE LOWER(s.name) = 'tsmc' ");
				List<Stock> resultList = query.getResultList();
				resultList.forEach(System.out::println);				
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				em.close();
				emf.close();
			}			
		}
		
		public static void queryUPPER() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {
				/*
					Hibernate: 
					    select
					        stock0_.id as id1_26_,
					        stock0_.name as name2_26_,
					        stock0_.price as price3_26_,
					        stock0_.symbol as symbol4_26_ 
					    from
					        stock stock0_ 
					    where
					        upper(stock0_.name)='TSMC'	 
				*/
				Query query = em.createQuery("SELECT s FROM Stock s WHERE UPPER(s.name) = 'TSMC' ");
				List<Stock> resultList = query.getResultList();
				resultList.forEach(System.out::println);				
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				em.close();
				emf.close();
			}			
		}
		
		
		public static void queryNameSubstringWithLengthById() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {
				/*
					Description:						
						1) "SELECT SUBSTRING(s.name, 6, 2)
						   The code snippet is going to extract a substring of the 'name' column in the 'Stock' entity starting at position 6 (1-based index) and including 2 characters.
							
						   For an example of the value in column 'name', it's 'Fubon Taiwan Index high dividend 30 ETF'.
						   The executed output, will be ' T'.
						   
					=======================================================================================================================================================================================================================================
					
					Hibernate: 
					    select
					        substring(stock0_.name,
					        6,
					        2) as col_0_0_ 
					    from
					        stock stock0_ 
					    where
					        stock0_.id=?	
					
					=======================================================================================================================================================================================================================================					
					
					A kindly reminder of 'substring' API, in Java:
					
					Here is snippet code, 						
						String name = "Fubon Taiwan Index high dividend 30 ETF";	
						String subStringInJava = name.substring(5, 7);
						System.out.println(subStringInJava); // ' T'				
					
					Note:
						In Java, the substring method uses 0-based indices, so name.substring(5, 7) will extract characters at indices 5 (inclusive) and 7 (exclusive).		 
		
				*/
				TypedQuery<String> query = em.createQuery("SELECT SUBSTRING(s.name, 6, 2) FROM Stock s WHERE s.id = :s_id ", String.class);
				query.setParameter("s_id", 3);
				List<String> resultList = query.getResultList();
				resultList.forEach(System.out::println);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
				emf.close();
			}
		}
		
		public static void queryNameSubstringById() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {	
				/*
					Hibernate: 
					    select
					        substring(stock0_.name,
					        6) as col_0_0_ 
					    from
					        stock stock0_ 
					    where
					        stock0_.id=?				 
				*/
				TypedQuery<String> query = em.createQuery("SELECT SUBSTRING(s.name, 6) FROM Stock s WHERE s.id = :s_id ", String.class);
				query.setParameter("s_id", 3);
				List<String> resultList = query.getResultList();
				resultList.forEach(System.out::println);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
				emf.close();
			}
		}
		
		
		public static void queryNameByTRIM() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {				
				// TRIM(LEADING 'Fubon' FROM s.name) -> 刪除 name 前面 'Fubon'
				/*				    
					Hibernate: 
					    select
					        trim(LEADING 'Fubon' 
					    FROM
					        stock0_.name) as col_0_0_ 
					    from
					        stock stock0_ 
					    where
					        stock0_.id=?									   
				*/
				TypedQuery<String> query1 = em.createQuery("SELECT TRIM(LEADING 'Fubon' FROM s.name) FROM Stock s WHERE s.id = :s_id", String.class);
				// TRIM(TRAILING 'ETF' FROM s.name) -> 刪除 name 後面 'ETF'
				/*
					Hibernate: 
					    select
					        trim(TRAILING 'ETF' 
					    FROM
					        stock0_.name) as col_0_0_ 
					    from
					        stock stock0_ 
					    where
					        stock0_.id=?				 
				*/
				TypedQuery<String> query2 = em.createQuery("SELECT TRIM(TRAILING 'ETF' FROM s.name) FROM Stock s WHERE s.id = :s_id", String.class);
				// TRIM(s.name) -> Default 為 TRIM(BOTH ' ' FROM s.name)，name 前後空白移除
				/*
					Hibernate: 
					    select
					        trim(stock0_.name) as col_0_0_ 
					    from
					        stock stock0_ 
					    where
					        stock0_.id=?				 
				*/
				TypedQuery<String> query3 = em.createQuery("SELECT TRIM(s.name) FROM Stock s WHERE s.id = :s_id", String.class);
				query1.setParameter("s_id", 3);
				query2.setParameter("s_id", 3);
				query3.setParameter("s_id", 3);
				List<String> results1 = query1.getResultList();
				List<String> results2 = query2.getResultList();
				List<String> results3 = query3.getResultList();
				if (!results1.isEmpty()) {
					String trimmedName = results1.get(0);
					System.out.println(trimmedName);
				} 
				if (!results2.isEmpty()) {
					String trimmedName = results2.get(0);
					System.out.println(trimmedName);
				} 
				if (!results3.isEmpty()) {
					String trimmedName = results3.get(0);
					System.out.println(trimmedName);
				} 
				if (results1.isEmpty() || results2.isEmpty() || results3.isEmpty()) {
					return;
				} 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
				emf.close();
			}
		}
		
		
		public static void queryByLocate() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
			EntityManager em = emf.createEntityManager();
			try {
				/*
				    Description:
				    	In this query, LOCATE('i', s.name) is used to find the position of the first occurrence of 'i' in the "name" field. 
				    	The SUBSTRING function then extracts a substring starting from that position to the end of the "name" field. 
				    	The TRIM function is applied to remove any leading or trailing spaces. 
				    	This query will return a substring that includes the 'i' character and the characters that follow it in the "name" field.
				    
				    =======================================================================================================================================================================================================================================
				    	
					Hibernate: 
					    select
					        distinct trim(substring(stock0_.name,
					        locate('i',
					        stock0_.name))) as col_0_0_ 
					    from
					        stock stock0_ 
					    order by
					        col_0_0_				 
				*/
				Query query1 = em.createQuery("SELECT DISTINCT TRIM(SUBSTRING(s.name, LOCATE('i', s.name))) AS location FROM Stock s ORDER BY location ");
				/*
				    Description:
				    	In this query, LOCATE('i', s.name) + 1 is used to find the position of the first occurrence of 'i' in the "name" field and then add 1 to that position. 
				    	The SUBSTRING function extracts a substring starting from the position after the first occurrence of 'i', effectively excluding the 'i' character itself. 
				    	The TRIM function is applied to remove any leading or trailing spaces. 
				    	This query will return a substring that includes the characters immediately following the 'i' character in the "name" field.

					=======================================================================================================================================================================================================================================
					
					Hibernate: 
					    select
					        distinct trim(substring(stock0_.name,
					        locate('i',
					        stock0_.name)+1)) as col_0_0_ 
					    from
					        stock stock0_ 
					    order by
					        col_0_0_				 
				*/
				Query query2 = em.createQuery("SELECT DISTINCT TRIM(SUBSTRING(s.name, LOCATE('i', s.name)+1)) AS location FROM Stock s ORDER BY location ");
				List<String> resultList1 = query1.getResultList();
				resultList1.forEach(System.out::println);
				List<String> resultList2 = query2.getResultList();
				resultList2.forEach(System.out::println);				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
				emf.close();
			}			
		}
		
		
		
		
		
}
