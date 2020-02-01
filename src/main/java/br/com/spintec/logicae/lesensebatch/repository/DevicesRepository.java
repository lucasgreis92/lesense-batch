package br.com.spintec.logicae.lesensebatch.repository;

import br.com.spintec.logicae.lesensebatch.model.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicesRepository extends JpaRepository<Devices,String> {
}
