package za.co.lindaring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
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
