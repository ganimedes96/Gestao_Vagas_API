package br.com.ganimedes.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ganimedes.gestao_vagas.modules.candidate.entities.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
  Optional<CandidateEntity> findByUsername(String username);
}
