package com.portfolio.repositories;

import com.portfolio.models.Profile;
import org.springframework.data.repository.CrudRepository;

/**
 * Portfolio repository
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
public interface ProfileRepository extends CrudRepository<Profile, String> {
}
