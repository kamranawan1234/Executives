import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Kamran Awan
 * This class is for managing the departments
 */
public class Department
{
    private final String name;
    Queue<Person> people;

    /**
     * This constructor is used to create a new department based on the name
     * @param name name of the department
     */
    public Department(String name)
    {
        this.name = name;
        this.people = new LinkedList<>();
    }

    /**
     * This method returns the name of the department
     * @return name
     */
    public String getName()
    {
        return name;
    }
}