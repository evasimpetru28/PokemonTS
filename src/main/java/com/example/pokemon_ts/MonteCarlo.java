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
        double favourable = 0;
        double total = 0;
        while (i <= 1000) { // de modificat cu nr mai mare
            i++;

            Pokemon enemyPokemon = pokemonService.getNewPokemon();
            Pokemon playerPokemon = pokemonService.getNewPokemon();

            if (enemyPokemon.getSpeed().getValue() < playerPokemon.getSpeed().getValue()) {
                playerStarts = true;
            }

            BattleRound battleRound = new BattleRound();

            var multiplierForPlayer = getMultiplier(playerPokemon, enemyPokemon);
            var multiplierForEnemy = getMultiplier(enemyPokemon, playerPokemon);

            Integer playerLife;
            Integer enemyLife;

            while (true) {
                playerLife = playerPokemon.getHp().getValue();
                enemyLife = enemyPokemon.getHp().getValue();

                if (battleIsFinished(playerLife, enemyLife)) {
                    break;
                }
                battleRound.fight(playerPokemon, enemyPokemon, playerStarts, multiplierForPlayer, multiplierForEnemy);
            }
            if (checkIfPlayerWon(playerLife)) {
                favourable ++;
            }
            total ++;
        }

        System.out.println(favourable + " " + total);
        System.out.println(favourable/total);
    }

    private boolean battleIsFinished(Integer playerLife, Integer enemyLife) {
        return playerLife <= 0 || enemyLife <= 0;
    }

    public boolean checkIfPlayerWon(int hp) {
        return hp > 0;
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
        return switch (pokemon.getTypeName()) {
            case "Normal" -> 1;
            case "Fighting" -> 7;
            case "Poison" -> 8;
            case "Ground" -> 9;
            case "Flying" -> 10;
            case "Bug" -> 12;
            case "Rock" -> 13;
            case "Ghost" -> 14;
            case "Steel" -> 17;
            case "Fire" -> 2;
            case "Water" -> 3;
            case "Electric" -> 4;
            case "Grass" -> 5;
            case "Ice" -> 6;
            case "Psychic" -> 11;
            case "Dragon" -> 15;
            case "Dark" -> 16;
            default -> 18;
        };
    }

}
