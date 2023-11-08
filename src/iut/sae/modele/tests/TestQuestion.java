/*
 * TestQuestion.java                                    17 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.tests;

import java.util.ArrayList;

import iut.sae.modele.Categorie;
import iut.sae.modele.Question;

/** 
 * test de la classe Question
 * @author djedline.boyer
 *
 */
public class TestQuestion {
    
         private static ArrayList<Question> listeQuestion = new ArrayList<>();
         private static String[] listeLibelle = {"Quelle question ?","   ",""};
         private static Categorie nomCategorie = new Categorie("absurde");
         private static String propoJuste = "Bonne question ?";
         private static String[] propoFausse = {"J'ai pas la réponse","J'ai la réponse",
         "c'est quoi cette question ?"};
         private static String feedback = "La réponse est 'Bonne question ?' car"
                 +"la reponse est compliqué et que c'est réellement" 
                 + " une bonne question";
         private static int diff = 2;
        
         /** 
         * Test constructeur question
         */
        public static void testQuestion() {
            Question exempleQuestion;
            try {
                listeQuestion.add(new Question(listeLibelle[0], nomCategorie, propoJuste,
                        propoFausse, feedback,diff));
                System.out.println("Test Ok");
            } catch (IllegalArgumentException erreur) {
                System.out.println("erreur inattendu : la question est invalide ");
            }
        }
        
        /** testgetLibelle() */
        public static void testgetLibelle() {
            System.out.print("Hey");
        }

        /** test setLibelle() */
        public static void testsetLibelle() {
            System.out.print("Hey");
        }

        /** test getPropositionJuste() */
        public static void testgetPropositionJuste() {
            System.out.print("Hey");
        }

        /** test setPropositionJuste() */
        public static void testsetPropositionJuste() {
            System.out.print("Hey");
        }

        /** test getPropositionFausse() */
        public static void testgetPropositionFausse() {
            System.out.print("Hey");
        }

        /** test setPropositionFausse() */
        public static void testsetPropositionFausse() {
            System.out.print("Hey");
        }

        /** test getFeedback() */
        public static void testgetFeedback() {
            System.out.print("Hey");
        }

        /** test setFeedback() */
        public static void testsetFeedback() {
            System.out.print("Hey");
        }

        /** test getDifficulte() */
        public static void testtestgetDifficulte() {
            System.out.print("Hey");
        }

        /** test setDifficulte() */
        public static void testsetDifficulte() {
            System.out.print("Hey");
        }

        /** 
         * Lance les tests
         * @param args
         */
        public static void main(String[] args) {
            testQuestion();
        }
}
