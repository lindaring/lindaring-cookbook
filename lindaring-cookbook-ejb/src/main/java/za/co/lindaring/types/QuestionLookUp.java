package za.co.lindaring.types;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class QuestionLookUp {
    private String name;
    private Date startDate;
    private Date endDate;
    private Integer active;
}