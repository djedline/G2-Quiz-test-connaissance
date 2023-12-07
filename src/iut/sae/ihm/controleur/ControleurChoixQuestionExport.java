/*
 * ControleurGestionDonnees.java                                     9 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.ImportExport;
import iut.sae.modele.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

/**
 * Controleur de la scène ChoixQuestionExport
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurChoixQuestionExport {

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnValider;

    @FXML
    private TreeView<Object> treeViewData;

    @FXML
    private CheckBox checkboxAllQuestions;

    private File fichierAExporter;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        // création d'une ArrayList qui contiendra les treeitem de catégorie
        List<CustomCheckboxTreeItem<Object>> listeTreeItem = new ArrayList<>();
        for (Categorie item : Donnees.listeCategorie) {

            // crée et ajoute le treeitem de catégorie
            CustomCheckboxTreeItem<Object> checkable = new CustomCheckboxTreeItem<>(item);
            checkable.setValue(item);
            
            listeTreeItem.add(checkable);

            for (Question laQuestion : Donnees.listeQuestions) {

                // si la catégorie et la catégorie de la question sont la même
                if (item.toString().equals(laQuestion.getCategorie().toString())) {

                    /*
                     * on crée un treeitem de la question et on l'ajoute au treeitem de la catégorie
                     */
                	CustomCheckboxTreeItem<Object> checkableQuestion = 
                			new CustomCheckboxTreeItem<Object>(laQuestion);
                	checkableQuestion.setValue(laQuestion);
                    checkable.getChildren().add(checkableQuestion);
                }
            }
        }
        // affiche les checkboxs
        treeViewData.setCellFactory(CheckBoxTreeCell.<Object>forTreeView());

        // on crée la racine du treeview puis on le cache
        treeViewData.setRoot(new TreeItem<>());
        treeViewData.getRoot().setExpanded(true);
        treeViewData.setShowRoot(false);

        // on ajoute tout les treeitems des catégories
        treeViewData.getRoot().getChildren().addAll(listeTreeItem);
    }

    /**
     * TODO comment method role
     * 
     * @param f
     */
    public void setExportFile(File f) {
        this.fichierAExporter = f;
    }

    @FXML
    /**
     * Coche toutes les questions si le bouton "tout exporter" est coché, et les
     * décoche si il est décoché
     * 
     * @param event l'évènement déclenché par la coche
     */
    void checkExportAll(ActionEvent event) {
        if (checkboxAllQuestions.isSelected()) {
            for (TreeItem<Object> cat : treeViewData.getRoot().getChildren()) {
                CheckBoxTreeItem<Object> catConv = (CheckBoxTreeItem<Object>) cat;
                catConv.setSelected(true);
            }
        } else {
            for (TreeItem<Object> cat : treeViewData.getRoot().getChildren()) {
                CheckBoxTreeItem<Object> catConv = (CheckBoxTreeItem<Object>) cat;
                catConv.setSelected(false);
            }
        }
    }

    @FXML
    void retourArriere(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_EXPORT);
    }

    @FXML
    /**
     * Sélectionne toutes les questions à exporter dans une liste et les exporte
     * avant de notifier l'utilisateur du résultat de l'exportation
     * 
     * @param event l'évènement déclenché.
     */
    void valider(ActionEvent event) {
        List<Question> selectionnees = new ArrayList<>();
        for (TreeItem<Object> itemCat : treeViewData.getRoot().getChildren()) {
            CheckBoxTreeItem<Object> convertiCat = (CheckBoxTreeItem<Object>) itemCat;
            if (convertiCat.isSelected()) {
                Categorie laCat = (Categorie) convertiCat.getValue();
                String nomCat = laCat.getLibelle();
                selectionnees.addAll(Donnees.getQuestionOfCategorie(nomCat));
            } else if (convertiCat.isIndeterminate()) {
                for (TreeItem<Object> itemQ : itemCat.getChildren()) {
                    CustomCheckboxTreeItem<Object> ccti = 
                    		(CustomCheckboxTreeItem<Object>) itemQ;
                    if (ccti.isSelected() && ccti.getUserData() != null) {
                        Question q = (Question) ccti.getUserData();
                        selectionnees.add(q);
                    }
                }
            }
        }
        try {
            ImportExport.exporter(fichierAExporter, selectionnees);
            new Alert(AlertType.INFORMATION, "Exportation réussie.").show();
        } catch (IOException e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

}
