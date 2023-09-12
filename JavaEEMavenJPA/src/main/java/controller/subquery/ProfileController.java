package controller.subquery;
import service.subquery.ProfileServices;

public class ProfileController {

	public static void main(String[] args) {
		
		// Create
		ProfileServices.create();
		
		// Insert
		ProfileServices.insert();
		
		// Query
		ProfileServices.queryProfilesWithMoreThanNumericNumbers();
		ProfileServices.queryProfilesWithSalaryUnderAverage();
		ProfileServices.queryProfilesWithSalaryAboveAverage();
	}
}
