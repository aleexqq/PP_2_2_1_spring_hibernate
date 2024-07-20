package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", null);
      userService.add(user1);
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Audi", 2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Mercedes", 3)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("Toyota", 5)));

      user1.setCar(new Car("BMW", 1));
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ (user.getCar() == null ? "null" : user.getCar().getModel() + " " + user.getCar().getSeries()));
         System.out.println();
      }

      List<User> usersByCar = userService.getUsersByCar("BMW", 1);
      for (User user : usersByCar) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Car = "+user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }
      context.close();
   }
}
