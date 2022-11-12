package com.example.pokemon_ts.pokemon;

import com.example.pokemon_ts.PokemonTsApplication;
import com.example.pokemon_ts.numbersgenerator.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@Setter
@Getter
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class PokemonService {

    public Pokemon getNewPokemon() {
        var probability = new Probability();
        var accuracy = new Accuracy();
        var hp = new HP();
        var speed = new Speed();
        var isSpecial = new Random().nextBoolean();
        if (isSpecial) {
            var specialType = new Special();
            specialType.setSpecialAttack1(new SpecialAttack(probability, accuracy));
            specialType.setSpecialAttack2(new SpecialAttack(probability, accuracy));
            specialType.setSpecialAttack3(new SpecialAttack(probability, accuracy));
            specialType.setSpecialAttack4(new SpecialAttack(probability, accuracy));
            specialType.setSpecialDefence(new SpecialDefence());
            return new Pokemon(specialType, hp, speed);
        }
        var physicalType = new Physical();
        physicalType.setAttack1(new Attack(probability, accuracy));
        physicalType.setAttack2(new Attack(probability, accuracy));
        physicalType.setAttack3(new Attack(probability, accuracy));
        physicalType.setAttack4(new Attack(probability, accuracy));
        physicalType.setDefence(new Defence());
        return new Pokemon(physicalType, hp, speed);
    }

}
