
package com.don.onofre.repository;

import com.don.onofre.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author roque
 */
@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {

}
