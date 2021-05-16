package kz.greetgo.task.GreetgoTask.Controller;


import kz.greetgo.task.GreetgoTask.Models.Class;
import kz.greetgo.task.GreetgoTask.Models.JwtRequest;
import kz.greetgo.task.GreetgoTask.Models.JwtResponse;
import kz.greetgo.task.GreetgoTask.Models.UserHelper;
import kz.greetgo.task.GreetgoTask.Models.Users;
import kz.greetgo.task.GreetgoTask.jwt.JwtTokenGenerator;
import kz.greetgo.task.GreetgoTask.mapper.ClassMapper;
import kz.greetgo.task.GreetgoTask.mapper.UsersMapper;
import kz.greetgo.task.GreetgoTask.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JwtAuthController {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    ClassMapper classMapper;

    @RequestMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception{
       authenticate(request.getEmail(),request.getPassword());
       final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
       final Users user= (Users) userService.loadUserByUsername(request.getEmail());
       final String token = jwtTokenGenerator.generateToken(userDetails);
        System.out.println("Whatapp");
//       return  ResponseEntity.ok(new JwtResponse(token));

        return new ResponseEntity<>(new JwtResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getAva_picture(),user.getAge(), user.getRoles(), user.getaClass(),token), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> toRegister(@RequestBody UserHelper users){
        System.out.println("users: " + users);

        String firstName = users.getFirstName();
        String lastName = users.getLastName();
        String ava_picture = users.getAva_picture();
        String email = users.getEmail();
        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
        String password = users.getPassword();
        Long a_class_id = 0L;
        int age = users.getAge();

        if(users.getAge()==6){
            a_class_id = 9L;
        }
        else if(users.getAge()==7){
            a_class_id=12L;
        }
        else if(users.getAge()==8){
            a_class_id=13L;
        }
        else if(users.getAge()==9){
            a_class_id=16L;
        }
        else if(users.getAge()==10){
            a_class_id=17L;
        }
        else if(users.getAge()==11){
            a_class_id=20L;
        }
        else if(users.getAge()==12){
            a_class_id=21L;
        }
        else if(users.getAge()==13){
            a_class_id=24L;
        }
        else if(users.getAge()==14){
            a_class_id=25L;
        }
        else if(users.getAge()==15){
            a_class_id=28L;
        }
        else if(users.getAge()==16 || users.getAge()==17){
            a_class_id=29L;
        }

        usersMapper.addUser(firstName, lastName, ava_picture, email, age, password, a_class_id);
        UserHelper users1 = usersMapper.userByEmail(email);
        usersMapper.addUserRole(users1.getId(), 2L);
        return ResponseEntity.ok(users);
//        Users newUser = new Users();
//        System.out.println("USER NAME: " + user.getFirstName() + " " + user.getLastName());
//        newUser.setFullName(user.getFullName());
//        newUser.setPassword(user.getPassword());
//        newUser.setEmail(user.getEmail());
//        newUser.setAva(user.getAva());
//        if (userService.createuser(newUser)!= null){
//            return ResponseEntity.ok(user);
//        }
//        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/allClass")
    public ResponseEntity<?> getAllClass() {
        List<Class> allClass = classMapper.findAll();
        return ResponseEntity.ok(allClass);
    }

    public void authenticate(String email, String password) throws Exception{

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }




}
