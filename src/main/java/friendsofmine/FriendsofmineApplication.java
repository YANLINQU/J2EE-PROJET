package friendsofmine;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class FriendsofmineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendsofmineApplication.class, args);
		System.out.println("Hello Spring!");
	}

}
