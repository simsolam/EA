package edu.mum.cs544.a;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

public class AppDepartmentEmployee {
    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department department = new Department("accounting");
        Employee employee1 = new Employee("062341234", "Frank Brown");
        Employee employee2 = new Employee("064362738", "John Doe");

        department.addEmployee(employee1);
        department.addEmployee(employee2);
        em.persist(department);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        Collection<Department> departmentList = em.createQuery("from Department").getResultList();
        for (Department dept : departmentList) {
            System.out.println("department name = " + dept.getName());
            for (Employee empl : dept.getEmployeeList()) {
                System.out.println("employee name= " + empl.getName());
            }
        }
        System.out.println("---- now the reverse -----");
        @SuppressWarnings("unchecked")
        Collection<Employee> employeeList = em.createQuery("from Employee").getResultList(); //Only one query, Eager Loading by default
        for (Employee empl : employeeList) {
            System.out.println("employee name= " + empl.getName());
            System.out.println("department name = " + empl.getDepartment().getName());
        }

        em.getTransaction().commit();
        em.close();
    }

}
