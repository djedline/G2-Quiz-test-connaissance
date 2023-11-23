package iut.sae.modele;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;

/** TODO comment class responsibility (SRP)
 * @author leo.cheikh-boukal
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

    /** TODO comment initial state
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
    
    /** TODO comment method role
     * @return le string
     */
    public String getString() {
    	return this.boxText.getText();
    }
}