package com.demo.desafio_hinova.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.desafio_hinova.Model.Veiculos;

public interface VeiculosRepository extends JpaRepository<Veiculos, Long> {
}
