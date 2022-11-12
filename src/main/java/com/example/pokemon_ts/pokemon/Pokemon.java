package com.example.pokemon_ts.pokemon;

import com.example.pokemon_ts.numbersgenerator.HP;
import com.example.pokemon_ts.numbersgenerator.Physical;
import com.example.pokemon_ts.numbersgenerator.Special;
import com.example.pokemon_ts.numbersgenerator.Speed;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class Pokemon {

    Object type;
    HP hp;
    Speed speed;

    public String getTypeName() {
        if (type instanceof Special) {
            return ((Special) type).getName();
        }
        else {
            return ((Physical) type).getName();
        }
    }

}
