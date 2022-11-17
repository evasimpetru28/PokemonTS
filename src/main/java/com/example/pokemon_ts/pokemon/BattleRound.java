package com.example.pokemon_ts.pokemon;

import com.example.pokemon_ts.numbersgenerator.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BattleRound {

    public void fight(Pokemon playerPokemon, Pokemon enemyPokemon, boolean playerStarts, Double multiplierForPlayer, Double multiplierForEnemy) {
        SpecialAttack playerSpecialAttack;
        Attack playerAttack;
        SpecialAttack enemySpecialAttack;
        Attack enemyAttack;

        Integer enemyLife = enemyPokemon.getHp().getValue();
        Integer playerLife = playerPokemon.getHp().getValue();

        Integer playerSpecialDefence = playerPokemon.getSpecialDefence().getStrength();
        Integer playerDefence = playerPokemon.getDefence().getStrength();

        Integer enemySpecialDefence = enemyPokemon.getSpecialDefence().getStrength();
        Integer enemyDefence = enemyPokemon.getDefence().getStrength();

        if (playerPokemon.getType() instanceof Special) {

            playerSpecialAttack = getRandomSpecialAttack(playerPokemon);
            double playerSpecialAccuracy = 1 - playerSpecialAttack.getAccuracy().getValue();

            Integer playerScpecialPower = playerSpecialAttack.getPower();
            if (enemyPokemon.getType() instanceof Special) {

                // special + special

                enemySpecialAttack = getRandomSpecialAttack(enemyPokemon);
                double enemySpecialAccuracy = 1 - enemySpecialAttack.getAccuracy().getValue();

                Integer enemySpecialPower = enemySpecialAttack.getPower();
                if (playerStarts) {
                    enemyLife = enemyLife - (int) (10 * enemySpecialDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower);
                    enemyPokemon.getHp().setValue(enemyLife);
                    if (enemyLife <= 0) {
                        return;
                    }
                    playerPokemon.getHp().setValue(playerLife - (int) (10 * playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower));
                }
                else {
                    playerLife = playerLife - (int) (10 * playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower);
                    playerPokemon.getHp().setValue(playerLife);
                    if (playerLife <= 0) {
                        return;
                    }
                    enemyPokemon.getHp().setValue(enemyLife - (int) (10 * enemyDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower));
                }
            }
            else {

                // special + physical

                enemyAttack = getRandomAttack(enemyPokemon);
                double enemyAccuracy = 1 - enemyAttack.getAccuracy().getValue();

                Integer enemyPower = enemyAttack.getPower();
                if (playerStarts) {
                    enemyLife = enemyLife - (int) (10 * enemyDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower);
                    enemyPokemon.getHp().setValue(enemyLife);
                    if (enemyLife <= 0) {
                        return;
                    }
                    playerPokemon.getHp().setValue(playerLife - (int) (10 * playerDefence * enemyAccuracy - multiplierForEnemy * enemyPower));
                }
                else {
                    playerLife = playerLife - (int) (10 * playerDefence * enemyAccuracy - multiplierForEnemy * enemyPower);
                    playerPokemon.getHp().setValue(playerLife);
                    if (playerLife <= 0) {
                        return;
                    }
                    enemyPokemon.getHp().setValue(enemyLife - (int) (10 * enemyDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower));
                }
            }
        }
        else {
            playerAttack = getRandomAttack(playerPokemon);

            double playerAccuracy = 1 - playerAttack.getAccuracy().getValue();
            Integer playerPower = playerAttack.getPower();
            if (enemyPokemon.getType() instanceof Special) {

                // physical + special

                enemySpecialAttack = getRandomSpecialAttack(enemyPokemon);
                double enemySpecialAccuracy = 1 - enemySpecialAttack.getAccuracy().getValue();

                Integer enemySpecialPower = enemySpecialAttack.getPower();
                if (playerStarts) {
                    enemyLife = enemyLife - (int) (10 * enemyDefence * playerAccuracy - multiplierForPlayer * playerPower);
                    enemyPokemon.getHp().setValue(enemyLife);
                    if (enemyLife <= 0) {
                        return;
                    }
                    playerPokemon.getHp().setValue(playerLife - (int) (10 * playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower));
                }
                else {
                    playerLife = playerLife - (int) (10 * playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower);
                    playerPokemon.getHp().setValue(playerLife);
                    if (playerLife <= 0) {
                        return;
                    }
                    enemyPokemon.getHp().setValue(enemyLife - (int) (10 * enemyDefence * playerAccuracy - multiplierForPlayer * playerPower));
                }
            }
            else {

                // physical + physical

                enemyAttack = getRandomAttack(enemyPokemon);
                double enemyAccuracy = 1 - enemyAttack.getAccuracy().getValue();

                if (playerStarts) {
                    enemyLife = enemyLife - (int) (10 * enemyDefence * playerAccuracy - multiplierForPlayer * playerPower);
                    enemyPokemon.getHp().setValue(enemyLife);
                    if (enemyLife <= 0) {
                        return;
                    }
                    playerPokemon.getHp().setValue(playerLife - (int) (10 * playerDefence * enemyAccuracy - multiplierForEnemy * enemyAttack.getPower()));
                }
                else {
                    playerLife = playerLife - (int) (10 * playerDefence * enemyAccuracy - multiplierForEnemy * enemyAttack.getPower());
                    playerPokemon.getHp().setValue(playerDefence);
                    if (playerLife <= 0) {
                        return;
                    }
                    enemyPokemon.getHp().setValue(enemyLife - (int) (10 * enemyDefence * playerAccuracy - multiplierForPlayer * playerPower));
                }
            }
        }
    }

    public Attack getRandomAttack(Pokemon pokemon) {
        var attack1 = ((Physical) pokemon.getType()).getAttack1();
        var attack2 = ((Physical) pokemon.getType()).getAttack2();
        var attack3 = ((Physical) pokemon.getType()).getAttack3();
        var attack4 = ((Physical) pokemon.getType()).getAttack4();

        List<Attack> attackList = Arrays.asList(attack1, attack2, attack3, attack4);
        return attackList.get(new Random().nextInt(attackList.size()));
    }

    public SpecialAttack getRandomSpecialAttack(Pokemon pokemon) {
        var attack1 = ((Special) pokemon.getType()).getSpecialAttack1();
        var attack2 = ((Special) pokemon.getType()).getSpecialAttack2();
        var attack3 = ((Special) pokemon.getType()).getSpecialAttack3();
        var attack4 = ((Special) pokemon.getType()).getSpecialAttack4();

        List<SpecialAttack> attackList = Arrays.asList(attack1, attack2, attack3, attack4);
        return attackList.get(new Random().nextInt(attackList.size()));
    }
}
