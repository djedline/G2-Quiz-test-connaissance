package iut.sae.modele;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;

/** 
 * Créer des boutons
 * @author djedline.boyer
 *
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
     * Customize les boutons
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
     * Affiche le texte des boutons
     * @return texte
     */
    public String getString() {
    	return this.boxText.getText();
    }
}