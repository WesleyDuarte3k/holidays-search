package br.com.feriados.infrastructure.viaCep;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Validated
@FeignClient(contextId = "ViaCepClient", url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepClient {

	@GetMapping("{cep}/json/")
	Address getAddress(@PathVariable String cep);
}
