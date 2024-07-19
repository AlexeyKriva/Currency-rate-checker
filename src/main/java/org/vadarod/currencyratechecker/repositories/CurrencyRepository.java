package org.vadarod.currencyratechecker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vadarod.currencyratechecker.entities.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
