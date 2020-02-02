package br.com.spintec.logicae.lesensebatch.service;

import br.com.spintec.logicae.lesensebatch.model.CallbackMarkup;
import br.com.spintec.logicae.lesensebatch.model.Sensors;
import br.com.spintec.logicae.lesensebatch.repository.CallbackMarkupRepository;
import br.com.spintec.logicae.lesensebatch.repository.CallbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CallbackMarkupService {

    @Autowired
    private CallbackMarkupRepository callbackMarkupRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CallbackMarkup> saveAll(List<CallbackMarkup> markups) {
        return callbackMarkupRepository.saveAll(markups);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CallbackMarkup save(CallbackMarkup markup) {
        return callbackMarkupRepository.saveAndFlush(markup);
    }

    public List<CallbackMarkup> findAllByDoneFalseAndCallbackId(UUID callbackId) {
        return callbackMarkupRepository.findAllByDoneFalseAndCallbackId(callbackId);
    }

    public List<CallbackMarkup> selectOldValue() {
        return callbackMarkupRepository.selectOldValue();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll(List<CallbackMarkup> markups) {
        callbackMarkupRepository.deleteAll(markups);
    }
}
