package za.co.lindaring.ejb.base;

import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
public abstract class BaseService {

    @Getter(AccessLevel.NONE)
    private static final String LOBOLA_PERSISTENCE_UNIT = "LobolaCalcPU";

    @PersistenceContext(unitName = LOBOLA_PERSISTENCE_UNIT)
    EntityManager entityManager;

}
