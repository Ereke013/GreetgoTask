package kz.greetgo.task.GreetgoTask.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {
    private static final Long serialVersionUID = 123456789L;

    private String email;
    private String password;
}
