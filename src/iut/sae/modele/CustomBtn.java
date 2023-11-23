package iut.sae.modele;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;

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

    public CustomBtn(Question laquest, MenuButton bt) {
        super(5);

        this.laQuestion = laquest;
        this.boxText.setText(laQuestion.toString());
        this.boxButton = bt;

        this.getChildren().addAll(boxText, boxButton);
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
    public CustomBtn(Categorie lacat, MenuButton bt) {
        super(5);

        this.laCategorie = lacat;
        this.boxText.setText(laCategorie.toString());
        this.boxButton = bt;

        this.getChildren().addAll(boxText, boxButton);
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
    public String getString() {
    	return this.boxText.getText();
    }
    
    public Categorie getCategorie() {
    	return this.laCategorie;
    }
    
    public Question getQuestion() {
    	return this.laQuestion;
    }
}