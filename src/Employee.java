import java.util.*;

public class Employee {
    private String name;
    private int age;
    private double salary;
    private List<Employee> employeeList;
    private Set<Employee> employeeSet;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.employeeList = new ArrayList<Employee>();
        this.employeeSet = new HashSet<Employee>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        Collections.sort(employeeList, (e1, e2) -> e1.getName().compareTo(e2.getName()));
    }

//    public int CompareEmployees(Employee e1, Employee e2){
//        return e1.getName().compareTo(e2.getName());
//    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }
}
