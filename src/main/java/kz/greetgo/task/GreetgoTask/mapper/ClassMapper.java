package kz.greetgo.task.GreetgoTask.mapper;

import kz.greetgo.task.GreetgoTask.Models.Class;
import kz.greetgo.task.GreetgoTask.Models.Roles;
import kz.greetgo.task.GreetgoTask.Models.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassMapper {
    @Select("select * from t_class")
    List<Class> findAll();

    @Insert("insert into t_class(class_name) values (#{class_name})")
//    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    void addClass(String class_name);

    @Select("select * from t_class where class_name=#{class_name}")
    Class getClassByName(String class_name);

    @Select("select * from t_class where id=#{id}")
    Class getClassById(Long id);

    @Delete("delete from t_class where id=#{id}")
    void deleteClass(Long id);
}
