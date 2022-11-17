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

        if (playerStarts) {
            System.out.println("Player HP: " + playerLife + "\nEnemy HP: "+ enemyLife);
        }
        else {
            System.out.println("Enemy HP: "+ enemyLife + "\nPlayer HP: " + playerLife);
        }

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
                    var decreaseLife = (int) (enemySpecialDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());

                    if (enemyPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = (int) (playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                }
                else {
                    var decreaseLife = (int) (playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                    if (playerPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = enemyLife - (int) (enemyDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());

                }
            }
            else {

                // special + physical

                enemyAttack = getRandomAttack(enemyPokemon);
                double enemyAccuracy = 1 - enemyAttack.getAccuracy().getValue();

                Integer enemyPower = enemyAttack.getPower();
                if (playerStarts) {
                    var decreaseLife = (int) (enemyDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());

                    if (enemyPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = (int) (playerDefence * enemyAccuracy - multiplierForEnemy * enemyPower);
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                }
                else {
                    var decreaseLife = (int) (playerDefence * enemyAccuracy - multiplierForEnemy * enemyPower);
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                    if (playerPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = (int) (enemyDefence * playerSpecialAccuracy - multiplierForPlayer * playerScpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());
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
                    var decreaseLife = (int) (enemyDefence * playerAccuracy - multiplierForPlayer * playerPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());

                    if (enemyPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = (int) (playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                }
                else {
                    var decreaseLife = (int) (playerSpecialDefence * enemySpecialAccuracy - multiplierForEnemy * enemySpecialPower);
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                    if (playerPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = (int) (enemyDefence * playerAccuracy - multiplierForPlayer * playerPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());

                }
            }
            else {

                // physical + physical

                enemyAttack = getRandomAttack(enemyPokemon);
                double enemyAccuracy = 1 - enemyAttack.getAccuracy().getValue();

                if (playerStarts) {
                    var decreaseLife = (int) (enemyDefence * playerAccuracy - multiplierForPlayer * playerPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());

                    if (enemyPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = (int) (playerDefence * enemyAccuracy - multiplierForEnemy * enemyAttack.getPower());
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                }
                else {
                    playerPokemon.getHp().setValue(playerDefence);
                    var decreaseLife = (int) (playerDefence * enemyAccuracy - multiplierForEnemy * enemyAttack.getPower());
                    decreaseOrIncreaseLife(decreaseLife, playerPokemon, playerLife);

                    System.out.println("\n\t=> Player HP: " + playerPokemon.getHp().getValue());

                    if (playerPokemon.getHp().getValue() <= 0) {
                        return;
                    }
                    decreaseLife = (int) (enemyDefence * playerAccuracy - multiplierForPlayer * playerPower);
                    decreaseOrIncreaseLife(decreaseLife, enemyPokemon, enemyLife);

                    System.out.println("\n\t=> Enemy HP: " + enemyPokemon.getHp().getValue());

                }
            }
        }
    }

    private void decreaseOrIncreaseLife(int decreaseLife, Pokemon pokemon, int enemyLife) {
        if (decreaseLife < 0) {
            enemyLife += decreaseLife;
            pokemon.getHp().setValue(enemyLife);
        }
        else {
            enemyLife -= decreaseLife;
            pokemon.getHp().setValue(enemyLife);
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
