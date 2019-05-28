package za.co.lindaring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "questions")
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.searchByDesc", query = "SELECT q FROM Question q WHERE q.desc LIKE CONCAT('%',:description,'%')"),
    @NamedQuery(name = "Question.searchByDate", query = "SELECT q FROM Question q WHERE q.dateAdded = :sDate"),
    @NamedQuery(name = "Question.searchByDescAndDesc", query = "SELECT q FROM Question q WHERE q.desc LIKE CONCAT('%',:description,'%') AND q.dateAdded = :sDate")
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
}
