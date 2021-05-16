package kz.greetgo.task.GreetgoTask.services.impl;


import kz.greetgo.task.GreetgoTask.Models.Roles;
import kz.greetgo.task.GreetgoTask.Models.UserHelper;
import kz.greetgo.task.GreetgoTask.Models.Users;
import kz.greetgo.task.GreetgoTask.mapper.ClassMapper;
import kz.greetgo.task.GreetgoTask.mapper.RoleMapper;
import kz.greetgo.task.GreetgoTask.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UsersMapper usersMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    ClassMapper classMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserHelper helperUsers = usersMapper.userByEmail(s);
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
            user.setaClass(classMapper.getClassById(helperUsers.getA_class_id()));

            return user;
        } else {
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }
}
