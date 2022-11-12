package com.example.pokemon_ts.pokemon;

import com.example.pokemon_ts.numbersgenerator.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class Pokemon {

    Object type;
    HP hp;
    Speed speed;
    SpecialDefence specialDefence;
    Defence defence;

    public String getTypeName() {
        if (type instanceof Special) {
            return ((Special) type).getName();
        }
        else {
            return ((Physical) type).getName();
        }
    }

}
