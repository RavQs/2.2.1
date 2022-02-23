package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      Car honda = new Car("Honda",222);
      Car hyundai = new Car("Huindai",333);
      Car kia = new Car("Kia", 1);
      Car Toyota = new Car("Toyota", 2);
      carService.add(honda);
      carService.add(hyundai);
      carService.add(kia);
      carService.add(Toyota);


      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",honda));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",hyundai));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",kia));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",Toyota));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      context.close();
   }
}
