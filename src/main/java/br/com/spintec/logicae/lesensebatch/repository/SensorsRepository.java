package br.com.spintec.logicae.lesensebatch.repository;

import br.com.spintec.logicae.lesensebatch.model.Sensors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SensorsRepository extends JpaRepository<Sensors, UUID> {


    @Query(value = "select s.*  " +
            "from sensors s " +
            "left join callback_markup cm " +
            "on s.id = cm.sensor_id " +
            "where cm.sensor_id is null " +
            "and  collected > (CURRENT_DATE - INTERVAL '180 days') " +
            "order by collected desc " +
            "limit 1000",
            nativeQuery = true)
    List<Sensors> findNewSensors();

    @Query(value= "select distinct s.* " +
            "from sensors s " +
            "join callback_markup cm " +
            "on s.id = cm.sensor_id " +
            "where collected > (CURRENT_DATE - INTERVAL '180 days')  " +
            "and not done " +
            "order by collected desc",
             nativeQuery = true)
    List<Sensors> findToSend();

    @Query(value= "select * from sensors where collected < (now() - interval '6 months')",
            nativeQuery = true)
    List<Sensors>  selectOldValue();

    @Query(value = "select * from sensors " +
            "where device_serial = ?1 " +
            "and collected >= ?2 " +
            "and collected <= ?3 " +
            "order by collected desc",
            nativeQuery = true)
    List<Sensors> findByFilter(String device, LocalDateTime collectedIni, LocalDateTime collectedFim);

    @Query(value = "select * from sensors " +
            "where device_serial = ?1 " +
            "and port = ?2 " +
            "and collected >= ?3 " +
            "and collected <= ?4 " +
            "order by collected desc",
            nativeQuery = true)
    List<Sensors> findByFilter(String device, Long port ,LocalDateTime collectedIni, LocalDateTime collectedFim);
}
