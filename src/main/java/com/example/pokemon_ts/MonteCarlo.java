package com.example.pokemon_ts;

import com.example.pokemon_ts.pokemon.BattleRound;
import com.example.pokemon_ts.pokemon.Pokemon;
import com.example.pokemon_ts.pokemon.PokemonService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MonteCarlo {

    final PokemonService pokemonService;

    public void getEstimationOfWinning() {
        boolean playerStarts = false;
        int i = 1;
        while (i <= 1000000000) {
            Pokemon enemyPokemon = pokemonService.getNewPokemon();
            Pokemon playerPokemon = pokemonService.getNewPokemon();
            i++;
            if (enemyPokemon.getSpeed().getValue() < playerPokemon.getSpeed().getValue()) {
                playerStarts = true;
            }
            BattleRound battleRound = new BattleRound();
            battleRound.fight(playerPokemon, enemyPokemon, playerStarts);
            var x = getMultiplier(playerPokemon, enemyPokemon);
            System.out.println(x);
        }
    }

    public Double getMultiplier(Pokemon playerPokemon, Pokemon enemyPokemon) {
        var multiplierMatrix = List.of(
                List.of(0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0.5,0d,0d,0d,0.5,0d),
                List.of(0d,0.5,0.5,0d,2d,2d,0d,0d,0d,0d,0d,2d,0.5,0d,0.5,0d,2d,0d),
                List.of(0d,2d,0.5,0d,0.5,0d,0d,0d,2d,0d,0d,0d,2d,0d,0.5,0d,0d,0d),
                List.of(0d,0d,2d,0.5,0.5,0d,0d,0d,0d,2d,0d,0d,0d,0d,0.5,0d,0d,0d),
                List.of(0d,0.5,2d,0d,0.5,0d,0d,0.5,2d,0.5,0d,0.5,2d,0d,0.5,0d,0.5,0d),
                List.of(0d,0.5,0.5,0d,2d,0.5,0d,0d,2d,2d,0d,0d,0d,0d,2d,0d,0.5,0d),
                List.of(2d,0d,0d,0d,0d,2d,0d,0.5,0d,0.5,0.5,0.5,2d,0d,0d,2d,2d,0.5),
                List.of(0d,0d,0d,0d,2d,0d,0d,0.5,0.5,0d,0d,0d,0.5,0.5,0d,0d,0d,2d),
                List.of(0d,2d,0d,2d,0.5,0d,0d,2d,0d,0d,0d,0.5,2d,0d,0d,0d,2d,0d),
                List.of(0d,0d,0d,0.5,2d,0d,2d,0d,0d,0d,0d,2d,0.5,0d,0d,0d,0.5,0d),
                List.of(0d,0d,0d,0d,0d,0d,2d,2d,0d,0d,0.5,0d,0d,0d,0d,0d,0.5,0d),
                List.of(0d,0.5,0d,0d,2d,0d,0.5,0.5,0d,0.5,2d,0d,0d,0.5,0d,2d,0.5,0.5),
                List.of(0d,2d,0d,0d,0d,2d,0.5,0d,0.5,2d,0d,2d,0d,0d,0d,0d,0.5,0d),
                List.of(0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,2d,0d,0d,2d,0d,0.5,0d,0d),
                List.of(0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,2d,0d,0.5,0d),
                List.of(0d,0d,0d,0d,0d,0d,0.5,0d,0d,0d,2d,0d,0d,2d,0d,0.5,0d,0.5),
                List.of(0d,0.5,0.5,0.5,0d,2d,0d,0d,0d,0d,0d,0d,2d,0d,0d,0d,0.5,2d),
                List.of(0d,0.5,0d,0d,0d,0d,2d,0.5,0d,0d,0d,0d,0d,0d,2d,2d,0.5,0d)
        );

        return multiplierMatrix.get(getTypeNumber(playerPokemon)-1).get(getTypeNumber(enemyPokemon)-1);
    }

    private Integer getTypeNumber(Pokemon pokemon) {
        int number;
        switch (pokemon.getTypeName()) {
            case "Normal":
                number = 1;
                break;
            case "Fighting":
                number = 7;
                break;
            case "Poison":
                number = 8;
                break;
            case "Ground":
                number = 9;
                break;
            case "Flying":
                number = 10;
                break;
            case "Bug":
                number = 12;
                break;
            case "Rock":
                number = 13;
                break;
            case "Ghost":
                number = 14;
                break;
            case "Steel":
                number = 17;
                break;
            case "Fire":
                number = 2;
                break;
            case "Water":
                number = 3;
                break;
            case "Electric":
                number = 4;
                break;
            case "Grass":
                number = 5;
                break;
            case "Ice":
                number = 6;
                break;
            case "Psychic":
                number = 11;
                break;
            case "Dragon":
                number = 15;
                break;
            case "Dark":
                number = 16;
                break;
            default:
                number = 18;
                break;
        }
        return number;
    }

}
