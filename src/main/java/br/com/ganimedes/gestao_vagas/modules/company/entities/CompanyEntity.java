package br.com.ganimedes.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {
  @Id 
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;
  @Pattern(regexp = "\\S+", message = "Username inválido")
  private String username;
  @Length(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
  private String  password;
  @Email(message = "Email inválido")
  private String email;
  private String description;
  private String website;
  @CreationTimestamp
  private  LocalDateTime createdAt;
}
