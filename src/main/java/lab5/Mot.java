package lab5;

/**
 * Objet contenant le mot et une définition
 */
public class Mot {
    private String mot;
    private String definition;

    public Mot(String mot, String definition) {
        this.mot = mot.trim().toUpperCase();
        this.definition = definition;
    }

    public String getMot() {
        return mot;
    }

    public String getDefinition() {
        return definition;
    }

    public String toString() {
        return mot + " & " + definition;
    }
}