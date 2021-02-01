package com.portfolio.repositories;

import com.portfolio.models.Portfolio;
import org.springframework.data.repository.CrudRepository;

/**
 * Portfolio repository
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
}
