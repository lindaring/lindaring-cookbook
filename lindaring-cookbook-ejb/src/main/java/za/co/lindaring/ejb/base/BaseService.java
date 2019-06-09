package za.co.lindaring.ejb.base;

import lombok.AccessLevel;
import lombok.Getter;
import za.co.lindaring.types.CookbookDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

}
