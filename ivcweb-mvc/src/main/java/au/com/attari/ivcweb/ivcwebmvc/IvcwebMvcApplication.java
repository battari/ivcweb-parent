package au.com.attari.ivcweb.ivcwebmvc;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class IvcwebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(IvcwebMvcApplication.class, args);
    }

}
