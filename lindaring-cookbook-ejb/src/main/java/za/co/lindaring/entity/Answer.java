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
    @NamedQuery(name = "Answer.deleteById", query = "DELETE FROM Answer a WHERE a.id = :answerId")
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
