package lab5;

import java.util.ArrayList;
import java.util.List;
/**
 *  Noeud
 * responsabilite : gere les proprietees d'un noeud
 * Collaborateur :Tree
 */
public class LexiNode {

    private char lettre;
    private String motCourant;
    private List<String> definitions = new ArrayList<String>();
    private List<LexiNode> enfants = new ArrayList<LexiNode>();

    public LexiNode() {   }

    public LexiNode(char lettreRepresentante, String motCourant){
        this.lettre = lettreRepresentante;
        this.motCourant = motCourant;
    }

    /**
     * Permet d'obtenir la liste de définition contenant dans le <code>LexiNode</code>
     * @return definition - Liste de définitions
     */
    public List<String> getDefinition() {
        return definitions;
    }

    /**
     * Permet de mettre à jour la liste de définition du <code>LexiNode</code> courant. Un mot peut avoir plusieurs
     * définitions.
     * @param definitions une liste de définitions au chargement des données ou à mettre à jour.
     */
    public void setDefinition(List<String> definitions) {
        this.definitions = definitions;
    }

    /**
     * Retourne une liste de <code>LexiNode</code>, qui représente les enfants possibles du représentant.
     * @return une liste d'enfants <code>LexiNode</code>
     */
    public List<LexiNode> getEnfants() {
        return enfants;
    }

    /**
     * Return la lettre représentate à la position courante.
     * @return la lettre représentante du <code>LexiNode</code>
     */
    public char getLettre() {
        return lettre;
    }

    /**
     * Retourne le mot courant à la position présente à partir du root.
     * @return un mot courant à partir du root.
     */
    public String getMotCourant() {
        return motCourant;
    }

    /**
     * Vérifie si la définition existe dans la liste et le rajoute s'il n'existe pas.
     * @param definition une définition entrée par l'utilisateur.
     * complexite : o (log(n))
     */
    public void addDefinition(String definition) {
        boolean exist = false;
        for (String str : this.definitions) {
            if (str.equals(definition)) {
                exist = true;
            }
        }
        if (exist == false && !definition.equals("")) {
            this.definitions.add(definition);
        }
    }

    /**
     * Permet d'ajouter un <code>LexiNode</code> dans la liste des enfants du noeud.
     * @param newChild un <code>LexiNode</code> à rajouter dans la liste des enfants du noeud présent.
     */
    public void addChild(LexiNode newChild){
        this.getEnfants().add(newChild);
    }
}