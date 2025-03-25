package com.demo.desafio_hinova.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "Veiculos")
public class Veiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String plate;
    private String advertised_price;
    private String brandId;
    private String modelId;
    private String ano;
    private Integer IdUsuario;
}
