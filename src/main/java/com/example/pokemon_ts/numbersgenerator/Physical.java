package com.example.pokemon_ts.numbersgenerator;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Random;

@Setter
@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Physical {

    String name = List.of("Normal", "Fighting", "Poison", "Ground", "Flying", "Bug", "Rock", "Ghost", "Steel").get(new Random().nextInt(9));
    Attack attack1;
    Attack attack2;
    Attack attack3;
    Attack attack4;

}
