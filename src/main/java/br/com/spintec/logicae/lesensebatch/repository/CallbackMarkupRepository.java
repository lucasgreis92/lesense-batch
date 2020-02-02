package br.com.spintec.logicae.lesensebatch.repository;

import br.com.spintec.logicae.lesensebatch.model.CallbackMarkup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CallbackMarkupRepository extends JpaRepository<CallbackMarkup,Long> {

    List<CallbackMarkup> findAllByDoneFalseAndCallbackId(UUID callbackId);

    @Query(value = " select * from callback_markup where executed < (now() - interval '6 months') ", nativeQuery = true)
    List<CallbackMarkup> selectOldValue();
}
