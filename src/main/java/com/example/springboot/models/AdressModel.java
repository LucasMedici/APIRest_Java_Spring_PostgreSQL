package com.example.springboot.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "TB_ADRESSES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_adress")
public class AdressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_adress;

    private String street;
    private String number;
    private String neighborhood;
    private String complement;
    private String zipcode;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
