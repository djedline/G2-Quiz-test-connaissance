/*
 * Question.java                                    17 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

/**
 * Classe permettant de créer des questions pour le quiz
 * @author djedline.boyer
 *
 */
public class Question {
    
    /** libellé de la question */
    private String libelle;
    
    /** réponse correcte à la question */
    private Categorie nomCategorie;
    
    /** réponse correcte à la question */
    private String propositionJuste;
    
    /** ensemble de mauvaise réponse à la question */
    private String[] propositionFausse;
    
    /** explication de la réponse juste */
    private String feedback;
    
    /** difficulté de la question entre 1 et 3 
     * 1 niveau facile
     * 2 niveau moyen
     * 3 niveau difficile */
    private int difficulte;

    private static int nbTest = 0;
    //TODO private Categorie categorie
    
    /** 
     * Crée une question
     * @param libelle libellé de la question
     * @param nomCategorie nom de la catégorie
     * @param propositionJuste réponse correcte à la question
     * @param propositionFausse ensemble de mauvaise réponse à la question
     * @param feedback explication de la réponse juste
     * @param difficulte niveau de difficulté de la question entre 0 et 3 
     * @throws IllegalArgumentException si les spécificité des questions sont fausse
     */
    public Question(String libelle, Categorie nomCategorie, String propositionJuste, String[] propositionFausse, String feedback,
            int difficulte) {
        super();
       if(verifQuestion(libelle, nomCategorie, propositionJuste, propositionFausse, feedback, difficulte)) {
            this.libelle = libelle;
            this.nomCategorie = nomCategorie;
            this.propositionJuste = propositionJuste;
            this.propositionFausse = propositionFausse;
            this.feedback = feedback;
            this.difficulte = difficulte;
       } else {
           throw new IllegalArgumentException();
       }
    }

    /** Vérifie que les paramètres d'une question sont correctes */
    private static boolean verifQuestion(String libelle, Categorie nomCategorie, String propositionJuste, String[] propositionFausse, String feedback,
            int difficulte) {
        boolean questionOk;
        boolean propOk = true;
        for (int index = 0; index < propositionFausse.length && propOk; index++) {
            propOk = !propositionFausse[index].isBlank();
        }
        questionOk = !libelle.isBlank() && nomCategorie != null 
                && !propositionJuste.isBlank() && propOk && propositionFausse != null
                && !feedback.isBlank() && 0 <= difficulte && difficulte <= 3;
        System.out.println("Test " + nbTest + " : " + questionOk);
        nbTest++;
        return questionOk;
    }
    
    /** @return valeur de libelle */
    public String getLibelle() {
        return this.libelle;
    }

    /** @param libelle nouvelle valeur de libelle 
     * @return false si le libellé n'a pas pu être modifié
     *         true sinon
     */
    public boolean setLibelle(String libelle) {
        boolean nouvLibelOk;
        nouvLibelOk = !libelle.isBlank();
        if (nouvLibelOk) {
            this.libelle = libelle;
        }
        return nouvLibelOk;
    }

    /** @return valeur de libelle */
    public String getCategorie() {
        return nomCategorie.getLibelle();
    }
    
    /** @param nouveau 
     * @return valeur de libelle */
    public boolean setCategorie(Categorie nouveau) {
        boolean nouvCategorie;
        nouvCategorie = nouveau != null;
        if (nouvCategorie) {
            this.nomCategorie = nouveau;
        }
        return nouvCategorie;
    }
    
    /** @return valeur de propositionJuste */
    public String getPropositionJuste() {
        return propositionJuste;
    }

    /** @param propositionJuste nouvelle valeur de propositionJuste 
     * @return false
     */
    public boolean setPropositionJuste(String propositionJuste) {
        boolean propoJusteOk = !propositionJuste.isBlank();
        if (propoJusteOk) {
            this.propositionJuste = propositionJuste;
        }
        
        return propoJusteOk;
    }

    /** @return valeur de propositionFausse */
    public String[] getPropositionFausse() {
        return this.propositionFausse;
    }

    /** @param propositionFausse nouvelle valeur de propositionFausse
      * @return false
      */
   public boolean setPropositionFausse(String[] propositionFausse) {
        this.propositionFausse = propositionFausse;
        boolean propOk = true;
        for (int index = 0; index < propositionFausse.length && propOk; index++) {
            propOk = !propositionFausse[index].isBlank();
        }
        if (propOk) {
            this.propositionFausse = propositionFausse;
        }
        return propOk;
    }

    /** @return valeur de feedback */
    public String getFeedback() {
        return feedback;
    }

    /** @param feedback nouvelle valeur de feedback  
     * @return false*/
    public boolean setFeedback(String feedback) {
        boolean feedBackOk = !feedback.isBlank();
        if (feedBackOk) {
            this.feedback = feedback;
        }
        return feedBackOk;
    }

    /** @return valeur de difficulte */
    public int getDifficulte() {
        //System.out.println(this.difficulte);
        return difficulte;
    }

    /** @param difficulte nouvelle valeur de difficulte  
     * @return false*/
    public boolean setDifficulte(int difficulte) {
        boolean difficulteOk;
        difficulteOk = 0 <= difficulte && difficulte <= 3;
        if(difficulteOk) {
            this.difficulte = difficulte;
        }
        return difficulteOk;
    }
    
}
