package lab5;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void addWordTest() {
        Tree testTree = new Tree();
        Mot testWord = new Mot("Cat", "Nothing but mammals");
        testTree.addWord(testWord);
        Mot wordFound = (testTree.showPossibleWords("cA")).get(0);
        assertEquals((wordFound.getMot().equals("Cat") && wordFound.getDefinition().equals("Nothing but mammals")), true);

    }
    @Test 
    public void updateWordTest() {
        boolean def1 = false;
        Tree testTree = new Tree();
        Mot testWord = new Mot("Cat", "Nothing but mammals");
        testTree.addWord(testWord);
        Mot wordFound = (testTree.showPossibleWords("cA")).get(0);
        ArrayList<String> def = new ArrayList<String>();
        def.add(new String("bepop bepop"));
        testTree.updateWord(wordFound.getMot(), def);
        if (testTree.getWordsArray().get(0).getDefinition().equals("bepop bepop"))  {
            def1 = true;
        }
        assertEquals(def1, true);
    }
    @Test
    public void goToWantedChildTest() {
        boolean cond1 = false;
        boolean cond2 = false;
        Tree testTree = new Tree();
        testTree.addWord(new Mot("Cat", "Nothing but mammals"));
        testTree.addWord(new Mot("Catalogue", "Livre de présentation"));
        if (testTree.goToWantedChild('c')) {
            cond1 = true;
        }
        if (testTree.goToWantedChild('a')) {
            cond2 = true;
        }
        assertEquals(cond1 && cond2, true);
    }

    @Test 
    public void clearTest() {
        Tree testTree = new Tree();
        testTree.addWord(new Mot("Cat", "Nothing but mammals"));
        testTree.addWord(new Mot("Catalogue", "Livre de présentation"));
        testTree.clear();
        assertEquals(testTree.getWordsArray().isEmpty(), true);
    }

}
