package com.example.pokemon_ts;

import com.example.pokemon_ts.numbersgenerator.Physical;
import com.example.pokemon_ts.numbersgenerator.Special;
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
		while (i <= 100000) {
			System.out.println("\n----------------------------- CASE " + i + " -----------------------------\n");

			i++;

			Pokemon enemyPokemon = pokemonService.getNewPokemon();
			Pokemon playerPokemon = pokemonService.getNewPokemon();

			var multiplierForPlayer = getMultiplier(playerPokemon, enemyPokemon);
			var multiplierForEnemy = getMultiplier(enemyPokemon, playerPokemon);

			displayPokemonInformation(playerPokemon, multiplierForPlayer, 1);
			displayPokemonInformation(enemyPokemon, multiplierForEnemy, 2);

			if (enemyPokemon.getSpeed().getValue() < playerPokemon.getSpeed().getValue()) {
				playerStarts = true;
				System.out.println("-------> Player starts");
			} else {
				System.out.println("-------> Enemy starts");
			}

			BattleRound battleRound = new BattleRound();

			Integer playerLife;
			Integer enemyLife;

			int round =0;

			while (true) {
				round++;
				playerLife = playerPokemon.getHp().getValue();
				enemyLife = enemyPokemon.getHp().getValue();

				if (battleIsFinished(playerLife, enemyLife)) {
					break;
				}
				System.out.println("\n=========== Round " + round + " ===========\n");
				battleRound.fight(playerPokemon, enemyPokemon, playerStarts, multiplierForPlayer, multiplierForEnemy);
			}
			if (checkIfPlayerWon(playerLife)) {
				favourable++;
				System.out.println("\nPLAYER WON! :)");
			}
			else {
				System.out.println("\nPLAYER LOST! :(");
			}
			total++;
		}

		System.out.println("\n-----------------------------------------------------------------------\n\n\tPROBABILITATEA DE A CASTIGA: \t\t"
				+ favourable / total
				+ "\n\n-----------------------------------------------------------------------"
		);
	}

	private void displayPokemonInformation(Pokemon pokemon, double multiplier, int nr) {
		var typeOfPlayer = pokemon.getType();
		System.out.println((nr == 1 ? "Player pokemon:\n" : "Enemy Pokemon:\n")
						+ "\n| HP:" + pokemon.getHp().getValue()
						+ "\n| Speed: " + pokemon.getSpeed().getValue()
						+ "\n| Type: " + pokemon.getTypeName() + (pokemon.getType() instanceof Special ? " (Special)" : " (Physical)")
						+ (typeOfPlayer instanceof Special
						? "\n| Special Attack 1: " + ((Special) typeOfPlayer).getSpecialAttack1().getPower() + "\n\t| Accuracy: " + ((Special) typeOfPlayer).getSpecialAttack1().getAccuracy().getValue()
						: "\n| Physical Attack 1: " + ((Physical) typeOfPlayer).getAttack1().getPower() + "\n\t| Accuracy: " + ((Physical) typeOfPlayer).getAttack1().getAccuracy().getValue()
				)
						+ (typeOfPlayer instanceof Special
						? "\n| Special Attack 2: " + ((Special) typeOfPlayer).getSpecialAttack2().getPower() + "\n\t| Accuracy: " + ((Special) typeOfPlayer).getSpecialAttack2().getAccuracy().getValue()
						: "\n| Physical Attack 2: " + ((Physical) typeOfPlayer).getAttack2().getPower() + "\n\t| Accuracy: " + ((Physical) typeOfPlayer).getAttack2().getAccuracy().getValue()
				)
						+ (typeOfPlayer instanceof Special
						? "\n| Special Attack 3: " + ((Special) typeOfPlayer).getSpecialAttack3().getPower() + "\n\t| Accuracy: " + ((Special) typeOfPlayer).getSpecialAttack3().getAccuracy().getValue()
						: "\n| Physical Attack 3: " + ((Physical) typeOfPlayer).getAttack3().getPower() + "\n\t| Accuracy: " + ((Physical) typeOfPlayer).getAttack3().getAccuracy().getValue()
				)
						+ (typeOfPlayer instanceof Special
						? "\n| Special Attack 4: " + ((Special) typeOfPlayer).getSpecialAttack4().getPower() + "\n\t| Accuracy: " + ((Special) typeOfPlayer).getSpecialAttack4().getAccuracy().getValue()
						: "\n| Physical Attack 4: " + ((Physical) typeOfPlayer).getAttack4().getPower() + "\n\t| Accuracy: " + ((Physical) typeOfPlayer).getAttack4().getAccuracy().getValue()
				)
						+ "\n| Special Defence: " + pokemon.getSpecialDefence().getStrength()
						+ "\n| Physical Defence: " + pokemon.getDefence().getStrength()
						+ "\n| Multiplier: " + multiplier
						+ "\n"
		);
	}

	private boolean battleIsFinished(Integer playerLife, Integer enemyLife) {
		return playerLife <= 0 || enemyLife <= 0;
	}

	public boolean checkIfPlayerWon(int hp) {
		return hp > 0;
	}

	public Double getMultiplier(Pokemon playerPokemon, Pokemon enemyPokemon) {
		var multiplierMatrix = List.of(
				List.of(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0.5, 0d, 0d, 0d, 0.5, 0d),
				List.of(0d, 0.5, 0.5, 0d, 2d, 2d, 0d, 0d, 0d, 0d, 0d, 2d, 0.5, 0d, 0.5, 0d, 2d, 0d),
				List.of(0d, 2d, 0.5, 0d, 0.5, 0d, 0d, 0d, 2d, 0d, 0d, 0d, 2d, 0d, 0.5, 0d, 0d, 0d),
				List.of(0d, 0d, 2d, 0.5, 0.5, 0d, 0d, 0d, 0d, 2d, 0d, 0d, 0d, 0d, 0.5, 0d, 0d, 0d),
				List.of(0d, 0.5, 2d, 0d, 0.5, 0d, 0d, 0.5, 2d, 0.5, 0d, 0.5, 2d, 0d, 0.5, 0d, 0.5, 0d),
				List.of(0d, 0.5, 0.5, 0d, 2d, 0.5, 0d, 0d, 2d, 2d, 0d, 0d, 0d, 0d, 2d, 0d, 0.5, 0d),
				List.of(2d, 0d, 0d, 0d, 0d, 2d, 0d, 0.5, 0d, 0.5, 0.5, 0.5, 2d, 0d, 0d, 2d, 2d, 0.5),
				List.of(0d, 0d, 0d, 0d, 2d, 0d, 0d, 0.5, 0.5, 0d, 0d, 0d, 0.5, 0.5, 0d, 0d, 0d, 2d),
				List.of(0d, 2d, 0d, 2d, 0.5, 0d, 0d, 2d, 0d, 0d, 0d, 0.5, 2d, 0d, 0d, 0d, 2d, 0d),
				List.of(0d, 0d, 0d, 0.5, 2d, 0d, 2d, 0d, 0d, 0d, 0d, 2d, 0.5, 0d, 0d, 0d, 0.5, 0d),
				List.of(0d, 0d, 0d, 0d, 0d, 0d, 2d, 2d, 0d, 0d, 0.5, 0d, 0d, 0d, 0d, 0d, 0.5, 0d),
				List.of(0d, 0.5, 0d, 0d, 2d, 0d, 0.5, 0.5, 0d, 0.5, 2d, 0d, 0d, 0.5, 0d, 2d, 0.5, 0.5),
				List.of(0d, 2d, 0d, 0d, 0d, 2d, 0.5, 0d, 0.5, 2d, 0d, 2d, 0d, 0d, 0d, 0d, 0.5, 0d),
				List.of(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 2d, 0d, 0d, 2d, 0d, 0.5, 0d, 0d),
				List.of(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 2d, 0d, 0.5, 0d),
				List.of(0d, 0d, 0d, 0d, 0d, 0d, 0.5, 0d, 0d, 0d, 2d, 0d, 0d, 2d, 0d, 0.5, 0d, 0.5),
				List.of(0d, 0.5, 0.5, 0.5, 0d, 2d, 0d, 0d, 0d, 0d, 0d, 0d, 2d, 0d, 0d, 0d, 0.5, 2d),
				List.of(0d, 0.5, 0d, 0d, 0d, 0d, 2d, 0.5, 0d, 0d, 0d, 0d, 0d, 0d, 2d, 2d, 0.5, 0d)
		);

		return multiplierMatrix.get(getTypeNumber(playerPokemon) - 1).get(getTypeNumber(enemyPokemon) - 1);
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
