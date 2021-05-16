package kz.greetgo.task.GreetgoTask.web.mapper;

import kz.greetgo.task.GreetgoTask.Controller.TestController;
import kz.greetgo.task.GreetgoTask.Models.Roles;
import kz.greetgo.task.GreetgoTask.Models.UserHelper;
import kz.greetgo.task.GreetgoTask.Models.Users;
import kz.greetgo.task.GreetgoTask.mapper.UsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {
    @Test
    void TestingNullUserWithUserShouldReturnNotEquals(){
        TestController controller = new TestController();
        UserHelper helper = new UserHelper();
        UserHelper helper2 = controller.first();
        assertNotEquals(helper, helper2);
    }

    @Test
    void TestingFullUserDataWithUserShouldReturnEquals(){
        TestController controller = new TestController();
        UserHelper helper = new UserHelper();
        helper.setFirstName("Tester");
        helper.setLastName("Testerov");
        helper.setEmail("test@gmail.com");
        helper.setAge(14);
        helper.setA_class_id(1L);
        UserHelper helper2 = controller.first();
        assertEquals(helper, helper2);
    }

}
