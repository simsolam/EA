package edu.mum.cs544.f;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppF_Office {

	private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department department = new Department("accounting");
        Employee employee1 = new Employee("Frank Brown", "062341234");
        Employee employee2 = new Employee("John Doe", "064362738");
        Office office = new Office("M290", "McLaughlin");

        department.addEmployee(employee1);
        department.addEmployee(employee2);

        employee1.setOffice(office);
        employee2.setOffice(office);
        em.persist(department);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        Collection<Department> departmentList = em.createQuery("from FDepartment").getResultList();
        for (Department dept : departmentList) {
            System.out.println("department name = " + dept.getName());
            for (Employee empl : dept.getEmployeeList()) {
                System.out.println("employee name= " + empl.getName());
                System.out.println("Office Location: " + empl.getOffice().getRoomnumber()
                        + " in building: " + empl.getOffice().getBuilding());
            }
        }
        System.out.println("---- now the reverse -----");
        @SuppressWarnings("unchecked")
        Collection<Employee> employeeList = em.createQuery("from FEmployee").getResultList();
        for (Employee empl : employeeList) {
            System.out.println("employee name= " + empl.getName());
            System.out.println("department name = " + empl.getDepartment().getName());
            System.out.println("Office Location: " + empl.getOffice().getRoomnumber()
                    + " in building: " + empl.getOffice().getBuilding());
        }

        em.getTransaction().commit();
        em.close();
    }
}
