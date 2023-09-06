package entity.jpql.where;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import java.util.List;

public class EmployeeWhereLiteralMatcher extends TypeSafeMatcher<List<EmployeeWhereLiteral>> {
    
	private final List<EmployeeWhereLiteral> expectedItems;

    public EmployeeWhereLiteralMatcher(List<EmployeeWhereLiteral> expectedItems) {
        this.expectedItems = expectedItems;
    }

    @Override
    protected boolean matchesSafely(List<EmployeeWhereLiteral> actualItems) {
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

    public static Matcher<List<EmployeeWhereLiteral>> containsInOrder(List<EmployeeWhereLiteral> expectedItems) {
        return new EmployeeWhereLiteralMatcher(expectedItems);    
    }
}
