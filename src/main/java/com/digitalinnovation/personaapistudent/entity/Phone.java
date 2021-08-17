package com.digitalinnovation.personaapistudent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.digitalinnovation.personaapistudent.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id // irá anotar uma chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // irá delegar ao banco de dados a geração de id (icremental)
    private Long id;

    @Enumerated(EnumType.STRING) // define a type de dados igual o criado no PhoneType
    @Column(nullable = false) // irá criar um campo que não pode ser nulo
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
