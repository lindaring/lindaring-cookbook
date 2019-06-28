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
@Table(name = "answers")
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.deleteById", query = "DELETE FROM Answer a WHERE a.id = :answerId"),
    @NamedQuery(name = "Answer.searchByText", query = "SELECT a FROM Answer a WHERE a.text LIKE CONCAT('%',:text,'%')"),
    @NamedQuery(name = "Answer.searchByDate", query = "SELECT a FROM Answer a WHERE a.dateAdded >= :sDate AND a.dateAdded <= :eDate"),
    @NamedQuery(name = "Answer.searchByActive", query = "SELECT a FROM Answer a WHERE a.active = :active"),
    @NamedQuery(name = "Answer.searchByTextAndDate", query = "SELECT a FROM Answer a WHERE a.text LIKE CONCAT('%',:text,'%') AND (a.dateAdded >= :sDate AND a.dateAdded <= :eDate)"),
    @NamedQuery(name = "Answer.searchByTextAndActive", query = "SELECT a FROM Answer a WHERE a.text LIKE CONCAT('%',:text,'%') AND a.active = :active"),
    @NamedQuery(name = "Answer.searchByDateAndActive", query = "SELECT a FROM Answer a WHERE (a.dateAdded >= :sDate AND a.dateAdded <= :eDate) AND a.active = :active"),
    @NamedQuery(name = "Answer.searchByTextAndDateAndActive", query = "SELECT a FROM Answer a WHERE a.text LIKE CONCAT('%',:text,'%') AND (a.dateAdded >= :sDate AND a.dateAdded <= :eDate) AND a.active = :active")
})
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long id;

    private String text;

    private Date dateAdded;

    @Column(name = "actived")
    private Integer active;

    @Column(name = "point")
    private Double points;

    @Column(name = "question_id")
    private long questionId;

    public String getAnswerId() {
        return "Answer " + id;
    }

}
