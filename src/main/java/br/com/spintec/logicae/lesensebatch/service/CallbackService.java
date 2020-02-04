package br.com.spintec.logicae.lesensebatch.service;

import br.com.spintec.logicae.lesensebatch.model.Callbacks;
import br.com.spintec.logicae.lesensebatch.repository.CallbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CallbackService {

    @Autowired
    private CallbacksRepository callbacksRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Callbacks save(Callbacks callbacks) {
        return callbacksRepository.saveAndFlush(callbacks);
    }

    public List<Callbacks> findAll() {
        return callbacksRepository.findAll();
    }
}
