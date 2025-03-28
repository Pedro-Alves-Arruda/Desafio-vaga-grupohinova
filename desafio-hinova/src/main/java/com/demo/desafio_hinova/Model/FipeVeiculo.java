package com.demo.desafio_hinova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FipeVeiculo implements Serializable
{
    @JsonProperty("TipoVeiculo")
    private int tipoVeiculo;

    @JsonProperty("Valor")
    private String valor;

    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("AnoModelo")
    private int anoModelo;

    @JsonProperty("Combustivel")
    private String combustivel;

    @JsonProperty("CodigoFipe")
    private String codigoFipe;

    @JsonProperty("MesReferencia")
    private String mesReferencia;

    @JsonProperty("SiglaCombustivel")
    private String siglaCombustivel;

    // Construtor padrão necessário para Jackson
    public FipeVeiculo() {}

    // Getters e Setters
    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(int tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getSiglaCombustivel() {
        return siglaCombustivel;
    }

    public void setSiglaCombustivel(String siglaCombustivel) {
        this.siglaCombustivel = siglaCombustivel;
    }
}
