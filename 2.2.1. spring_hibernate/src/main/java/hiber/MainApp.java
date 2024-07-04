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

      Car car1 = new Car("Toyota", 123);
      Car car2 = new Car("Honda", 456);
      Car car3 = new Car("Ford", 789);

      userService.add(new User("User1", "user1@mail.ru", car1));
      userService.add(new User("User2", "user2@mail.ru", car2));
      userService.add(new User("User3", "user3@mail.ru", car3));
      userService.add(new User("User4", "user4@mail.ru", null)); // Example without car

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("Name = " + user.getName()); // Assuming 'name' is stored in the database
         System.out.println("Email = " + user.getEmail());

         Car userCar = user.getCar();
         if (userCar != null) {
            System.out.println("Car: " + userCar.getModel() + " " + userCar.getSeries());
         } else {
            System.out.println("Car: No car registered");
         }

         System.out.println();
      }

      context.close();
   }
}