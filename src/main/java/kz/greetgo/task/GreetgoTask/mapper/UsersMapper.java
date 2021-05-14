package kz.greetgo.task.GreetgoTask.mapper;

import kz.greetgo.task.GreetgoTask.Models.UserHelper;
import kz.greetgo.task.GreetgoTask.Models.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface UsersMapper {
    @Select("select * from t_users")
    List<UserHelper> findAll();

    @Insert("insert into t_users( firstname, lastname, ava_picture, email, age, password, a_class_id) values (#{firstName}, #{lastName}, #{ava_picture}, #{email}, #{age}, #{password}, #{a_class_id})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    void addUser(String firstName, String lastName, String ava_picture, String email, int age, String password, Long a_class_id);

    @Select("select * from t_users where id=#{id}")
    UserHelper userById(Long id);

    @Select("select * from t_users where email=#{email}")
    UserHelper userByEmail(String email);

    @Update("update  t_users set ava_picture=#{ava_picture}, email=#{email}, age=#{age}, firstname=#{firstName}, lastname=#{lastName}, password=#{password}, a_class_id=#{a_class_id} where id=#{id}")
    void updateUser(Long id, String firstName, String lastName, String ava_picture, String email, int age, String password, Long a_class_id);

    @Insert("insert into t_users_roles(users_id, roles_id) VALUES (#{users_id}, #{roles_id})")
    void addUserRole(Long users_id, Long roles_id);

    @Select("select roles_id from t_users_roles where users_id=#{users_id}")
    List<Long> rolesId(Long users_id);

    @Delete("delete from t_users where id=#{id}")
    void deleteUser(Long id);

    @Select("select * from t_users where a_class_id=#{a_class_id}")
    List<UserHelper> getUsersByClass(Long a_class_id);
}
