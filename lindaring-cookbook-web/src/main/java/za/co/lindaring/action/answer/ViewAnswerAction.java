package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
import za.co.lindaring.ejb.MessageService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.exception.TechnicalException;
import za.co.lindaring.types.AnswerLookUp;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Slf4j
@Setter
@Getter
@RequestScoped
@ManagedBean(name = "ViewAnswerAction")
public class ViewAnswerAction extends BaseAction {

    private String searchName;
    private Date searchFromDate;
    private Date searchToDate;
    private String searchActive;

    private List<Answer> answers = new ArrayList<>();
    private Map<Long, Long> questionIds = new LinkedHashMap<>();

    @EJB
    public AnswerService answerService;

    @EJB
    public MessageService messageService;

    @PostConstruct
    public void init() {
        answers = answerService.getAllAnswers();

        for (Answer a: answers) {
            if (questionIds.get(a.getQuestionId()) == null) {
                questionIds.put(a.getQuestionId(), a.getQuestionId());
            }
        }
    }

    public void search() {
        try {
            Integer active = isNotEmpty(searchActive) ? Integer.parseInt(searchActive) : null;
            AnswerLookUp answerLookUp = AnswerLookUp.builder()
                                                    .text(searchName)
                                                    .startDate(searchFromDate)
                                                    .endDate(searchToDate)
                                                    .active(active)
                                                    .build();
            answers = answerService.searchAnswer(answerLookUp);

        } catch (BusinessException e) {
            displayWarning(e.getMessage());

        } catch (TechnicalException e) {
            log.error(e.getMessage(), e);
            displayError(messageService.getGenericFailedMessage());
        }
    }

    public void reset() {
        this.searchName = "";
        this.searchFromDate = null;
        this.searchToDate = null;
        this.searchActive = "";
    }

}
