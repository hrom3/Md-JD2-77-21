package by.bsuir.beans;

import by.bsuir.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {

    @Bean
    public StringUtil stringUtil() {
        return new StringUtil();
    }
}
