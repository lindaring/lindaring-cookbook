package za.co.lindaring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "questions")
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.searchByDesc", query = "SELECT q FROM Question q WHERE q.desc LIKE CONCAT('%',:description,'%')"),
    @NamedQuery(name = "Question.searchByDate", query = "SELECT q FROM Question q WHERE q.dateAdded >= :sDate AND q.dateAdded <= :eDate"),
    @NamedQuery(name = "Question.searchByActive", query = "SELECT q FROM Question q WHERE q.active = :active"),
    @NamedQuery(name = "Question.searchByDescAndDate", query = "SELECT q FROM Question q WHERE q.desc LIKE CONCAT('%',:description,'%') AND (q.dateAdded >= :sDate AND q.dateAdded <= :eDate)"),
    @NamedQuery(name = "Question.searchByDescAndActive", query = "SELECT q FROM Question q WHERE q.desc LIKE CONCAT('%',:description,'%') AND q.active = :active"),
    @NamedQuery(name = "Question.searchByDateAndActive", query = "SELECT q FROM Question q WHERE (q.dateAdded >= :sDate AND q.dateAdded <= :eDate) AND q.active = :active"),
    @NamedQuery(name = "Question.searchByDescAndDateAndActive", query = "SELECT q FROM Question q WHERE q.desc LIKE CONCAT('%',:description,'%') AND (q.dateAdded >= :sDate AND q.dateAdded <= :eDate) AND q.active = :active")
})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long id;

    @Column(name = "description")
    private String desc;

    private Date dateAdded;

    @Column(name = "activated")
    private int active;

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "questionId",
        cascade = CascadeType.ALL
    )
    private List<Answer> answers = new ArrayList<>();
}
