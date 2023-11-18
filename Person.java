/**
 * @author Kamran Awan
 * This class is for managing the people
 */
public class Person
{
    private final String name;
    private int seniority;

    /**
     * This constructor inputs the data for the person
     * @param name name of the person
     * @param seniority seniority of the person
     */
    public Person(String name, int seniority)
    {
        this.name = name;
        this.seniority = seniority;
    }

    /**
     * This method returns the name of the person
     * @return name of the person
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method returns the seniority of the person
     * @return seniority of the person
     */
    public int getSeniority()
    {
        return seniority;
    }

    /**
     * This method inputs the seniority for the person
     * @param seniority seniority of the person
     */
    public void setSeniority(int seniority)
    {
        this.seniority = seniority;
    }
}
