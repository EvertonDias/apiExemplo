package br.com.api.repository;

import br.com.api.model.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Transactional
    @Modifying
    @Query("update Cliente c set c.isAtivo = :isAtivo where c.id = :id ")
    void desativar(Long id, Boolean isAtivo);
}
