/*
 * CustomBtn.java                                                   21 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */

package iut.sae.modele;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;

/**
 * Permet la création d'un ensemble bouton et item
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class CustomBtn extends HBox {

    /** La categorie associé */
    private Categorie laCategorie;
    
    /** la question associé */
    private Question laQuestion;
    
    /** label du bouton */
    private Label boxText = new Label();
    
    /** bouton */
    private MenuButton boxButton;

    /**
     * Constructeur de la classe avec le text
     * @param txt
     */
    public CustomBtn(Label txt) {
        super();

        this.boxText = txt;
        this.boxText.setWrapText(true);
        this.getChildren().add(boxText);
        this.setAlignment(Pos.CENTER_LEFT);
        
        boxText.getStyleClass().add("labelTreeItem");
    }

    /**
     * Constructeur d'un ensemble de bouton et item
     * 
     * @param laquest
     * @param bt
     */
    public CustomBtn(Question laquest, MenuButton bt) {

        super(5);

        this.laQuestion = laquest;
        this.boxText.setText(laQuestion.toString());
        this.boxButton = bt;

        this.getChildren().addAll(boxText, boxButton);
        this.setAlignment(Pos.CENTER_LEFT);
        
        boxText.getStyleClass().add("labelTreeItem");
    }

    /**
     * Constructeur avec la categorie et le bouton en param
     * 
     * @param lacat
     * @param bt
     */
    public CustomBtn(Categorie lacat, MenuButton bt) {
        super(5);

        this.laCategorie = lacat;
        this.boxText.setText(laCategorie.toString());
        this.boxButton = bt;

        this.getChildren().addAll(boxText, boxButton);
        this.setAlignment(Pos.CENTER_LEFT);
        
        boxText.getStyleClass().add("labelTreeItem");
    }

    /**
     * Méthode qui permet de récupérer le texte sur le bouton
     * 
     * @return le texte du bouton
     */
    public String getString() {
        return this.boxText.getText();
    }

    /**
     * Méthode qui permet de récupérer la catégorie enregistré
     * 
     * @return la catégorie enregistré
     */
    public Categorie getCategorie() {
        return this.laCategorie;
    }

    /**
     * Méthode qui permet de récupérer la question enregistré
     * 
     * @return la question enregistré
     */
    public Question getQuestion() {
        return this.laQuestion;
    }
}