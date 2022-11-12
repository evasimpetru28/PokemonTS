package com.example.pokemon_ts;

import com.example.pokemon_ts.numbersgenerator.*;
import com.example.pokemon_ts.pokemon.Pokemon;
import com.example.pokemon_ts.pokemon.PokemonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class PokemonTsApplication {

    static MonteCarlo monteCarlo = new MonteCarlo(new PokemonService());

    public static void main(String[] args) {
        SpringApplication.run(PokemonTsApplication.class, args);
        monteCarlo.getEstimationOfWinning();
    }

}
