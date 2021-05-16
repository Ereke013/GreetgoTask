package kz.greetgo.task.GreetgoTask.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 987654321L;


    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String ava_picture;
    private int age;
    private List<Roles> roles;
    private Class aClass;
    private String jwtToken;

//    public JwtResponse(String jwtToken){
//        this.jwtToken = jwtToken;
//    }

    public String getJwtToken(){
        return this.jwtToken;
    }


}
