package com.demo.desafio_hinova.Services;

import com.demo.desafio_hinova.Model.Usuarios;
import com.demo.desafio_hinova.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
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

    public void deletar(Long id){
         repository.deleteById(id);
    }

    public Usuarios atualizar(Long id, Usuarios usuarioNovo){
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

    public static boolean validarCPF(String CPF) {
        if (CPF == null)
            return false;

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") ||
                CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
                || CPF.equals("99999999999") || (CPF.length() != 11))
            return(false);
        char dig10,
                dig11;
        int sm, i, r, num, peso;
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0; peso = 10; for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);
            // converte no respectivo caractere numerico
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);
            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else
                return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
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
