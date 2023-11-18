package com.jcr.repository;

import com.jcr.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * 
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Finds a {@link User} entity by its username.
	 *
	 * @param username		The username of the user to be searched for.
	 * @return An Optional containing the found User if present, or an empty
	 *         Optional if not found.
	 */
	Optional<User> findByUsername(String username);
}
