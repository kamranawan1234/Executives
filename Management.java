import java.util.*;

/**
 * @author Kamran Awan
 * This class is used for managing the program
 */
public class Management
{
    Map<String, Department> map = new HashMap<>();
    Queue<Person> unemployed = new LinkedList<>();

    /**
     * This method gets the person based on their name
     * @param name name of the person
     * @return person or null
     */
    public Person getPersonByName(String name)
    {
        for (Person person : unemployed)
        {
            if (person.getName().equals(name))
            {
                unemployed.remove(person);
                return person;
            }
        }
        return null;
    }

    /**
     * This method removes the person from their department
     * @param name name of the person
     * @return person or null
     */
    public Person removePersonFromDepartment(String name)
    {
        for (Department department : map.values())
        {
            for (Person person : department.people)
            {
                if (person.getName().equals(name))
                {
                    department.people.remove(person);
                    return person;
                }
            }
        }
        return null;
    }

    /**
     * This method displays the payroll of the person based on their department
     */
    private void displayPayroll()
    {
        for (Department department : map.values())
        {
            System.out.println("Department: " + department.getName());
            List<Person> sortedPeople = new ArrayList<>(department.people);

            for (Person person : sortedPeople)
            {
                System.out.println(person.getName() + ": $" + calculateSalary(person.getName()));
            }
        }
    }

    /**
     * This method calculates the salary of the person
     * @param name name of the person
     * @return salary or -1
     */
    private int calculateSalary(String name)
    {
        for (Department department : map.values())
        {
            for (Person person : department.people)
            {
                if (person.getName().equals(name))
                {
                    int salary = 40000 + 5000 * countSubordinates(person, department.people);
                    person.setSeniority(person.getSeniority() + 1);
                    return salary;
                }
            }
        }
        return -1;
    }

    /**
     * This method gets the count of the person
     * @param person name of the person
     * @param people people in the department
     * @return count
     */
    private static int countSubordinates(Person person, Queue<Person> people)
    {
        int count = 0;
        for (Person person2 : people)
        {
            if (person2.getSeniority() < person.getSeniority())
            {
                count++;
            }
        }
        return count;
    }

    /**
     * This method adds a department
     * @param departmentName name of the department
     */
    public void add(String departmentName)
    {
        Department department = new Department(departmentName);
        map.put(departmentName, department);
        System.out.println("Department " + departmentName + " added.");
    }

    /**
     * This method adds a person
     * @param personName name of the person
     */
    public void hire(String personName)
    {
        Person person = new Person(personName, 0);
        unemployed.add(person);
        System.out.println("Person " + personName + " hired.");
    }

    /**
     * This method adds a person to a department
     * @param personName name of the person
     * @param departmentName name of the department
     */
    public void join(String personName, String departmentName)
    {
        Person person = getPersonByName(personName);

        if (person != null)
        {
            Department department = map.get(departmentName);
            if (department != null)
            {
                department.people.add(person);
                System.out.println(personName + " joined " + departmentName + " department.");
            }
            else
            {
                System.out.println(departmentName + " was not found");
            }
        }
    }

    /**
     * This method makes a person leave a department
     * @param personName name of the person
     */
    public void quit(String personName)
    {
        Person person = removePersonFromDepartment(personName);
        if (person != null)
        {
            unemployed.add(person);
            System.out.println(personName + " quit the department.");
        }
        else
        {
            System.out.println("Person " + personName + " not found in any department.");
        }
    }

    /**
     * This method changes a person between one department to another
     * @param personName name of the person
     * @param departmentName name of the department
     */
    public void change(String personName, String departmentName)
    {
        Person person = removePersonFromDepartment(personName);

        if (person != null)
        {
            Department department = map.get(departmentName);
            if (department != null)
            {
                department.people.add(person);
                System.out.println(personName + " moved to " + departmentName + " department.");
            }
            else
            {
                System.out.println("Department " + departmentName + " not found.");
            }
        }
        else
        {
            System.out.println("Person " + personName + " not found in any department.");
        }
    }

    /**
     * This method handles the input for the program
     */
    public void input()
    {
        boolean ended = false;
        Scanner input = new Scanner(System.in);

        while (!ended)
        {
            System.out.println("Commands: ");
            System.out.println("Add <department> - Creates a new department in queue");
            System.out.println("Hire <person> - Creates a new executive");
            System.out.println("Join <person> <department> - <person> is added to <department>");
            System.out.println("Quit <person> - <person> is removed from his or her department");
            System.out.println("Change <person> - <department> <person> is moved from old department to <department>");
            System.out.println("Payroll - Each executive’s salary is computed and displayed by department in decreasing order of seniority");
            System.out.println("Salary <person> - Gets the salary of a specific person");
            System.out.println("Exit – Ends the program");

            String nextInput = input.next().toLowerCase();

            if (nextInput.equals("add"))
            {
                System.out.println("Enter the name of the department: ");
                add(input.next());
            }
            else if (nextInput.equals("hire"))
            {
                System.out.println("Enter the name of the person: ");
                hire(input.next());
            }
            else if (nextInput.equals("join"))
            {
                System.out.println("Enter the name of the department: ");
                String departmentName = input.next();

                System.out.println("Enter the name of the person: ");
                String personName = input.next();

                join(personName, departmentName);
            }
            else if (nextInput.equals("quit"))
            {
                System.out.println("Enter the name of the person: ");
                quit(input.next());
            }
            else if (nextInput.equals("change"))
            {
                System.out.println("Enter the name of the department: ");
                String departmentName = input.next();

                System.out.println("Enter the name of the person: ");
                String personName = input.next();

                change(personName, departmentName);
            }
            else if (nextInput.equals("payroll"))
            {
                displayPayroll();
            }
            else if (nextInput.equals("salary"))
            {
                System.out.println("Enter the name of the person: ");
                String personName = input.next();

                int salary = calculateSalary(personName);
                if (salary != -1)
                {
                    System.out.println("Salary of " + personName + ": $" + salary);
                } else
                {
                    System.out.println("Person " + personName + "' not found.");
                }
            }
            else if (nextInput.equals("exit"))
            {
                ended = true;
            }
            else
            {
                System.out.println("Incorrect command inputted");
            }
        }
    }

    /**
     * main method
     */
    public static void main(String[] args)
    {
        Management tester = new Management();
        tester.input();
    }
}
