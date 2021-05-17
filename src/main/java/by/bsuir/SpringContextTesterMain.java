package by.bsuir;

import by.bsuir.domain.Cars;
import by.bsuir.domain.Dealer;
import by.bsuir.domain.User;
import by.bsuir.repository.IDealerRepository;
import by.bsuir.repository.IUserRepository;
import by.bsuir.repository.impl.UserRepositoryImpl;
import by.bsuir.service.UserService;
import by.bsuir.util.StringUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.Collectors;

public class SpringContextTesterMain {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlContext =
                new ClassPathXmlApplicationContext("classpath:application-context.xml");

        User user1 = xmlContext.getBean("user1", User.class);
        User user2 = (User) xmlContext.getBean("user2");
        System.out.println(user1);
        System.out.println(user1.getUserCar());
        System.out.println(user2);
        System.out.println(user2.getUserCar());

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("by.bsuir");

        UserService userService = annotationConfigApplicationContext.getBean("userServiceImpl", UserService.class);

        System.out.println(userService.findAll()
                .stream()
                .map(User::getSurname)
                .collect(Collectors.joining(", ")));

        IUserRepository userRepository = annotationConfigApplicationContext.getBean(UserRepositoryImpl.class);
//       IUserRepository userRepository = annotationConfigApplicationContext.
//                getBean("userRepository", IUserRepository.class);

        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }

        IDealerRepository dealerRepository = annotationConfigApplicationContext.getBean("dealerRepository", IDealerRepository.class);

        for (Dealer dealer : dealerRepository.findAll()) {
            System.out.println(dealer);
        }

        System.out.println(annotationConfigApplicationContext.getBean(StringUtil.class).concat("Ja", "va"));
        System.out.println(annotationConfigApplicationContext.getBean("stringUtil", StringUtil.class).concat("Sa", "sha"));

        Cars genCar = annotationConfigApplicationContext.getBean(Cars.class);
        System.out.println(genCar);

        System.out.println(userRepository.findAll().stream()
                .map(User::getName)
                .collect(Collectors.joining(", ")));
    }
}
