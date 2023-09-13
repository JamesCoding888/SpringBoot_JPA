package controller.subquery;
import service.subquery.ProfileServices;

public class ProfileController {

	public static void main(String[] args) {
		
		// Create
//		ProfileServices.create();
		
		// Insert
//		ProfileServices.insertProfile();
//		ProfileServices.insertJobInfo();
		
		// Query
		ProfileServices.queryProfilesWithMoreThanNumericNumbers();
		ProfileServices.queryProfilesWithSalaryUnderAverage();
		ProfileServices.queryProfilesWithSalaryAboveAverage();
		ProfileServices.queryAverageSalaryByDepartment();
		ProfileServices.queryDepartmentsWithAboveAverageSalary();
		ProfileServices.queryProfilesWithMatchingJobInfo();
		ProfileServices.queryProfilesWithNotMatchingJobInfo();
	}
}
