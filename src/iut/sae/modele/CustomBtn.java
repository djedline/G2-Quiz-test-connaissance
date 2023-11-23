package iut.sae.modele;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;

/** 
 * Classe permettant la création d'un ensemble bouton et item
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 * leo.cheikh-boukal
 * @version 1.0
 */
public class CustomBtn extends HBox {

    private Label boxText;
    private MenuButton boxButton;

    CustomBtn(Label txt) {
        super();

        this.boxText = txt;

        this.getChildren().add(boxText);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    /** 
     * Constructeur d'un ensemble de bouton et item
     * @param txt
     * @param bt
     */
    public CustomBtn(Label txt, MenuButton bt) {
        super(5);

        this.boxText = txt;
        this.boxButton = bt;

        this.getChildren().addAll(boxText, boxButton);
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
    /** 
     * Méthode qui permet de récupérer le texte sur le bouton
     * @return le texte du bouton
     */
    public String getString() {
    	return this.boxText.getText();
    }
}