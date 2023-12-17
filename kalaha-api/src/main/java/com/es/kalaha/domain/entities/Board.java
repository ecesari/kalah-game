package com.es.kalaha.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    @OneToMany(cascade = CascadeType.ALL)
    public List<House> houses;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Store> stores;
    public Board() {
    }


}
