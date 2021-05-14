package kz.greetgo.task.GreetgoTask.mapper;

import kz.greetgo.task.GreetgoTask.Models.Roles;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    @Select("select * from t_roles")
    List<Roles> findAll();

    @Select("select * from t_roles where role!='#{role}'")
    List<Roles> getAllUsersWithout(String role);

    @Select("select * from t_roles where id=#{id}")
    Roles getRole(Long id);


}
