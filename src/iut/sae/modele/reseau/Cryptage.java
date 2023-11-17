/*
 * Cryptage.java                                    14 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.lang.Math;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
 */
public class Cryptage {
    
    private final static int TAILLE_ENSEMBLE = 880;
    private final static double MAX_LONGUEUR_CLE = 100.0;
    
    /** TODO comment method role
     * @param args
     */
    public static void main(String[] args) {
        String message = "Лорем ипсум долор сит амет, долоре иудицабит ет сеа, вис долоре цонституам ат, ест дебет алияуип еа. Про убияуе сусципит ет, нонумы инсоленс при те. Вим ех фастидии аппетере реформиданс, еним идяуе инани меи ех. Сеа но иусто регионе реформиданс, ерудити молестие витуператорибус те вим. Нам ут сумо дисцере, лаборес диссентиет ест ин.Лорем ипсум долор сит амет, долоре иудицабит ет сеа, вис долоре цонституам ат, ест дебет алияуип еа. Про убияуе сусципит ет, нонумы инсоленс при те. Вим ех фастидии аппетере реформиданс, еним идяуе инани меи ех. Сеа но иусто регионе реформиданс, ерудити молестие витуператорибус те вим. Нам ут сумо дисцере, лаборес диссентиет ест ин.";
        System.out.println("Message : " + message);
        String cle = genereCle();
        
        
        String crypte = chiffrer(message, cle);
        dechiffrer(crypte, cle);
    }
    
    /** 
     * Génère la clé
     * @return laCle
     */
    public static String genereCle() {
        String laCle="";
        int nombreAlea; 
        final int LONGUEUR_CLE = (int) (Math.random() * MAX_LONGUEUR_CLE);
        
        for(int i = 0 ; laCle.length() < LONGUEUR_CLE ; i++) {
            nombreAlea = (int)(TAILLE_ENSEMBLE * Math.random());
            if(Character.isValidCodePoint(nombreAlea) 
            		&& Character.toString(nombreAlea).length() == 1) {
                laCle += Character.toString(nombreAlea);
            }
        }
        System.out.println("Clé : " + laCle + " de longueur " + laCle.length());
        return laCle;
        }
    
    /** TODO comment method role
     * @param aChiffrer 
     * @param cle
     * @return contenuCrypte le contenu du fichier crypté
     */
    public static String chiffrer(String aChiffrer, String cle) {
        String msgCrypte = "";
        int indexCle = 0;
        
        for (int c = 0 ; c < aChiffrer.length() ; c++, indexCle++) {
            int codeLettre = Character.codePointAt(aChiffrer, c);
            int codeCle = Character.codePointAt(cle, indexCle);
            if (indexCle == cle.length() - 1) {
                indexCle = -1;
            }
            int charCrypte = (codeLettre + codeCle) % TAILLE_ENSEMBLE;
            msgCrypte += Character.toString(charCrypte);
            System.out.println("CodePoint : " + charCrypte + " = " + Character.toString(charCrypte));
        }
        System.out.println("Message crypté : " + msgCrypte);
        return msgCrypte;
    }
    
    /** 
     * Déchiffrement.
     * @param aDechiffrer
     * @param cle
     * @return un message déchiffré.
     */
    public static String dechiffrer(String aDechiffrer, String cle) {
        String msgDecrypte = "";
        int indexCle = 0;
        
        for (int c = 0 ; c < aDechiffrer.length() ; c++, indexCle++) {
            int codeLettre = Character.codePointAt(aDechiffrer, c);
            int codeCle = Character.codePointAt(cle, indexCle);
            if (indexCle == cle.length() - 1) {
                indexCle = -1;
            }
            int charDecrypte = (codeLettre - codeCle) % TAILLE_ENSEMBLE;
            if (charDecrypte < 0) {
                charDecrypte += TAILLE_ENSEMBLE;
            }
            msgDecrypte += Character.toString(charDecrypte);
        }
        System.out.println("Message décrypté : " + msgDecrypte);
        return msgDecrypte;
    }
}
