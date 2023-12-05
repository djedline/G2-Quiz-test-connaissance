/* CustomCheckboxTreeItem.java                                        27/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import iut.sae.modele.Categorie;
import javafx.scene.control.CheckBoxTreeItem;

/**
 * 
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @param <T> le type de données de l'item
 */
public class CustomCheckboxTreeItem<T> extends CheckBoxTreeItem<T> {

    /**
     * Les données stockées pour cet item.
     */
    private Object userData;

    /**
     * Crée un TreeItem avec une checkbox qui stockera une catégorie.
     * @param item l'objet stocké par ce treeitem
     */
    public CustomCheckboxTreeItem(Object item) {
        this.userData = item;
    }

    /**
     * @return userData les données stockées pour cet item.
     */
    public Object getUserData() {
        return this.userData;
    }

    /**
     * @param e l'objet à stocker comme données de cet item.
     */
    public void setUserData(Object e) {
        this.userData = e;
    }

}
