package com.demo.desafio_hinova.Services;

import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioServices{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ObjectMapper mapper;

    public List<Usuarios> listar(){
        return repository.findAll();
    }

    public Usuarios salvar(Usuarios usuario){
        // validações
        var validado = validar(usuario);

        //caso validado salva e retorna
        if(validado)
            return repository.save(usuario);

        //caso não retorna usuario vazio
        return new Usuarios();
    }

    public void deletar(Integer id){
         repository.deleteById(id);
    }

    public Usuarios atualizar(Integer id, Usuarios usuarioNovo){
        var validado = validar(usuarioNovo);

        if(validado) {
            Optional<Usuarios> usuarioAntigo = repository.findById(id);
            usuarioAntigo.map(usuarios -> usuarios = usuarioNovo);
            return repository.save(usuarioAntigo.get());

        }else {
            return new Usuarios();
        }
    }

    private boolean validar(Usuarios usuario){

        if(usuario.getCpf().equals(null) || usuario.getEmail().equals(null) || usuario.getName().equals(null) || usuario.getPhone().equals(null))
            return false;
        else if(validarCPF(usuario.getCpf()) && validarTelefone(usuario.getNumber()) && validarEmail(usuario.getEmail()))
            return true;
        return false;
    }

    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }

        int soma = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }

        int digito1 = 11 - (soma % 11);
        if (digito1 == 10 || digito1 == 11) {
            digito1 = 0;
        }

        soma = 0;
        peso = 11;

        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }

        soma += digito1 * 2;
        int digito2 = 11 - (soma % 11);
        if (digito2 == 10 || digito2 == 11) {
            digito2 = 0;
        }

        return cpf.charAt(9) == (char) (digito1 + '0') && cpf.charAt(10) == (char) (digito2 + '0');
    }

    public static boolean validarTelefone(String telefone) {
        Pattern pattern = Pattern.compile("^\\(\\d{2}\\) \\d{5}-\\d{4}$");
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
