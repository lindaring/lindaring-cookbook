package za.co.lindaring.ejb.base;

import lombok.AccessLevel;
import lombok.Getter;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.types.CookbookDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.SortedMap;

@Getter
public abstract class BaseService {

    @Getter(AccessLevel.NONE)
    private static final String LOBOLA_PERSISTENCE_UNIT = "LobolaCalcPU";

    @PersistenceContext(unitName = LOBOLA_PERSISTENCE_UNIT)
    EntityManager entityManager;

    protected void incrementMonthValue(SortedMap<Integer, Long> map, Date dateAdded) {
        CookbookDate date = new CookbookDate(dateAdded);
        Long m = map.get(date.getMonth());
        map.put(date.getMonth(), (m != null) ? ++m : 1);
    }

    protected <T> void getDateTypedQueryParameters(TypedQuery<T> typedQuery, Date from, Date to) throws BusinessException {
        if (from != null && to != null) {
            typedQuery.setParameter("sDate", (new CookbookDate(from)).toStartOfDay());
            typedQuery.setParameter("eDate", (new CookbookDate(to)).toEndOfDay());
        } else if (to != null) {
            throw new BusinessException("Enter from data.");
        } else if (from != null) {
            throw new BusinessException("Enter to data.");
        }
    }

}
