/*
 * Categorie.java                                    18 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

/** 
 * Classe permettant de créer des catégories pour le quiz
 * @author nael.briot
 */
public class Categorie {
        
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
        
        /** TODO comparer deux Categories */
        public boolean compareTo(Categorie aComparer) {
        	return titreCat.toUpperCase().equals(aComparer.getLibelle().toUpperCase());
        }
        
        /* non javadoc - @see java.lang.Object#toString() */
        @Override
        public String toString() {
            return this.titreCat;
        }
        
        
}
