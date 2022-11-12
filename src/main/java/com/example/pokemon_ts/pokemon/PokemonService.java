package com.example.pokemon_ts.pokemon;

import com.example.pokemon_ts.numbersgenerator.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@Setter
@Getter
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class PokemonService {

    public Pokemon getNewPokemon() {
        var hp = new HP();
        var speed = new Speed();
        var isSpecial = new Random().nextBoolean();
        var defence = new Defence();
        var specialDefence = new SpecialDefence();
        if (isSpecial) {
            var specialType = new Special();
            specialType.setSpecialAttack1(new SpecialAttack(new Accuracy()));
            specialType.setSpecialAttack2(new SpecialAttack(new Accuracy()));
            specialType.setSpecialAttack3(new SpecialAttack(new Accuracy()));
            specialType.setSpecialAttack4(new SpecialAttack(new Accuracy()));
            return new Pokemon(specialType, hp, speed, specialDefence, defence);
        }
        else {
            var physicalType = new Physical();
            physicalType.setAttack1(new Attack(new Accuracy()));
            physicalType.setAttack2(new Attack(new Accuracy()));
            physicalType.setAttack3(new Attack(new Accuracy()));
            physicalType.setAttack4(new Attack(new Accuracy()));
            return new Pokemon(physicalType, hp, speed, specialDefence, defence);
        }

    }

}
