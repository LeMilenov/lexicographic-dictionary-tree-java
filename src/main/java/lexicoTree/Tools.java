package lexicoTree;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe avec les outils de sauvegarde et de chargement
 * responsabilite : Lire et sauvegarder un fichier et retourner sa valeur sous un format specifique
 * Collaborateur : Controleur
 */
public class Tools {
    public static JFileChooser fileChooser = new JFileChooser();

    /**
     * Permet de charger le contenu d'un fichier et de recréer l'arborescence des
     * mots pour l'utilisation de l'interface graphique.
     * 
     * @return une liste de <code>Mot</code>
     */
    public static ArrayList<Mot> readFile() {
        ArrayList<Mot> list = new ArrayList<>();

        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                File aFile = new File(file.getAbsolutePath());
                Scanner scanner = new Scanner(aFile);
                while (scanner.hasNextLine()) {
                    String[] str = scanner.nextLine().split(" & ");
                    // System.out.println(str[0] + " : " + str[1]);
                    list.add(new Mot(str[0], str[1]));
                }
                scanner.close();
            } catch (FileNotFoundException f) {
                System.out.println("Error occured, please try again.");
            }
        }

        return list;
    }

    /**
     * Permet de sauvegarde l'état actuel de l'arbre dans un fichier avec un nom
     * saisie par l'utilisateur.
     * Source : https://www.homeandlearn.co.uk/java/save_file_dialogue_box.html
     * Source : https://stackoverflow.com/questions/17010647/set-default-saving-extension-with-jfilechooser#17011063
     * @param words la lsite de <code>Mot</code> à ajouter.
     */
    public static void saveFile(ArrayList<Mot> words) {
        try {
            JFileChooser save = new JFileChooser();
            FileWriter writer;
            save.setSelectedFile(new File("MonDictio.txt"));
            FileNameExtensionFilter txtFileFilter = new FileNameExtensionFilter("Text Files", "txt");
            save.addChoosableFileFilter(txtFileFilter);
            int returnVal = save.showSaveDialog(null);
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File saved_file = save.getSelectedFile();
                String file_name = saved_file.toString();
                if (!file_name.endsWith(".txt"))
                    file_name += ".txt";
                writer = new FileWriter(file_name);
                for (Mot m : words) {
                    for (String str : m.getDefinition().split("\n")) {
                        writer.append(m.getMot() + " & " + str + "\n");
                    }
                }
                writer.close();
                JOptionPane.showMessageDialog(null, "Fichier sauvegardé");
            }
            
        } catch (Exception e) {
        }
    }
}