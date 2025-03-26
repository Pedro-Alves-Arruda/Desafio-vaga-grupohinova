package com.demo.desafio_hinova.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "VEICULOS")
public class Veiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plate;
    private String advertisedPrice;
    private String brandId;
    private String modelId;
    private String ano;
    private Integer IdUsuario;
    @CreatedDate
    private LocalDateTime createdAt;
}
