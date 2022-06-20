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

      User userOne = new User("User1", "Lastname1", "user1@mail.ru");
      User userTwo = new User("User2", "Lastname2", "user2@mail.ru");
      User userThree = new User("User3", "Lastname3", "user3@mail.ru");
      User userFour = new User("User4", "Lastname4", "user4@mail.ru");

      Car carOne = new Car("model1", "series1");
      Car carTwo = new Car("model2", "series2");
      Car carThree = new Car("model3", "series3");
      Car carFour = new Car("model4", "series4");

      userOne.setCar(carOne);

      userService.add(userOne);

      userService.add(userTwo, carTwo);
      userService.add(userThree, carThree);
      userService.add(userFour, carFour);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         //Car
         System.out.println("model = " + user.getCar().getModel());
         System.out.println("series = " + user.getCar().getSeries());
         System.out.println();
      }

      User userResult = userService.getUserfromMS("model1", "series1");
      System.out.println(userResult);

      context.close();
   }
}
