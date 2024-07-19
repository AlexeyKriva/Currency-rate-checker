package org.vadarod.currencyratechecker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vadarod.currencyratechecker.entities.Currency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsCurrencyByDate(LocalDateTime dateTime);
    Optional<Currency> findCurrencyByDateAndCurId(LocalDateTime dateTime, int curId);
}
