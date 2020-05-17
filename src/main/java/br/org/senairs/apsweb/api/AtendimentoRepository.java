package br.org.senairs.apsweb.api;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.senairs.apsweb.entidades.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>{
	Atendimento findById(long id);
}
