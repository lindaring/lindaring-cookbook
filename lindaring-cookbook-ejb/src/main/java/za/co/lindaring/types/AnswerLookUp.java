package za.co.lindaring.types;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class AnswerLookUp {
    private String text;
    private Date startDate;
    private Date endDate;
    private Integer active;
    private Double points;
    private Long questionId;
}
