package pokemon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Main {
    private static List<Pokemon> pokelist;
    private static int playerSelection, aiSelection;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) throws IOException, FileNotFoundException {
        csvReader();
        wellcome();
        choosePokemon();
        statsAndAttack();
        scanner.close();
    }

    /*
     * CSVReader
     */
    public static void csvReader() {
        try {
            CSVReader reader = new CSVReaderBuilder(
                    new FileReader("PokemonList/pokemon.csv")).withSkipLines(1). // Skiping
            // firstline
            // as it is
            // header
                            build();
            pokelist = reader.readAll().stream().map(data -> {
                Pokemon csvObject = new Pokemon();
                csvObject.setId(Integer.parseInt(data[0]));
                csvObject.setName(data[1]);
                csvObject.setTyp1(data[2]);
                csvObject.setTyp2(data[3]);
                csvObject.setTotal(Double.parseDouble(data[4]));
                csvObject.setHp(Double.parseDouble(data[5]));
                csvObject.setAttack(Double.parseDouble(data[6]));
                csvObject.setDefense(Double.parseDouble(data[7]));
                csvObject.setSpAtk(Double.parseDouble(data[8]));
                csvObject.setSpDef(Double.parseDouble(data[9]));
                csvObject.setSpeed(Double.parseDouble(data[10]));
                csvObject.setGeneration(Integer.parseInt(data[11]));
                csvObject.setLegendary(Boolean.parseBoolean(data[12]));
                return csvObject;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * wellcome
     */
    static void wellcome() throws IOException {
        System.out.println("----------------------------WELLCOME TO----------------------------");
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("asciiArt/PokemonLogo.txt"));
        String currentLine = bufferedReader.readLine();
        while (currentLine != null) {
            System.out.println(currentLine);
            currentLine = bufferedReader.readLine();
        }
        bufferedReader.close();
        System.out.println("------------------------------THE GAME------------------------------");
        System.out.println();
    }

    /*
     * choosePokemon
     */
    static void choosePokemon() throws IOException {
        // Print slection
        boolean inputOK = false;
        inputOK = false;
        System.out.println("-----------------------choose your Pokemon------------------------");
        System.out.println();
        int i = 9;
        for (Pokemon p : pokelist) {
            System.out.print(pokelist.indexOf(p) + 1 + ". " + p.getName() + " \t\t");
            if (p.getId() == i) {
                System.out.println();
                i += 9;
            }
        }

        System.out.println();
        // Player selection
        do {
            try {
                playerSelection = scanner.nextInt() - 1;
                inputOK = true;
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Selection does not exist!");
                inputOK = false;
                scanner.nextLine();
            }
        } while (!numberIn(playerSelection, 0, pokelist.size() - 1) && !inputOK);

        System.out.println();
        // Show Pokemon art
        pokelist.get(playerSelection).art();
        System.out.print(" VS ");
        // AI selection
        aiSelection = random.nextInt(pokelist.size() - 1);
        // Show Pokemon art
        pokelist.get(aiSelection).art();
        // System.out.println();
    }

    /*
     * stats & attack
     */
    static void statsAndAttack() {
        System.out.println("\nLet the rumble begin!\n");
        while (pokelist.get(playerSelection).getHp() > 0 && pokelist.get(aiSelection).getHp() > 0) {
            // name & HP
            System.out.print(pokelist.get(playerSelection).getName());
            System.out.print(" (" + pokelist.get(playerSelection).getHp()
                    + "/");
            System.out.print(pokelist.get(playerSelection).getMaxLifePoints() +
                    " HP)\t\tvs.\t");
            System.out.print(pokelist.get(aiSelection).getName());
            System.out.print(" (" + pokelist.get(aiSelection).getHp() +
                    "/");
            System.out.print(pokelist.get(aiSelection).getMaxLifePoints() +
                    " HP)\n");
            System.out.print("(1)" + pokelist.get(playerSelection).getAttack() + "\t\t\t\t\t");
            System.out.print(pokelist.get(aiSelection).getAttack() + "\n");
            System.out.print("(2)" + pokelist.get(playerSelection).getAttack() + "\t\t\t\t\t");
            System.out.print(pokelist.get(aiSelection).getAttack() + "\n");

            // Attack
            int playerAttack = 0, aiAttack;

            // player slecteAttack
            boolean inputOK = false;
            inputOK = false;
            do {
                try {
                    playerAttack = scanner.nextInt();
                    inputOK = true;
                } catch (Exception e) {
                    inputOK = false;
                    scanner.nextLine();
                }
            } while (!numberIn(playerAttack, 1, 2) && !inputOK);

            // ai slecteAttack
            aiAttack = 1 + random.nextInt(2);
            // TODO iaAttak

            // dmg calculation
            double playerDmg = damageCalc(pokelist.get(playerSelection).getAttack(),
                    pokelist.get(aiSelection).getDefense()),
                    aiDmg = damageCalc(pokelist.get(aiSelection).getAttack(),
                            pokelist.get(playerSelection).getDefense());
            // player attack
            System.out.print(
                    pokelist.get(playerSelection).getName() +
                            " attacks " +
                            pokelist.get(aiSelection).getName() +
                            " and deals ");
            if (playerDmg > 0) {
                System.out.print(playerDmg + " damage.\n");
                Double hp = pokelist.get(aiSelection).getHp() - playerDmg;
                pokelist.get(aiSelection).setHp(roundAvoid(hp, 2));
            } else {
                System.out.print(" 0 damage.\n");
            }
            // ai attack
            System.out.print(
                    pokelist.get(aiSelection).getName() +
                            " attacks " +
                            pokelist.get(playerSelection).getName() +
                            " and deals ");
            if (aiDmg > 0) {
                System.out.print(aiDmg + " damage.\n");
                Double hp = pokelist.get(playerSelection).getHp() - aiDmg;
                pokelist.get(playerSelection).setHp(roundAvoid(hp, 2));
            } else {
                System.out.print(" 0 damage.\n");
            }
        }
        if (pokelist.get(aiSelection).getHp() < 0) {
            System.out.println(pokelist.get(playerSelection).getName() + " has won! :D");
        } else {
            System.out.println(pokelist.get(playerSelection).getName() + " has lost! :(");
        }
    }

    /*
     * dmg calculation
     */
    static Double damageCalc(Double a, Double d) {
        double attackModifier = random.nextDouble(),
                defenseModifier = random.nextDouble() * .5;
        // System.out.println(attackModifier + " " + defenseModifier);
        double dmg = (attackModifier * a) - (defenseModifier * d);
        return roundAvoid(dmg, 2);
    }

    /*
     * round void
     */
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    /*
     * number control input
     */
    static boolean numberIn(int selection, int a, int b) {
        if (selection >= a && selection <= b) {
            return true;
        } else {
            System.out.println("Selection does not exist!");
            return false;
        }
    }

}
