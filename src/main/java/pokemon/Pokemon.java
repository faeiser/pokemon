package pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Pokemon {
    private String name, typ1, typ2;
    private int id, generation;
    private double total, hp, attack, defense, spAtk, spDef, speed, MaxLifePoints = 150;
    private boolean legendary;

    void art() throws IOException {
        System.out.print("\t" + name.toUpperCase() + " Type(" + typ1 + "/" + typ2 + ")\n");
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("asciiArt/" + name +
                            ".txt"));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                System.out.println("\t" + currentLine);
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("asciiArt/PokemonLogo.txt"));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                System.out.println(currentLine);
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        System.out.println("------------------------------SELECTED------------------------------");
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyp1() {
        return typ1;
    }

    public void setTyp1(String typ1) {
        this.typ1 = typ1;
    }

    public String getTyp2() {
        return typ2;
    }

    public void setTyp2(String typ2) {
        this.typ2 = typ2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(double spAtk) {
        this.spAtk = spAtk;
    }

    public double getSpDef() {
        return spDef;
    }

    public void setSpDef(double spDef) {
        this.spDef = spDef;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMaxLifePoints() {
        return MaxLifePoints;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

}
