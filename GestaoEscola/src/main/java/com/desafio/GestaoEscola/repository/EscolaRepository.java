package com.desafio.GestaoEscola.repository;

import com.desafio.GestaoEscola.entities.Escola;
import com.desafio.GestaoEscola.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, UUID> {

    public Escola findByNome(String nome);

    @Query("select e from Escola e where e.nome=:nome and e.provincia.nome=:provincia")
    public Optional<Escola> findByNomeAndAndProvincia(@Param("nome") String nome, @Param("provincia") String provincia);

    @Query("select e from Escola e where e.nome like %:nome% and e.provincia.nome like %:provincia%")
    public List<Escola> findByNomeAndAndProvinciaLike(@Param("nome") String nome, @Param("provincia") String provincia);
}
