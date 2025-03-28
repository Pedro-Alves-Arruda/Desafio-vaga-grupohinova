package com.demo.desafio_hinova.Repository;

import com.demo.desafio_hinova.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

    @NativeQuery("SELECT * FROM USUARIOS WHERE created_at BETWEEN :dataInicial AND :dataFinal")
    List<Usuarios> listarUsuariosDataEspecifica(@Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal);

}
