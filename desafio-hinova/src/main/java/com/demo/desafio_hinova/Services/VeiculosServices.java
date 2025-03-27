package com.demo.desafio_hinova.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.desafio_hinova.Model.Veiculos;
import com.demo.desafio_hinova.Repository.VeiculosRepository;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculosServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.demo.desafio_hinova.Services.VeiculosServices.class);

    @Autowired
    private VeiculosRepository repository;

    @Autowired
    private ObjectMapper mapper;

    public List<Veiculos> listar(){
        return repository.findAll();
    }

    public void salvar(Veiculos veiculo){
        //validações
        var valido = true;

        //caso valido ele salva
        if(valido)
            repository.save(veiculo);

        //caso não ele lança um log de erro
        LOGGER.info("Erro ao salvar usuario, usuario não é valido");
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public void atualizar(Long id, Veiculos veiculoNovo){
        //validações
        var validado = true;

        //caso validado ele atualiza
        if(validado) {
            Optional<Veiculos> veiculoAntigo = repository.findById(id);
            veiculoAntigo = veiculoAntigo.map(veiculos -> veiculos = veiculoNovo);
            repository.save(veiculoAntigo.get());

        }else {
            //caso não ele lança um log de erro
            LOGGER.info("Erro ao atualizar veiculo, veiculo não é valido");
        }
    }
}
