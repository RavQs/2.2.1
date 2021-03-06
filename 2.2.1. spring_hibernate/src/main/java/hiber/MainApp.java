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
        Car honda = new Car("Honda", 1);
        Car hyundai = new Car("Huindai", 2);
        Car kia = new Car("Kia", 3);
        Car toyota = new Car("Toyota", 4);

        carService.add(honda);
        carService.add(hyundai);
        carService.add(kia);
        carService.add(toyota);


        UserService userService = context.getBean(UserService.class);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", honda));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", hyundai));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", kia));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", toyota));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel());
            System.out.println();
        }

        User searchForUser = userService.searchUserByCar(kia.getModel(), kia.getSeries()).get(0);
        System.out.println("User's Id = " + searchForUser.getId());
        System.out.println("User's Name = " + searchForUser.getFirstName());
        System.out.println("User's Last Name = " + searchForUser.getLastName());
        System.out.println("Users Car = " + searchForUser.getCar().getModel());

        context.close();
    }
}
