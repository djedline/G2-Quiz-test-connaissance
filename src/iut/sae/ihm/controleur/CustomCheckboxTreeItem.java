/* CustomCheckboxTreeItem.java                                        27/11/2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import iut.sae.modele.Categorie;
import javafx.scene.control.CheckBoxTreeItem;

/**
 * TODO comment class responsibility (SRP)
 * @author 
 * @param <T>
 */
public class CustomCheckboxTreeItem<T> extends CheckBoxTreeItem<T> {

    /**
     * 
     */
    private Object userData;

    /**
     * TODO comment initial state
     * @param item
     */
    public CustomCheckboxTreeItem(Categorie item) {
        this.userData = item;
    }

    /**
     * TODO comment method role
     * @return userData
     */
    public Object getUserData() {
        return this.userData;
    }

    /**
     * TODO comment method role
     * @param e
     */
    public void setUserData(Object e) {
        this.userData = e;
    }

}
