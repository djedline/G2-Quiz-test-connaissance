/*
 * Question.java                                    17 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

/**
 * Permet de créer des questions
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
    
    /** difficulté de la question entre 0 et 3 
     * 0 tous les niveaux
     * 1 niveau facile
     * 2 niveau moyen
     * 3 niveau difficile */
    private int difficulte;

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
        if(verifQuestion()) {
            this.libelle = libelle;
            this.nomCategorie = nomCategorie;
            this.propositionJuste = propositionJuste;
            this.propositionFausse = propositionFausse;
            this.feedback = feedback;
            this.difficulte = difficulte;
        } 
    }

    /**  */
    private static boolean verifQuestion() {
        return false;
        
    }
    
    /** @return valeur de libelle */
    public String getLibelle() {
        return libelle;
    }

    /** @param libelle nouvelle valeur de libelle 
     * @return false si le libellé n'a pas pu être modifié
     *         true sinon
     */
    public boolean setLibelle(String libelle) {
        boolean nouvLibelOk;
        if (libelle.isBlank()) {
            nouvLibelOk = false;
        } else {
            this.libelle = libelle;
            nouvLibelOk = true;
        }
        return nouvLibelOk;
    }

    /** @return valeur de libelle */
    public String nomCategorie() {
        return nomCategorie.getLibelle();
    }
    
    /** @return valeur de propositionJuste */
    public String getPropositionJuste() {
        return propositionJuste;
    }

    /** @param propositionJuste nouvelle valeur de propositionJuste 
     * @return false*/
    public boolean setPropositionJuste(String propositionJuste) {
        this.propositionJuste = propositionJuste;
        return false;
    }

    /** @return valeur de propositionFausse */
    public String[] getPropositionFausse() {
        return propositionFausse;
    }

    /** @param propositionFausse nouvelle valeur de propositionFausse
      * @return false
      */
   public boolean setPropositionFausse(String[] propositionFausse) {
        this.propositionFausse = propositionFausse;
        return false;
    }

    /** @return valeur de feedback */
    public String getFeedback() {
        return feedback;
    }

    /** @param feedback nouvelle valeur de feedback  
     * @return false*/
    public boolean setFeedback(String feedback) {
        this.feedback = feedback;
        return false;
    }

    /** @return valeur de difficulte */
    public int getDifficulte() {
        return difficulte;
    }

    /** @param difficulte nouvelle valeur de difficulte  
     * @return false*/
    public boolean setDifficulte(int difficulte) {
        this.difficulte = difficulte;
        return false;
    }
    
}
