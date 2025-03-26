package com.demo.desafio_hinova.Repository;

import com.demo.desafio_hinova.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
}
