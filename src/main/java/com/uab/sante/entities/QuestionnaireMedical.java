package com.uab.sante.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table (name = "QuestionnaireMedical")
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
    @Column(name = "Details")
    private String details;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    public QuestionnaireMedical(Boolean question1, Boolean question2, Boolean question3, Boolean question4,Boolean question5, String details, boolean isDeleted){

        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;

        this.details = details;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public Boolean getQuestion1() {
        return question1;
    }

    public Boolean getQuestion2() {
        return question2;
    }

    public Boolean getQuestion3() {
        return question3;
    }

    public Boolean getQuestion4() {
        return question4;
    }

    public Boolean getQuestion5() {
        return question5;
    }

    public String getDetails() {
        return details;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion1(Boolean question1) {
        this.question1 = question1;
    }

    public void setQuestion2(Boolean question2) {
        this.question2 = question2;
    }

    public void setQuestion3(Boolean question3) {
        this.question3 = question3;
    }

    public void setQuestion4(Boolean question4) {
        this.question4 = question4;
    }

    public void setQuestion5(Boolean question5) {
        this.question5 = question5;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionnaireMedical questionnaireMedical = (QuestionnaireMedical) o;
        return isDeleted == questionnaireMedical.isDeleted &&
                Objects.equals(id, questionnaireMedical.id) &&
                Objects.equals(question1, questionnaireMedical.question1) &&
                Objects.equals(question2, questionnaireMedical.question2) &&
                Objects.equals(question3, questionnaireMedical.question3) &&
                Objects.equals(question4, questionnaireMedical.question4) &&
                Objects.equals(question5, questionnaireMedical.question5) &&
                Objects.equals(details, questionnaireMedical.details);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question1, question2, question3, question4, question5, details, isDeleted);
    }
    public QuestionnaireMedical() {super();}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
