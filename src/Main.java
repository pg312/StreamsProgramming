import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));

        empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
        empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));

        System.out.println("Group employees by city");
        Map<String, List<Employee>> employeesByCity = empList.stream().collect(Collectors.groupingBy(e -> e.getCity()));
        System.out.println(employeesByCity);

        System.out.println("Group employees by age");
        Map<Integer, List<Employee>> employeesByAge = empList.stream().collect(Collectors.groupingBy(e -> e.getAge()));
        System.out.println(employeesByAge);

        System.out.println("Count of male and female employees");
        Map<String, Long> countByGender = empList.stream().collect(Collectors.groupingBy(e -> e.getGender(), Collectors.counting()));
        System.out.println(countByGender);

        System.out.println("All Departments");
        empList.stream().map(e -> e.getDeptName()).distinct().forEach(System.out::println);

        System.out.println("Age greater than 28");
        empList.stream().filter(e -> e.getAge() > 28).forEach(System.out::println);

        System.out.println("Max age of employees");
        Optional<Employee> maxAge = empList.stream().max(Comparator.comparing(e-> e.getAge()));
        System.out.println(maxAge.get());

        System.out.println("Average age of Male and Female employees");
        Map<String, Double> averageAgeByGender  = empList.stream().collect(Collectors.groupingBy(e-> e.getGender(),Collectors.averagingInt(e-> e.getAge())));
        System.out.println(averageAgeByGender);

        System.out.println("No of employees in each department");
        Map<String, Long> noOfEmployeesInEachDept = empList.stream().collect(Collectors.groupingBy(e->e.getDeptName(),Collectors.counting()));
        System.out.println(noOfEmployeesInEachDept);


        System.out.println("Youngest Female employee");
        Optional<Employee> youngestFemaleEmployee = empList.stream().filter(e -> Objects.equals(e.getGender(), "F")).min(Comparator.comparing(e -> e.getAge()));
        System.out.println(youngestFemaleEmployee);

        System.out.println("Employees whose age is greater than 30 and less than 30");
        Map<Boolean, List<Employee>> ageGreaterThan30AndLessThan30 = empList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 30));
        System.out.println(ageGreaterThan30AndLessThan30);

        System.out.println("Department has highest number of employees");
        Optional<Map.Entry<String, Long>> departmentWithMaxEmployees = empList.stream().collect(Collectors.groupingBy(e -> e.getDeptName(), Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue());
        System.out.println(departmentWithMaxEmployees);

        System.out.println("Any employee from HR department");
        Optional<Employee> anyEmployeeFromHRDept = empList.stream().filter(e -> e.getDeptName() == "HR").findAny();
        System.out.println(anyEmployeeFromHRDept);

        System.out.println("Departments with greater than 3 employees");
        List<Map.Entry<String, Long>> departmentWithGreaterThan3Employees = empList.stream().collect(Collectors.groupingBy(e -> e.getDeptName(), Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > 3).collect(Collectors.toList());
        System.out.println(departmentWithGreaterThan3Employees);

        System.out.println("All the employees lives in Blore city");
        List<Employee> employeesLivesInBlore = empList.stream().filter(e -> e.getCity().equals("Blore")).sorted(Comparator.comparing(e -> e.getName())).collect(Collectors.toList());
        System.out.println(employeesLivesInBlore);

        System.out.printf("Total no.of employees in the Organization: %d\n",employeesByAge.size());

        System.out.println("Sort by Name and Age");
        Comparator<Employee> nameSort = Comparator.comparing(e-> e.getName());
        Comparator<Employee> ageSort = Comparator.comparing(e-> e.getAge());
        List<Employee> sortByNameAndAge = empList.stream().sorted(nameSort.thenComparing(ageSort)).collect(Collectors.toList());
        System.out.println(sortByNameAndAge);

        System.out.println("Highest experienced employee in the Organization");
        Optional<Employee> highestExperiencedEmployee = empList.stream().min(Comparator.comparing(e -> e.getYearOfJoining()));
        System.out.println(highestExperiencedEmployee);

        System.out.println("Total Salary of the Organization");
        Long totalSalary = empList.stream().map(e -> e.getSalary()).reduce(0L, (a, b) -> a + b);
        System.out.println(totalSalary);

        System.out.println("Average salary of each department");
        Map<String, Double> averageSalaryOfEachDepartment = empList.stream().collect(Collectors.groupingBy(e -> e.getDeptName(), Collectors.averagingDouble(e -> e.getSalary())));
        System.out.println(averageSalaryOfEachDepartment);

        System.out.println("Highest salary in the Organization");
        Optional<Employee> maxSalary = empList.stream().max(Comparator.comparing(e -> e.getSalary()));
        System.out.println(maxSalary);

        System.out.println("Second highest salary in the Organization");
        Optional<Employee> secondHighestSalary = empList.stream().sorted(Comparator.comparing((Employee e) -> e.getSalary()).reversed()).skip(1).findFirst();
        System.out.println(secondHighestSalary);

        System.out.println("Highest paid salary based on gender");
        Map<String, Optional<Employee>> highestPaidBasedOnGender = empList.stream().collect(Collectors.groupingBy(e -> e.getDeptName(), Collectors.maxBy((a, b) -> (int) (a.getSalary() - b.getSalary()))));
        System.out.println(highestPaidBasedOnGender);

        System.out.println("Sorting employees salary in ascending order");
        List<Long> salariesSortedInAscen = empList.stream().map(e -> e.getSalary()).sorted().collect(Collectors.toList());
        System.out.println(salariesSortedInAscen);

        System.out.println("Sorting employees salary in descending order");
        List<Long> salariesInDescendingOrder = empList.stream().map(e -> e.getSalary()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(salariesInDescendingOrder);

    }
}