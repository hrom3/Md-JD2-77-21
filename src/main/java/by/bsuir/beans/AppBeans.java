package by.bsuir.beans;

import by.bsuir.domain.Cars;
import by.bsuir.domain.User;
import by.bsuir.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.sql.Date;

@Configuration
public class AppBeans {

    @Bean
    public StringUtil stringUtil() {
        return new StringUtil();
    }

    @Bean
    public Cars car1() {
        return Cars.builder()
                .id(50L)
                .owner(null)
                .model("S6 AllRoad")
                .productionDate(new Date(System.currentTimeMillis()))
                .name("AUDI")
                .price(89_659)
                .dealerId(20L)
                .build();
    }

    @Bean
    @Primary
    public Cars car2() {
        return Cars.builder()
                .id(51L)
                .owner(null)
                .model("Model S P100")
                .productionDate(new Date(System.currentTimeMillis()))
                .name("Tesla")
                .price(156_000)
                .dealerId(1L)
                .build();
    }

    @Bean
    public User user1(Cars car) {
        return new User(car);
    }

    @Bean
    public User user2(Cars car) {
        return new User(car);
    }
}
