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

	
	private Categorie laCategorie;
	private Question laQuestion;
    private Label boxText = new Label();
    private MenuButton boxButton;

    CustomBtn(Label txt) {
        super();

        this.boxText = txt;

        this.getChildren().add(boxText);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    /** 
     * Customize les boutons
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
    }
    
    /** TODO comment initial state
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
    }
    
    /** 
     * Affiche le texte des boutons
     * @return texte
     */
    public String getString() {
    	return this.boxText.getText();
    }
    
    /** TODO comment method role
     * @return f
     */
    public Categorie getCategorie() {
    	return this.laCategorie;
    }
    
    /** TODO comment method role
     * @return laQuestion
     */
    public Question getQuestion() {
    	return this.laQuestion;
    }
}