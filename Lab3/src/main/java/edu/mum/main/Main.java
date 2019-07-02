package edu.mum.main;


import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.User;
import edu.mum.service.UserService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:context/applicationContext.xml");

        UserService userService = (UserService) context.getBean("userServiceImpl");
//         public User(String firstName, String lastName, String email, int rating, boolean admin, int version, Date lastLogin) {
        User u = new User("John", "Doe", "jdoe@mum.edu", 12, true, 1, new Date());
        userService.save(u);

        User user = userService.findByEmail("jdoe@mum.edu");
        System.out.println("*************  User ***************");
        System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());


        System.out.println("        *********  User Merge Test **********");

        // Now test merge return value
        user.setEmail("Lotta@Doe.com");
        User mergeResult = userService.update(user);
        user.setEmail("xxx.com");

        try {
            mergeResult = userService.update(user);
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println();
            System.out.println("State object exception - Need to use RETURN value from update/merge");
        }

        System.out.println();
        System.out.println("        *********  User Flush/Refresh Test **********");

        user.setId(null);
        try {
            userService.testRefresh(user);
        } catch (Exception e) {
            System.out.println();
            System.out.println("All Done");

        }
    }

}