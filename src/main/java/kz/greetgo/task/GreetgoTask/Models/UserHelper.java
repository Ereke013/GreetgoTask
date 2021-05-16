package kz.greetgo.task.GreetgoTask.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHelper {
    private Long id;
    private String firstName;
    private String lastName;
    private String ava_picture;
    private String email;
    private int age;
    private String password;
    private Long a_class_id;
}
