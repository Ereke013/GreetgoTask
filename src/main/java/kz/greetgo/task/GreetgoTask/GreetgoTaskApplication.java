package kz.greetgo.task.GreetgoTask;

import kz.greetgo.task.GreetgoTask.Models.Users;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MappedTypes(Users.class)
//@MapperScan("kz.greetgo.task.GreetgoTask.mapper")
@SpringBootApplication
public class GreetgoTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetgoTaskApplication.class, args);
	}

}
