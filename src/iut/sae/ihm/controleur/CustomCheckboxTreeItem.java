package iut.sae.ihm.controleur;

import iut.sae.modele.Categorie;
import javafx.scene.control.CheckBoxTreeItem;

public class CustomCheckboxTreeItem<T> extends CheckBoxTreeItem<T> {

	private Object userData;
	
	public CustomCheckboxTreeItem(Categorie item) {
		this.userData = item;
	}

	public Object getUserData(){
		return this.userData;
	}
	
	public void setUserData(Object e) {
		this.userData = e;
	}
	
}
