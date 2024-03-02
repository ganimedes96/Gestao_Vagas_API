package br.com.ganimedes.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ganimedes.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.ganimedes.gestao_vagas.modules.company.entities.JobEntity;
import br.com.ganimedes.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {
  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  public JobEntity create (@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    // jobEntity.setCompanyId(UUID.fromStr.withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))ing(companyId.toString()));
    var jobEntity = JobEntity.builder()
      .description(createJobDTO.getDescription())
      .companyId(UUID.fromString(companyId.toString()))
      .benefits(createJobDTO.getBenefits())
      .level(createJobDTO.getLevel())
      .build();
    return  this.createJobUseCase.execute(jobEntity);
  }
}
