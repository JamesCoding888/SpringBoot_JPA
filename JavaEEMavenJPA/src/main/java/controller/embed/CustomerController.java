package controller.embed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import service.embed.CustomerServices;

public class CustomerController {
	public static void main(String[] args) {
		
		// Create
//		CustomerServices.create("mydb");
		
		// Insert
//		CustomerServices.insertCustomerEmbeddedCustomerItemQtyMap();
//		CustomerServices.insertCustomer();
		
		// Query
//		CustomerServices.queryCustomerEmbeddedCustomerItemQty();
//		CustomerServices.queryTuple();
//		CustomerServices.queryKEY();
//		CustomerServices.queryBETWEENByNumber();
//		CustomerServices.queryBETWEENByDate();
//		CustomerServices.queryNOTBETWEENByDate();
//		CustomerServices.queryIN();
//		CustomerServices.queryIN4BindingVariable();
//		CustomerServices.queryLIKEWithWildCard();
//		CustomerServices.queryNOTLIKEWithWildCard();
//		CustomerServices.queryLIKEWithEscapeWildCard();
//		CustomerServices.queryNOTLIKEWithEscapeWildCard();
//		CustomerServices.queryLIKEWithWildCard02();
		CustomerServices.queryLIKEWithWildCard03();
		CustomerServices.queryLIKEWithWildCard04();
	}
	
}
