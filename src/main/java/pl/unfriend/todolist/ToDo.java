package pl.unfriend.todolist;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @DateTimeFormat
    private Date deadline;
    @DateTimeFormat
    private Date created;
    @BooleanFlag
    private boolean isdone;
}
