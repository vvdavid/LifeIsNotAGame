package objects;

import javax.swing.JLabel;

public class Properties {

    private final JLabel births, deaths, generation;
    private final String counterFormat = "%,d";

    public Properties(JLabel birth, JLabel deaths, JLabel generation) {
        this.births = birth;
        this.deaths = deaths;
        this.generation = generation;
    }

    public void addBirth(int birthsCount) {
        this.births.setText(String.format(counterFormat, birthsCount));
    }

    public void addDeath(int deathCount) {
        this.deaths.setText(String.format(counterFormat, deathCount));
    }

    public void addGeneration(int generationCount) {
        this.generation.setText(String.format(counterFormat, generationCount));
    }
}
