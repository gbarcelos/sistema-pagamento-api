package br.com.oak.sistemapagamentoapi.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutaTransacao {

  @Autowired
  private EntityManager manager;

  @Transactional
  public <T> T executa(Supplier<T> supplier) {
    return supplier.get();
  }

  @Transactional
  public <T> T commit(T object) {
    manager.persist(object);
    return object;
  }
}
