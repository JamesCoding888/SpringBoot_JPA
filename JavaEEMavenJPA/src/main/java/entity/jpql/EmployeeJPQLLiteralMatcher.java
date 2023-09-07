package entity.jpql;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import java.util.List;

public class EmployeeJPQLLiteralMatcher extends TypeSafeMatcher<List<EmployeeJPQLLiteral>> {
    
	private final List<EmployeeJPQLLiteral> expectedItems;

    public EmployeeJPQLLiteralMatcher(List<EmployeeJPQLLiteral> expectedItems) {
        this.expectedItems = expectedItems;
    }

    @Override
    protected boolean matchesSafely(List<EmployeeJPQLLiteral> actualItems) {
        if (expectedItems.size() != actualItems.size()) {
            return false; // Check if the sizes are the same
        }
        
        for (int i = 0; i < expectedItems.size(); i++) {
            if (!expectedItems.get(i).equals(actualItems.get(i))) {
                return false; // Check if the elements at each position are equal
            }
        }
        
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("list containing elements in the same order as ").appendValue(expectedItems);
    }

    public static Matcher<List<EmployeeJPQLLiteral>> containsInOrder(List<EmployeeJPQLLiteral> expectedItems) {
        return new EmployeeJPQLLiteralMatcher(expectedItems);    
    }
}
