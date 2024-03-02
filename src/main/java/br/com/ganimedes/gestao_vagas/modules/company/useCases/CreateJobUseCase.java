package br.com.ganimedes.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ganimedes.gestao_vagas.modules.company.entities.JobEntity;
import br.com.ganimedes.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.ganimedes.gestao_vagas.modules.company.repositories.JobReposiotry;
import br.com.ganimedes.gestao_vagas.exceptions.CompanyNotFoundException;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobReposiotry jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
      companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
          throw new CompanyNotFoundException();
      });
      return this.jobRepository.save(jobEntity);
  }
}