package br.com.ganimedes.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ganimedes.gestao_vagas.exceptions.USerFoundException;
import br.com.ganimedes.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.ganimedes.gestao_vagas.modules.candidate.entities.CandidateEntity;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new USerFoundException("User already exists");
        });

    var passwordHash = passwordEncoder.encode(candidateEntity.getPassword());   
    candidateEntity.setPassword(passwordHash);   
    return this.candidateRepository.save(candidateEntity);
  }
}
