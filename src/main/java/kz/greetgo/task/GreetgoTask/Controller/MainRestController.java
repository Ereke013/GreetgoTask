package kz.greetgo.task.GreetgoTask.Controller;

import kz.greetgo.task.GreetgoTask.Models.*;
import kz.greetgo.task.GreetgoTask.Models.Class;
import kz.greetgo.task.GreetgoTask.mapper.ClassMapper;
import kz.greetgo.task.GreetgoTask.mapper.RoleMapper;
import kz.greetgo.task.GreetgoTask.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api")
public class MainRestController {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ClassMapper classMapper;

//    @PostMapping("/login")
//    public ResponseEntity<?> auth(@RequestBody Request request) {
//        UserHelper helperUsers = usersMapper.userByEmail(request.getEmail());
//        if (helperUsers != null) {
//            if (new BCryptPasswordEncoder().matches(request.getPassword(), helperUsers.getPassword())) {
//
//                Users user = new Users();
//                user.setId(helperUsers.getId());
//                user.setPassword(helperUsers.getPassword());
//                user.setAge(helperUsers.getAge());
//                user.setAva_picture(helperUsers.getAva_picture());
//                user.setEmail(helperUsers.getEmail());
//                user.setFirstName(helperUsers.getFirstName());
//                user.setLastName(helperUsers.getLastName());
//
//                List<Long> userRolesId = usersMapper.rolesId(helperUsers.getId());
//                List<Roles> userRoles = new ArrayList<>();
//                for (Long usId : userRolesId) {
//                    Roles role = roleMapper.getRole(usId);
//                    userRoles.add(role);
//
//                }
//                user.setRoles(userRoles);
//                user.setAClass(classMapper.getClassById(helperUsers.getA_class_id()));
//                System.out.println("corrrreect");
//                return new ResponseEntity<>(user, HttpStatus.OK);
//            }
//            System.out.println("User password is incorrect");
//            return ResponseEntity.badRequest().body("Password is incorrect");
//        }
//        System.out.println("User is not found");
//        return ResponseEntity.badRequest().body("User Is not Found");
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Users users) {
//        System.out.println("users: " + users);
//
//        String firstName = users.getFirstName();
//        String lastName = users.getLastName();
//        String ava_picture = users.getAva_picture();
//        String email = users.getEmail();
//        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
//        String password = users.getPassword();
//        Long a_class_id = users.getAClass().getId();
//        int age = users.getAge();
//
//        usersMapper.addUser(firstName, lastName, ava_picture, email, age, password, a_class_id);
//        UserHelper users1 = usersMapper.userByEmail(email);
//        usersMapper.addUserRole(users1.getId(), 2L);
//        return ResponseEntity.ok(users);
//    }

    @GetMapping("/allUsers") //checked
    public List<Users> getAll() {
        List<UserHelper> helperUsers = usersMapper.findAll();
        List<Users> users = new ArrayList<>();
        System.out.println("helperUsers");
        System.out.println(helperUsers);
        for (int i = 0; i < helperUsers.size(); i++) {
            Users user = new Users();
            user.setId(helperUsers.get(i).getId());
            user.setPassword(helperUsers.get(i).getPassword());
            user.setAge(helperUsers.get(i).getAge());
            user.setAva_picture(helperUsers.get(i).getAva_picture());
            user.setEmail(helperUsers.get(i).getEmail());
            user.setFirstName(helperUsers.get(i).getFirstName());
            user.setLastName(helperUsers.get(i).getLastName());

            List<Long> userRolesId = usersMapper.rolesId(helperUsers.get(i).getId());
            List<Roles> userRoles = new ArrayList<>();
            for (Long usId : userRolesId) {
                Roles role = roleMapper.getRole(usId);
                userRoles.add(role);

            }
            user.setRoles(userRoles);
            user.setAClass(classMapper.getClassById(helperUsers.get(i).getA_class_id()));
            users.add(user);
        }
        return users;
    }

    @PostMapping("/addUsers") //checked
    public ResponseEntity<?> addUser(@RequestBody UserHelper users) {

        System.out.println("users: " + users);

        String firstName = users.getFirstName();
        String lastName = users.getLastName();
        String ava_picture = users.getAva_picture();
        if(ava_picture.length()<1){
            ava_picture = "https://mpchsschool.in/wp-content/uploads/2019/10/default-profile-picture.png";
        }
        if(!testImage(ava_picture)){
            ava_picture="https://moonvillageassociation.org/wp-content/uploads/2018/06/default-profile-picture1.jpg";
        }
        String email = users.getEmail();
        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
        String password = users.getPassword();
        Long a_class_id = users.getA_class_id();
        int age = users.getAge();

        usersMapper.addUser(firstName, lastName, ava_picture, email, age, password, a_class_id);
        UserHelper users1 = usersMapper.userByEmail(email);
        usersMapper.addUserRole(users1.getId(), 2L);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/addClass") //checked
    public ResponseEntity<?> addClass(@RequestParam String class_name) {
        System.out.println("[className] : " + class_name);
        if (class_name != null) {
            classMapper.addClass(class_name);
            return new ResponseEntity<>(class_name, HttpStatus.OK);
        }
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/allUsers/{id}") //checked
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Long id) {
        UserHelper helperUsers = usersMapper.userById(id);
        if (helperUsers != null) {
            Users user = new Users();
            user.setId(helperUsers.getId());
            user.setPassword(helperUsers.getPassword());
            user.setAge(helperUsers.getAge());
            user.setAva_picture(helperUsers.getAva_picture());
            user.setEmail(helperUsers.getEmail());
            user.setFirstName(helperUsers.getFirstName());
            user.setLastName(helperUsers.getLastName());

            List<Long> userRolesId = usersMapper.rolesId(helperUsers.getId());
            List<Roles> userRoles = new ArrayList<>();
            for (Long usId : userRolesId) {
                Roles role = roleMapper.getRole(usId);
                userRoles.add(role);

            }
            user.setRoles(userRoles);
            user.setAClass(classMapper.getClassById(helperUsers.getA_class_id()));
            return ResponseEntity.ok(user);
        }
        System.out.println("User is not found");
        return ResponseEntity.badRequest().body("User Is not Found");
    }

    @DeleteMapping(value = "/deleteUser/{id}") //checked
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long id) {
        UserHelper userHelper = usersMapper.userById(id);
        if (userHelper != null) {
            usersMapper.deleteUser(id);
            return ResponseEntity.ok(userHelper);
        }
        return ResponseEntity.badRequest().body("User Not Found");
    }

    @PutMapping(value = "/updateUser/{id}") //checked
    public ResponseEntity<?> updateUser(@RequestBody Users users, @PathVariable(name = "id") Long id) {
        System.out.println("update users: " + users);

        String firstName = users.getFirstName();
        String lastName = users.getLastName();
        String ava_picture = users.getAva_picture();
        String email = users.getEmail();
        String password = users.getPassword();
        Long a_class_id = users.getAClass().getId();
        int age = users.getAge();

        usersMapper.updateUser(id, firstName, lastName, ava_picture, email, age, password, a_class_id);
//        UserHelper users1 = usersMapper.userByEmail(email);
//        usersMapper.addUserRole(users1.getId(), 2L);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/usersbyClass/{id}") //checked
    public ResponseEntity<?> getUsersByClass(@PathVariable(name = "id") Long id) {
        List<UserHelper> helperUsers = usersMapper.getUsersByClass(id);
        List<Users> users = new ArrayList<>();
        System.out.println("helperUsers in byClass");
        System.out.println(helperUsers);
        for (int i = 0; i < helperUsers.size(); i++) {
            Users user = new Users();
            user.setId(helperUsers.get(i).getId());
            user.setPassword(helperUsers.get(i).getPassword());
            user.setAge(helperUsers.get(i).getAge());
            user.setAva_picture(helperUsers.get(i).getAva_picture());
            user.setEmail(helperUsers.get(i).getEmail());
            user.setFirstName(helperUsers.get(i).getFirstName());
            user.setLastName(helperUsers.get(i).getLastName());

            List<Long> userRolesId = usersMapper.rolesId(helperUsers.get(i).getId());
            List<Roles> userRoles = new ArrayList<>();
            for (Long usId : userRolesId) {
                Roles role = roleMapper.getRole(usId);
                userRoles.add(role);

            }
            user.setRoles(userRoles);
            user.setAClass(classMapper.getClassById(helperUsers.get(i).getA_class_id()));
            users.add(user);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/allClass")
    public ResponseEntity<?> getAllClass() {
        List<Class> allClass = classMapper.findAll();
        return ResponseEntity.ok(allClass);
    }


    public Boolean testImage(String url){
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            //BufferedImage image = ImageIO.read(new URL("http://someimage.jpg"));
            if(image != null){
                return true;
            } else{
                return false;
            }

        } catch (MalformedURLException e) {
            System.err.println("URL error with image");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("IO error with image");
            e.printStackTrace();
            return false;
        }
    }

}


