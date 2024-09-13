package br.com.oak.sistemapagamentoapi.model.jpa;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Restaurante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Size(min = 1)
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private Set<FormaPagamento> formasPagamento = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Set<FormaPagamento> getFormasPagamento() {
    return formasPagamento;
  }

  public void setFormasPagamento(
      Set<FormaPagamento> formasPagamento) {
    this.formasPagamento = formasPagamento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Restaurante that = (Restaurante) o;
    return Objects.equals(nome, that.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome);
  }

  @Override
  public String toString() {
    return "Restaurante{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", formasPagamento=" + formasPagamento +
        '}';
  }

  public boolean aceitaFormaDePagamento(@NotNull FormaPagamento formaPagamento) {
    return formasPagamento.contains(formaPagamento);
  }
}
