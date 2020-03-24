package caleb.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@SpringBootApplication 선언만으로도 대부분의 설정이 자동으로 이루어짐.
public class Applications {
    public static void main(String[] args) {
        SpringApplication.run(Applications.class, args);
    }
}
