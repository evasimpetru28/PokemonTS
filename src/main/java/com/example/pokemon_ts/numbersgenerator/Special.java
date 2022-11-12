package com.example.pokemon_ts.numbersgenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Random;

@Setter
@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Special{

    String name = List.of("Fire", "Water", "Electric", "Grass", "Ice", "Psychic", "Dragon", "Dark", "Fairy").get(new Random().nextInt(9));
    SpecialAttack specialAttack1;
    SpecialAttack specialAttack2;
    SpecialAttack specialAttack3;
    SpecialAttack specialAttack4;
    SpecialDefence specialDefence;

}
