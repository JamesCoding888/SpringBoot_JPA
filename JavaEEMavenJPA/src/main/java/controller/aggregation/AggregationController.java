package controller.aggregation;

import service.aggregation.PurchasedOrderServices;

public class AggregationController {
	public static void main(String[] args) {
		
		// Create
//		PurchasedOrderServices.create("mydb");
		
		// Insert
//		PurchasedOrderServices.insert();
		
		// Query
		PurchasedOrderServices.queryByCOUNT();		
		PurchasedOrderServices.queryByAVG();
		PurchasedOrderServices.queryByMAX();
		PurchasedOrderServices.queryByMIN();
		PurchasedOrderServices.queryBySUM();
		PurchasedOrderServices.queryByGROUPBY();
		PurchasedOrderServices.queryByAvgGROUPBY();
		PurchasedOrderServices.queryByCountGROUPBY();
		PurchasedOrderServices.queryByMaxGROUPBY();
		PurchasedOrderServices.queryBySumGROUPBY();		
		PurchasedOrderServices.queryByHavingMaxGROUPBY();
		PurchasedOrderServices.queryByHavingMaxGROUPBYOrderBy();
	}
	
}
