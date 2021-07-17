package com.techcourses.coursesmanager.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    private Integer age;

//    public Integer getAge() {
//        return Period.between(this.dob, LocalDate.now()).getYears();
//    }
}
