package br.com.ganimedes.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException() {
    super("Company not found");
}
}
