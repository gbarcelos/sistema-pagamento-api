package br.com.oak.sistemapagamentoapi.repository;

import br.com.oak.sistemapagamentoapi.model.jpa.Pagamento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

  Optional<Pagamento> findByCodigo(String codigoPagamento);
}
