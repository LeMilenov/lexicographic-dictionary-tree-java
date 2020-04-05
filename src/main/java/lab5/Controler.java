package lab5;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.*;

/**
 * Le controleur qui contient tout les autres composantes.
 * <ul>
 *     <li><code>Tree</code></li>
 *     <li><code>InterFaceGUI</code></li>
 * </ul>
 * Responsabilite : fait le lien entre l`arbre et l`interface
 * Collaborateur : Tree, Mot, InterfaceGUI, Tools
 */
public class Controler {
    private Tree dictionnaire ;
    private InterfaceGUI m_interface;

    /**
     * le constructeur:
     *  - intialise l`arbre et l`interface
     *  - donne les valeurs par defaut de l`interface
     *  - rajouter des listeners sur les boutons et sur le textBox
     * 
     */
    public Controler(){
        dictionnaire = new Tree();
        m_interface =  new InterfaceGUI();
        m_interface.setTitle("Dictio");
        m_interface.setSize(800,400);
        m_interface.setVisible(true);


        m_interface.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
                updateEventChange();
            }
        });

        m_interface.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
                updateEventChange();
            }
        });
        m_interface.getModifyAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCurrentWord();
            }
        });
        //afficher les mots possibles quand on ecrit un mot dans le textbox
        m_interface.getTypedWordBox().addKeyListener( new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                System.out.println("stopped typing");
                m_interface.resetAvailablewords();
                updateEventChange();
            }
        });

    }

    /**
     * Permet de modifier la liste des <code>Mot</code> possible à gauche ainsi que de modifier la définition:
     * - vas cherche le mot ecrit
     * - affiche les mots possibles selon le mot ecrit
     * - affiche la definition si le mot est defini
     * - affiche touts les mots possibles 
     */
    private void updateEventChange() {
        String motEcrit = m_interface.getTypedWordBox().getText().toUpperCase();

        // regarder si le motEcrit existe deja
        Mot motTrouver = dictionnaire.getMot(motEcrit);
        // si le mot n`existe pas -> on affiche les mots possibles
        if( motTrouver == null){
            //afficher les mots possibles dans un JList
            m_interface.getDefinitionBox().setText("");
        }else{
            // afficher le mot trouver + clear leftList
            m_interface.setDefinition(motTrouver.getDefinition());
            // m_interface.setLeftList(new JList());
        }
        m_interface.setLeftList(dictionnaire.showPossibleWords(motEcrit));
        showDictionnaire();

    }
    /**
     * Permet de choisir un fichier et de le charger dans notre dictionnaire (<code>Tree</code>).
     *
     */
    public void readFile(){
        dictionnaire.clear();
        ArrayList<Mot> mots =  Tools.readFile();
        for (Mot unMot : mots) {
            dictionnaire.addWord(unMot);
        }
        updateEventChange();
    }
    /**
     * Permet de sauvegarder l'état de <code>Tree</code> dans un fichier avec un nom spécifié.
     */
    public void saveFile(){
        Tools.saveFile(dictionnaire.getWordsArray());
    }

    /**
     * Met à jour la liste de gauche avec les <code>Mot</code> possible.
     */
    public void showDictionnaire(){
        ArrayList<String> motsPourAffichage = new ArrayList<String>();
        //show only the names
        for(Mot mot :dictionnaire.getWordsArray()){
            //si ils ne contient pas deja le mot -> le rajoute
            if(!motsPourAffichage.contains(mot.getMot()))
                motsPourAffichage.add(mot.getMot());
        }
        //show all words in rightList (we have to cast them to a JList first)
        m_interface.setRightList(motsPourAffichage);
    }


    /**
     * Permet de mettre à jour la définition du <code>Mot</code> en saisie. Il y a deux cas possible :
     * <ol>
     *     <li>Si le <code>Mot</code> n'existe pas, <code>Mot</code> est créé avec sa définition
     *     et ajouté dans <code>Tree</code></li>
     *     <li>Si le <code>Mot</code> existe dans (<code>Tree</code>), la définition serait modifiée.
     *     la définition serait ajoutée au mot.</li>
     * </ol>
     */
    public void updateCurrentWord(){
        //check if word and definition are safe
        String motEcrit = m_interface.getTypedWordBox().getText();
        String definitionEcrit = m_interface.getDefinitionBox().getText().toString();
        System.out.println(definitionEcrit);
        if (definitionEcrit.isEmpty()) {
            JOptionPane.showMessageDialog(m_interface, "Veuillez entrer une definition pour le mot.");
        } else {
            ArrayList<String> definitionsEcrites = new ArrayList<String>();
            definitionsEcrites.add(definitionEcrit);

            boolean exist = false;
            ArrayList<Mot> list = dictionnaire.getWordsArray();
            for (Mot mot : list) {
                if (mot.getMot().equals(motEcrit)) {
                    exist = true;
                }
            }

            if (exist) {
                dictionnaire.updateWord(motEcrit, definitionsEcrites);
            } else {
                dictionnaire.addWord(new Mot(motEcrit, definitionEcrit));
            }

        }
        updateEventChange();
    }


}