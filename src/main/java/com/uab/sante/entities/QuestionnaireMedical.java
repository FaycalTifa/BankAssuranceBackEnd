package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table (name = "QuestionnaireMedical")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionnaireMedical implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Question1")
    private Boolean question1;

    @Column(name = "Question2")
    private Boolean question2;

    @Column(name = "Question3")
    private Boolean question3;

    @Column(name = "Question4")
    private Boolean question4;

    @Column(name = "Question5")
    private Boolean question5;

    @Column(name = "detail1")
    private String detail1;

    @Column(name = "detail2")
    private String detail2;

 @Column(name = "detail3")
    private String detail3;

    @Column(name = "detail4")
    private String detail4;

 @Column(name = "detail5")
    private String detail5;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;


}
