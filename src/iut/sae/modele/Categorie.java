/*
 * Categorie.java                                    18 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.io.Serializable;

/** 
 * Classe permettant de créer des catégories pour le quiz
 * @author nael.briot
 */
public class Categorie implements Serializable {
        
        /** Version de la classe catégorie (date et heure au format JJMMHHmm*/
        private static final long serialVersionUID = 13110945L;
    
        /** Attribut contenant le titre de la Catégorie */
        private String titreCat;
        
        /**
         * Constructeur de catégorie, permet de créer une nouvelle catégorie
         * @param libelle le titre de la catégorie à ajouter
         * @throws IllegalArgumentException si le titre est vide
         */
        public Categorie(String libelle) {
                this.titreCat = libelle;
                if (libelle.isBlank()){
                        throw new IllegalArgumentException("le libellé n'est pas valide");
                }
        }
        /** Accesseur sur le titre de la catégorie 
         * @return le titre de la catégorie 
         */
        public String getLibelle() {
                return this.titreCat;
        }
        
        /* non javadoc - @see java.lang.Object#toString() */
        @Override
        public String toString() {
            return this.titreCat;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Categorie) {
                Categorie converti = (Categorie) o;
                return this.titreCat.toUpperCase().equals(
                        converti.getLibelle().toUpperCase());
            } else {
                return false;
            }
        }
        
        
}
