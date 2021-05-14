package kz.greetgo.task.GreetgoTask.Repositories;

import kz.greetgo.task.GreetgoTask.Models.Class;
import kz.greetgo.task.GreetgoTask.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClassRepository extends JpaRepository<Class, Long> {
//    Class findByClass_name(String className);
//    List<Class> findAllByClass_nameLike(String className);
}
