package br.com.spintec.logicae.lesensebatch.repository;

import br.com.spintec.logicae.lesensebatch.model.LesenseBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LesenseBatchRepository extends JpaRepository<LesenseBatch,Long> {

}
