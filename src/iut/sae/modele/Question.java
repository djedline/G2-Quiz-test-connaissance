/*
 * Question.java                                                    17 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe permettant de créer des questions pour le quiz
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class Question implements Serializable {

    /** Version de la classe question (date et heure au format JJMMHHmm */
    private static final long serialVersionUID = 13110945L;

    /** libellé de la question */
    private String libelle;

    /** réponse correcte à la question */
    private Categorie nomCategorie;

    /** réponse correcte à la question */
    private String propositionJuste;

    /** ensemble de mauvaise réponse à la question */
    private ArrayList<String> propositionFausse = new ArrayList<String>();

    /** explication de la réponse juste */
    private String feedback;

    /**
     * difficulté de la question entre 1 et 3 1 niveau facile 2 niveau moyen 3
     * niveau difficile
     */
    private int difficulte;

    /**
     * Crée une question
     * 
     * @param libelle           libellé de la question
     * @param nomCategorie      la catégorie
     * @param propositionJuste  réponse correcte à la question
     * @param propositionFausse ensemble de mauvaise réponse à la question
     * @param feedback          explication de la réponse juste
     * @param difficulte        niveau de difficulté de la question entre 1 et 3
     *                          => facile 2 => moyen 3 => difficile
     * @throws IllegalArgumentException si les spécificité des questions sont 
     * fausse
     */
    public Question(String libelle, Categorie nomCategorie, 
            String propositionJuste, String[] propositionFausse,String feedback,
            int difficulte) {
        super();
        if (verifQuestion(
                libelle, nomCategorie, propositionJuste, propositionFausse, 
                feedback, difficulte)) {
            this.libelle = libelle;
            this.nomCategorie = nomCategorie;
            this.propositionJuste = propositionJuste;
            for (String element : propositionFausse) {
                this.propositionFausse.add(element);
            }
            this.feedback = feedback;
            this.difficulte = difficulte;
        } else {
        	System.out.println("le libelle" + libelle);
        	System.out.println("la categorie " + nomCategorie);
        	System.out.println("la proposition juste " + propositionJuste);
        	System.out.println("les proposition fausse " 
        	                 + propositionFausse);
        	System.out.println("le feedback" + feedback);
        	System.out.println("la difficulté " + difficulte);
            throw new IllegalArgumentException(
                    "Les arguments entrées sont incorrectes");
            
        }
    }

    /** 
     * Vérifie que les paramètres d'une question sont correctes 
     * 
     * @param libelle           libellé de la question
     * @param nomCategorie      la catégorie
     * @param propositionJuste  réponse correcte à la question
     * @param propositionFausse ensemble de mauvaise réponse à la question
     * @param feedback          explication de la réponse juste
     * @param difficulte        niveau de difficulté de la question entre 1 et 3
     *                          => facile 2 => moyen 3 => difficile
     */
    private static boolean verifQuestion(String libelle, Categorie nomCategorie,
            String propositionJuste, String[] propositionFausse, 
            String feedback, int difficulte) {
        boolean questionOk;
        boolean propOk = true;
        for (int index = 0; index < propositionFausse.length && propOk; index++) {
            propOk = !propositionFausse[index].isBlank();
        }
        questionOk = !libelle.isBlank() && nomCategorie != null 
                && !propositionJuste.isBlank() && propOk
                && propositionFausse != null && feedback != null 
                && 1 <= difficulte && difficulte <= 3
                && propositionFausse.length != 0;
        return questionOk;
    }

    /**
     * verifie que 
     * @param o
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Question) {
            Question aComparer = (Question) o;
            boolean propOk = propositionFausse.size() == 
                    aComparer.getPropositionFausse().size();
            for (int index = 0; 
                    index < propositionFausse.size() && propOk; index++) {
                propOk = propositionFausse.get(index).toUpperCase()
                        .equals(aComparer.getPropositionFausse().get(index)
                                .toUpperCase());
            }
            return libelle.toUpperCase().equals(aComparer.getLibelle()
                    .toUpperCase())
                    && nomCategorie.equals(nomCategorie)
                    && propositionJuste.toUpperCase().equals(
                            aComparer.getPropositionJuste().toUpperCase()) 
                    && propOk
                    && feedback.toUpperCase().equals(aComparer.feedback
                            .toUpperCase())
                    && difficulte == aComparer.getDifficulte();
        } else {
            return false;
        }
    }

    /** 
     * getter de libelle
     * @return valeur de libelle
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * @param libelle nouvelle valeur de libelle
     * @return false si le libellé n'a pas pu être modifié true sinon
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
    public Categorie getCategorie() {
        return nomCategorie;
    }

    /**
     * @param nouveau
     * @return valeur de libelle
     */
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

    /**
     * @param propositionJuste nouvelle valeur de propositionJuste
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
    public ArrayList<String> getPropositionFausse() {
        return this.propositionFausse;
    }

    /**
     * @param propositionFausse nouvelle valeur de propositionFausse
     * @return false
     */
    public boolean setPropositionFausse(String[] propositionFausse) {

        boolean propOk = propositionFausse.length > 1;
        for (int index = 0; index < propositionFausse.length && propOk; index++) {
            propOk = !propositionFausse[index].isBlank();
        }
        if (propOk) {
            this.viderPropositionFausse();
            for (String element : propositionFausse) {
                this.propositionFausse.add(element);
            }
        }
        return propOk;
    }

    /**
     * @param propositionFausse nouvelle valeur de propositionFausse
     * @return true si ça a marché, false sinon
     */
    public boolean addPropositionFausse(String propositionFausse) {
        if (!(this.propositionFausse.size() > 3)) {
            this.propositionFausse.add(propositionFausse);
            return true;
        }
        return false;

    }

    /**
     * Méthode qui permet de vider le contenu de la proposition fausse
     */
    public void viderPropositionFausse() {
        this.propositionFausse.clear();
    }

    /** @return valeur de feedback */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @param feedback nouvelle valeur de feedback
     * @return false
     */
    public boolean setFeedback(String feedback) {
        boolean feedBackOk = feedback != null;
        if (feedBackOk) {
            this.feedback = feedback;
        }
        return feedBackOk;
    }

    /** @return valeur de difficulte */
    public int getDifficulte() {
        // System.out.println(this.difficulte);
        return difficulte;
    }

    /**
     * @param difficulte nouvelle valeur de difficulte
     * @return false
     */
    public boolean setDifficulte(int difficulte) {
        boolean difficulteOk;
        difficulteOk = 1 <= difficulte && difficulte <= 3;
        if (difficulteOk) {
            this.difficulte = difficulte;
        }
        return difficulteOk;
    }

    @Override
    public String toString() {
        return libelle;
    }

}
