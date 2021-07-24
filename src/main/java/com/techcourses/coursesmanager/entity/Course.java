package com.techcourses.coursesmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "duration")
    private Integer duration;
    @NonNull
    @Column(name = "certification")
    private Boolean certification;

    @OneToMany(mappedBy = "course",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Student> students;
}
