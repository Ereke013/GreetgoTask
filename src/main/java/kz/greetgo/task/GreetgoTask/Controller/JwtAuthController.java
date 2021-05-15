package kz.greetgo.task.GreetgoTask.Controller;


import kz.greetgo.task.GreetgoTask.Models.JwtRequest;
import kz.greetgo.task.GreetgoTask.Models.JwtResponse;
import kz.greetgo.task.GreetgoTask.Models.UserHelper;
import kz.greetgo.task.GreetgoTask.Models.Users;
import kz.greetgo.task.GreetgoTask.jwt.JwtTokenGenerator;
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

    @RequestMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception{
       authenticate(request.getEmail(),request.getPassword());
       final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
       final Users user= (Users) userService.loadUserByUsername(request.getEmail());
       final String token = jwtTokenGenerator.generateToken(userDetails);
        System.out.println("Whatapp");
//       return  ResponseEntity.ok(new JwtResponse(token));

        return new ResponseEntity<>(new JwtResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getAva_picture(),user.getAge(), user.getRoles(), user.getAClass(),token), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> toRegister(@RequestBody Users users){
        System.out.println("users: " + users);

        String firstName = users.getFirstName();
        String lastName = users.getLastName();
        String ava_picture = users.getAva_picture();
        String email = users.getEmail();
        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
        String password = users.getPassword();
        Long a_class_id = users.getAClass().getId();
        int age = users.getAge();

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
