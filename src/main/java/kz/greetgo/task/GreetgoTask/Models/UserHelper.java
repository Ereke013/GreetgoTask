package kz.greetgo.task.GreetgoTask.Models;


import javax.persistence.*;
import java.util.List;


public class UserHelper {
    private Long id;
    private String firstName;
    private String lastName;
    private String ava_picture;
    private String email;
    private int age;
    private String password;
    private Long a_class_id;

    public UserHelper() {
    }

    public UserHelper(Long id, String firstName, String lastName, String ava_picture, String email, int age, String password, Long a_class_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ava_picture = ava_picture;
        this.email = email;
        this.age = age;
        this.password = password;
        this.a_class_id = a_class_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAva_picture() {
        return ava_picture;
    }

    public void setAva_picture(String ava_picture) {
        this.ava_picture = ava_picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getA_class_id() {
        return a_class_id;
    }

    public void setA_class_id(Long a_class_id) {
        this.a_class_id = a_class_id;
    }
}
