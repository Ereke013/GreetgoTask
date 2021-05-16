package kz.greetgo.task.GreetgoTask.Models;



import javax.persistence.*;

@Entity
@Table(name = "t_class")

public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "class_name")
    private String class_name;

    public Class() {
    }

    public Class(Long id, String class_name) {
        this.id = id;
        this.class_name = class_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
