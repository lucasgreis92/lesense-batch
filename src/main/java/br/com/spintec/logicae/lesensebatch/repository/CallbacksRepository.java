package br.com.spintec.logicae.lesensebatch.repository;

import br.com.spintec.logicae.lesensebatch.model.Callbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallbacksRepository extends JpaRepository<Callbacks,String> {
}
