package br.com.spintec.logicae.lesensebatch.repository;

import br.com.spintec.logicae.lesensebatch.model.Sensors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorsRepository extends JpaRepository<Sensors, UUID> {


    @Query(value = "select s.*  " +
            "from sensors s " +
            "left join callback_markup cm " +
            "on s.id = cm.sensor_id " +
            "where cm.sensor_id is null " +
            "and  collected > (CURRENT_DATE - INTERVAL '3 months') " +
            "order by collected desc " +
            "limit 500",
            nativeQuery = true)
    List<Sensors> findNewSensors();

    @Query(value= "select distinct s.* " +
            "from sensors s " +
            "join callback_markup cm " +
            "on s.id = cm.sensor_id " +
            "where collected > (CURRENT_DATE - INTERVAL '3 months')  " +
            "and not done " +
            "order by collected asc",
             nativeQuery = true)
    List<Sensors> findToSend();


}