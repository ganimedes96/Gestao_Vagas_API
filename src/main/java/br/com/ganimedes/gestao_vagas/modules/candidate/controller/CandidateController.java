package br.com.ganimedes.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ganimedes.gestao_vagas.exceptions.USerFoundException;
import br.com.ganimedes.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.ganimedes.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.ganimedes.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.ganimedes.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCases;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {
 
  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;
  @Autowired
  private ProfileCandidateUseCases profileCandidateUseCases;
  @PostMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
     try {
      var result  = this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.status(201).body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
     }
  }
  @GetMapping("/")
  public ResponseEntity<Object> profile(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");
    try {
      var result = this.profileCandidateUseCases.execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
};
