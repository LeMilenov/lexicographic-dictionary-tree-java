package lexicoTree;
import java.util.ArrayList;

/**
 * arbre lexicographique (LexiNode = noeud)
 * responsabilite : Gere la structure des noeuds, permet : rajouter/modifier Lexinodes
 * collaborateurs : LexiNode, Controleur, Mot
 */
public class Tree{

    //root est un LexiNode null sur lequel on rajoute tout les autres noeuds
    private LexiNode root;
    //focus est le LexiNode de reference courant (permet de naviguer dans l`arbre)
    private LexiNode focus;
    // creation d`un arbre par defaut
    public Tree(){
        // LexiNode est null car, on veux seulement utiliser ses enfants
        root = new LexiNode();
    }

    /**
     * Ajoute un <code>Mot</code> dans l'arbre char par char pour créer l'arborescence d`un mot.
     * @param word un <code>Mot</code> à rajouter dans l'arbre.
     * complexite : o(log(n))
     */
    public void addWord(Mot word){
        //commencer au root
        focus = root;

        String motCourant = "";
        char[] charArray = word.getMot().toCharArray();
        //pour chaque lettre du Mot
        for(char lettre : charArray ){
            // on verifie que la char courante n`est pas egale a ""
            if(Character.getNumericValue(lettre) == -1){
                continue;
            }
            //update motCourant
            motCourant += lettre;
            //parcourir le dictionnaire pour verifier que cette char existe avec goToWantedChild
            if(!goToWantedChild(lettre)){
                // si existe pas -> on rajoute un enfant avec la lettre et la valeur du motCourant curant
                LexiNode nouveauNode = new LexiNode(lettre,motCourant);
                focus.addChild(nouveauNode);
                // on change le focus pour qu`il soit a cette nouvelle position
                focus = nouveauNode;
            }
        }
        //quand le focus est au bout du mot voulu :
        //rajouter la definition au focus si elle n`existe pas encore
        boolean definitionNonExistante = true;
        for(String s : focus.getDefinition()){
            definitionNonExistante = !s.equalsIgnoreCase(word.getDefinition());
        }
        if(definitionNonExistante){
            focus.addDefinition(word.getDefinition());
        }
    }

    /**
     * Permet de modifier la liste de définition d'un <code>Mot</code> sur le <code>LexiNode</code> en focus.
     * @param mot le mot recherché pour la modification de définitions.
     * @param definitions une liste de définitions.
     */
    public void updateWord(String mot, ArrayList<String> definitions){
        //va au noeud final du mot
        goToWord(mot);
        //modifie les definitions du noeud courant
        focus.setDefinition(definitions);
    }

    /**
     * Vérifie si l'enfant recherchée du <code>LexiNode</code> courant existe et met le focus sur l'enfant.
     * @param lettre un char qui permet de changer de focus vers l'enfant désiré
     * @return l'existance de l'enfant.
     * complexite : o (log(n))
     */
    public boolean goToWantedChild(char lettre){
        // pour tous les enfants du focus
        for(LexiNode enfant : focus.getEnfants()){
            //on verifie si l`enfant courant contient la lettre voulue
            if(enfant.getLettre() == lettre){
                //changer le focus pour la nouvelle position
                focus = enfant;
                return true;
            }
        }
        // la lettre n`a pas ete trouver
        return false;
    }
    /**
     * affiche une liste des mots possible a partir d`un noeud specifique dans l`arbre
     * @param motCourant une chaìne de caractère pour parcourrir l'arbre
     * @return une liste de <code>Mot</code> contenant une définition dans le <code>Tree</code>
     * complexite :  o (log(n))
     */
    public ArrayList<Mot> showPossibleWords(String motCourant){
        // uniformiser le motCourant
        motCourant = motCourant.toUpperCase().trim();
        ArrayList<Mot> motsPossibles = new ArrayList<Mot>();
        // se rendre au noeud de depart
        goToWord(motCourant);
        // si on est au root et que le mot entree n`est pas "" -> le motCourant n`existe pas
        if(focus == root && motCourant.length() > 0){
            // on retourne l`array vide
            return motsPossibles;
        }
        // pour chaque enfant - > showPossibleWords(motCourant)
        for(LexiNode enfantCourant : focus.getEnfants()){
            // si l`enfant a pas d`enfant  ou qu`il possede une definiton -> un mot a rajouter dans notre array
            if(enfantCourant.getEnfants().isEmpty() || !enfantCourant.getDefinition().isEmpty()){
                //creer definition pour chaque mot
                for(String def : enfantCourant.getDefinition()){
                    // ajouter un mot pour chaque definition different
                    motsPossibles.add(new Mot(enfantCourant.getMotCourant(), def));
                }
            }
            // si on a pas de mot au noeud courant, on reessaye avec les enfants du noeud
            for(Mot motPossible : showPossibleWords(motCourant + enfantCourant.getLettre())){
                motsPossibles.add(motPossible);
            }
        }
        return motsPossibles;
    }

    /**
     * Permet de parcourrir l'arbre char par char et se rendre au mot désiré.
     * @param word une chaine de caractères saisie par l'utilisateur
     * complexite :  o (log(n))
     */
    public void goToWord(String word){
        // commencer a la position root
        focus = root;
        for(char lettre :  word.toCharArray()){
            //parcourir le dictionnaire pour verifier que cette char existe -> goToWantedChild parcours et return false ou true
            goToWantedChild(lettre);
        }
    }

    /**
     * Parcour l'arborescence et trouve tout les <code>Mot</code> existant dans l'arbre.
     * @return la liste de <code>Mot</code> possible dans l'arbre.
     */
    public ArrayList<Mot> getWordsArray(){
        return showPossibleWords("");
    }

    /**
     * Reinitialise le noeud root -> efface la references a tout les enfants
     */
    public void clear(){
        root = new LexiNode();
    }

    /**
     * Parcour l'arborescence <code>Tree</code> pour se rendre au noeud contenant la String passé en paramètre. S'il existe dans l'arbre,
     * un <code>Mot</code> est retourné.
     * @param word une chaine de caractère recherchée saisie par l'utilisateur
     * @return un <code>Mot</code> avec ses définitions contenant dans l'arbre si il existe sinon null.
     * complexite :  o (log(n)) + log(n)
     */
    public Mot getMot(String word){
        goToWord(word);
        String definitions = "";
        for(String s :focus.getDefinition()){
            definitions += s +"\n";
        }
        //si la definiton est "" -> pas de mot existant
        return (definitions.length() > 0) ? new Mot(focus.getMotCourant(), definitions) : null;
    }

}