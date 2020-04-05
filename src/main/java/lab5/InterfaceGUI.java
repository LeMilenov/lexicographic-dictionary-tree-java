package lab5;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;

import java.util.ArrayList;

/**
 * L'interface graphique du dictionnaire
 */
public class InterfaceGUI extends JFrame {
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JScrollPane scrollPane;
    private JButton save, load, addSave;
    private JTextField nameSearch;
    private JList leftList;
    private JList rightList;
    private DefaultListModel leftModel;
    private DefaultListModel rightModel;
    private JTextArea def;

    public InterfaceGUI() {
        initializeButtons();
        initializePanels();
    }

    /**
     * Initialize les buttons de l'interface
     */
    private void initializeButtons() {
        save = new JButton("Enregistrer"); // Création bouton enregistrement
        load = new JButton("Charger"); // Création bouton enregistrement
        addSave = new JButton("ajouter/modifier"); // Création de boutons de modifier
    }

    /**
     * Initialize les panneaux de l'interface
     */
    private void initializePanels() {
        setLocationRelativeTo(null); // positionne la fenêtre au centre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // En fermant la fenêtre le programme termine l'exécution
        this.setLayout(new BorderLayout()); // définir le layout comme un JFrame

        // north panel pour les deux boutons charger et enregistrer
        topPanel = new JPanel();
        topPanel.add(load);
        topPanel.add(save);
        this.add(topPanel, BorderLayout.NORTH); // On positione le panel en haut

        leftPanel = new JPanel(new GridLayout(2, 1));
        nameSearch = new JTextField(1);
        leftList = new JList();
        leftModel = new DefaultListModel<String>();
        leftList.setModel(leftModel);
        scrollPane = new JScrollPane(leftList);
        leftPanel.add(nameSearch);
        scrollPane.setBounds(5, 5, 100, 200);
        leftPanel.add(scrollPane);
        this.add(leftPanel, BorderLayout.WEST); // Ajout du panel dans le JFrame de gauche
        
        // Def
        centerPanel = new JPanel(new GridLayout(1,1));
        def = new JTextArea(100, 100);
        def.setEditable(true);
        centerPanel.setSize(200, 200);
        centerPanel.setMinimumSize(new Dimension(250, 250));
        centerPanel.setMaximumSize(new Dimension(250, 250));
        centerPanel.add(def);

        this.add(centerPanel, BorderLayout.CENTER);

        // Allwords
        rightPanel = new JPanel(new GridLayout(1, 1));
        rightList = new JList();
        rightModel = new DefaultListModel<String>();
        rightList.setModel(rightModel);
        scrollPane = new JScrollPane(rightList);
        scrollPane.setBounds(5, 5, 100, 200);
        rightPanel.add(scrollPane);

        this.add(rightPanel, BorderLayout.EAST);

        // button vers la fin qui permet de modifier et d'ajouter des mots dans le
        // dictionnaire
        bottomPanel = new JPanel(new GridLayout(1, 1));
        bottomPanel.add(addSave);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Initialize la liste de gauche pour les <code>Mot</code> possible à partir de la saisie de l'utilisateur.
     * @param list la liste de mot possible
     */
    public void setLeftList(ArrayList<Mot> list) {
        leftModel.clear();
        for (Mot mot : list) {
            leftModel.addElement(mot.getMot());
        }
        this.leftList.updateUI();;
    }

    /**
     * Initialize la liste de tout les <code>Mot</code> existant dans <code>Tree</code>
     * @param list la liste de mot existant
     */
    public void setRightList(ArrayList<String> list) {

        rightModel.clear();
        for (String str : list) {
            rightModel.addElement(str);
        }
        this.rightList.updateUI();
    }

    /**
     * Met à jour la définition et met à jour l'interface graphique avec les nouvelles données.
     * @param definition sur le mot que l'on veut attribuer
     */
    public void setDefinition(String definition) {
        if (!definition.equals("")) {
            this.def.setText(definition);
            this.def.updateUI();
        }
    }

    /**
     * Retire tous les éléments de la liste des mots possible
     */
    public void resetAvailablewords() {
        leftModel.clear();
    }

    /**
     * Permet d'accéder la saisie de l'utilisateur pour la recherche d'un mot.
     * @return le <code>JTextField</code> de l'interface graphique.
     */
    public JTextField getTypedWordBox() {
        return nameSearch;
    }

    /**
     * Permet d'accéder la saisie de l'utilisateur pour la définition d'un mot.
     * @return le <code>JTextArea</code> de l'interface graphique.
     */
    public JTextArea getDefinitionBox() {
        return def;
    }

    /**
     * Permet d'accéder le button de chargement à partir d'un fichier.
     * @return le <code>JButton</code> pour la fonction chargement.
     */
    public JButton getLoadButton() {
        return load;
    }

    /**
     * Permet d'accéder le button de sauvegarde de l'état d'arbre.
     * @return le <code>JButton</code> pour la sauvegarde de l'état courante du <code>Tree</code>
     */
    public JButton getSaveButton() {
        return save;
    }

    /**
     * Permet d'accéder le button d'ajout ou de modification d'un mot ou d'une définition
     * @return le <code>JButton</code> pour l'ajout ou la modification
     */
    public JButton getModifyAddButton() {
        return  addSave;
    }
}