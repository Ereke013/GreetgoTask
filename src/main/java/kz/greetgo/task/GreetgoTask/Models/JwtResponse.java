package kz.greetgo.task.GreetgoTask.Models;


import java.io.Serializable;
import java.util.List;


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

    public JwtResponse() {
    }

    public JwtResponse(Long id, String email, String firstName, String lastName, String password, String ava_picture, int age, List<Roles> roles, Class aClass, String jwtToken) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.ava_picture = ava_picture;
        this.age = age;
        this.roles = roles;
        this.aClass = aClass;
        this.jwtToken = jwtToken;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAva_picture() {
        return ava_picture;
    }

    public void setAva_picture(String ava_picture) {
        this.ava_picture = ava_picture;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    //    public JwtResponse(String jwtToken){
//        this.jwtToken = jwtToken;
//    }

    public String getJwtToken(){
        return this.jwtToken;
    }


}
