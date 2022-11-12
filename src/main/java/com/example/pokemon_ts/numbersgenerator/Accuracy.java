package com.example.pokemon_ts.numbersgenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@Setter
@Getter
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class Accuracy {

    Double value = (double) new Random().nextInt(255) / 256;

}
