package br.com.oak.sistemapagamentoapi.model.jpa;

import br.com.oak.sistemapagamentoapi.model.FormaPagamento;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Email
  @NotBlank
  private String email;

  @Size(min = 1)
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private Set<FormaPagamento> formasPagamento = new HashSet<>();

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public Set<FormaPagamento> getFormasPagamento() {
    return formasPagamento;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
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
    Usuario usuario = (Usuario) o;
    return Objects.equals(email, usuario.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public String toString() {
    return "Usuario{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", formasPagamento=" + formasPagamento +
        '}';
  }

  public Set<FormaPagamento> filtrarFormasDePagamentoAceita(@NotNull Restaurante restaurante) {
    return formasPagamento.stream()
        .filter(restaurante::aceitaFormaDePagamento)
        .collect(Collectors.toSet());
  }
}