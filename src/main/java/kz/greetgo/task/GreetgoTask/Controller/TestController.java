package kz.greetgo.task.GreetgoTask.Controller;

import kz.greetgo.task.GreetgoTask.Models.UserHelper;
import kz.greetgo.task.GreetgoTask.Models.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserHelper first(){
        UserHelper users = new UserHelper();
        users.setFirstName("Tester");
        users.setLastName("Testerov");
        users.setEmail("test@gmail.com");
        users.setAge(14);
        users.setA_class_id(1L);
        return users;
    }
}
