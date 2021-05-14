package kz.greetgo.task.GreetgoTask.Repositories;

import kz.greetgo.task.GreetgoTask.Models.Roles;
import kz.greetgo.task.GreetgoTask.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(String role);
}
