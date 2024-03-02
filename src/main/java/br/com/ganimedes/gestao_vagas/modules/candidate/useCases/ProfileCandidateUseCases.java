package br.com.ganimedes.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ganimedes.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.ganimedes.gestao_vagas.modules.candidate.dto.ProfiileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCases{
  
  @Autowired
  private CandidateRepository candidateRepository;

  public ProfiileCandidateResponseDTO execute (UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate)
      .orElseThrow(() -> {
        throw  new UsernameNotFoundException("User not found");
      });

      var candidateDTO = ProfiileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .id(candidate.getId())
        .build();
      return candidateDTO;
  }
}
