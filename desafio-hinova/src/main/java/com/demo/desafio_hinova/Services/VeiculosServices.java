package com.demo.desafio_hinova.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import com.demo.desafio_hinova.Model.Veiculos;
import com.demo.desafio_hinova.Model.FipeVeiculo;
import com.demo.desafio_hinova.Repository.VeiculosRepository;
import com.demo.desafio_hinova.Exceptions.CamposVeiculosNulos;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
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

    public Veiculos salvar(Veiculos veiculo){
        //validações
        FipeVeiculo fipeVeiculo = valida(veiculo);

        //caso valido ele salva
        if(fipeVeiculo != null) {
            veiculo.setCreatedAt(LocalDateTime.now());
            veiculo.setFipePrice(fipeVeiculo.getValor());
            return repository.save(veiculo);
        }

        //caso não ele lança um log de erro
        LOGGER.info("Erro ao salvar veiculo, veiculo não é valido");
        return new Veiculos();
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public void atualizar(Long id, Veiculos veiculoNovo){
        //validações
        FipeVeiculo fipeVeiculo = valida(veiculoNovo);

        //caso validado ele atualiza
        if(fipeVeiculo != null) {
            veiculoNovo.setFipePrice(fipeVeiculo.getValor());
            Optional<Veiculos> veiculoAntigo = repository.findById(id);
            veiculoAntigo = veiculoAntigo.map(veiculos -> veiculos = veiculoNovo);
            repository.save(veiculoAntigo.get());

        }else {
            //caso não ele lança um log de erro
            LOGGER.info("Erro ao atualizar veiculo, veiculo não é valido");
        }
    }

    private FipeVeiculo valida(Veiculos veiculo) {

        if(veiculo.getPlate().equals(null) || veiculo.getAdvertisedPrice().equals(null)
        || veiculo.getAno().equals(null)){
            throw new CamposVeiculosNulos("Por favor verificar os campos plate, advertised_price, ano. Eles não podem ser nulos");
        }
        try{
            LOGGER.info("Buscando veiculo em: https://parallelum.com.br/fipe/api/v1/carros/marcas/"+veiculo.getBrandId()+"/modelos/"+veiculo.getModelId()+"/anos/"+veiculo.getAno());
            var restClient = buscaDadosVeiculo(veiculo);

            FipeVeiculo fipeVeiculo = mapper.readValue(restClient.getBody(), FipeVeiculo.class);
            if(fipeVeiculo != null)
                return fipeVeiculo;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


       return new FipeVeiculo();
    }

    @Cacheable("veiculo")
    private static ResponseEntity<String> buscaDadosVeiculo(Veiculos veiculo) {
        return RestClient.builder()
                .baseUrl("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + veiculo.getBrandId() + "/modelos/" + veiculo.getModelId() + "/anos/" + veiculo.getAno())
                .build()
                .get()
                .retrieve()
                .toEntity(String.class);
    }
}
