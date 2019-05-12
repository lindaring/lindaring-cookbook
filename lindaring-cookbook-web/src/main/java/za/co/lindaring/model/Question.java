package za.co.lindaring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    private long id;
    private String desc;
    private Date dateAdded;
    private int active;
}
