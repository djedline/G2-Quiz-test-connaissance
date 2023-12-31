/* ControleurGestionDonnees.java                                     9 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Categorie;
import iut.sae.modele.CustomBtn;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Controleur de la scène GestionDonnees
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurGestionDonnees {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAnnuler"
    private Button btnAnnuler; // Value injected by FXMLLoader

    @FXML // fx:id="treeViewData"
    private TreeView<CustomBtn> treeViewData; // Value injected by FXMLLoader

    @FXML
    void retourArriere(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_MENU_GESTION_DONNEES);
    }

    // This method is called by the FXMLLoader when initialization is complete
    @FXML 
    void initialize() {
        assert btnAnnuler != null
                : "fx:id=\"btnAnnuler\" was not injected:" 
                + " check your FXML file 'GestionDonnees.fxml'.";
        assert treeViewData != null
                : "fx:id=\"treeViewData\" was not injected: " 
                + "check your FXML file 'GestionDonnees.fxml'.";

        // crée l'eventHandler pour modifier la Categorie
        EventHandler<ActionEvent> modifierCat = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                // récupère la catégorie qui était dans le menuitem
                Categorie laCategorie = 
                        (Categorie) ((MenuItem) e.getSource()).getUserData();
                // vérifier que la catégorie choisi n'est pas "general"
                if (laCategorie != Donnees.listeCategorie.getFirst()) {
                    EchangeurDeVue.echangerAvec(laCategorie);
                } else {
                    Alert erreur = new Alert(AlertType.WARNING);
                    erreur.setContentText(
                            "Vous ne pouvez pas modifier la catégorie Général");
                    erreur.showAndWait();
                }

            }
        };
        // crée l'eventhandler pour modifier les questions
        EventHandler<ActionEvent> modifierQuest =
                new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // récupère la question qui est passée en paramètre du menuitem
                Question laQuestion = 
                        (Question) ((MenuItem) e.getSource()).getUserData();
                EchangeurDeVue.echangerAvec(laQuestion);
            }
        };
        // crée l'eventhandler pour supprimer les catégories
        EventHandler<ActionEvent> suprimerCat = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                // récupère la catégorie qui était dans le menuitem
                Categorie laCategorie = 
                        (Categorie) ((MenuItem) e.getSource()).getUserData();
                // vérifie que la catégorie choisi n'est pas general
                if (laCategorie != Donnees.listeCategorie.getFirst()) {
                    if (Donnees.isCategorieVide(laCategorie)) {
                        Donnees.suprimerCategorie(laCategorie);
                    } else {
                        // affiche l'alert si la catégorie n'est pas vide
                        Alert confirmation = new Alert(AlertType.CONFIRMATION);
                        confirmation.setContentText(
                                "La categorie contient des questions."
                                + "\nCette action les supprimera aussi.");
                        Optional<ButtonType> result = 
                                confirmation.showAndWait();
                        /* si l'utilisateur clique sur ok, 
                           les questions et la catégorie sont supprimées */
                        if (result.get() == ButtonType.OK) {
                            Donnees.suprimerCategorie(laCategorie);
                        }
                    }
                    EchangeurDeVue.echangerAvec(
                            EnsembleDesVues.VUE_GESTION_DONNEES);

                } else {
                    Alert erreur = new Alert(AlertType.WARNING);
                    erreur.setContentText(
                            "Vous ne pouvez pas supprimer la catégorie Général");
                    erreur.showAndWait();
                }
            }
        };
        // crée l'eventHandler de suppresion question
        EventHandler<ActionEvent> supprimerQuest =
                new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // recupere la question qui est passé en parametre du menuitem
                Question laQuestion = 
                        (Question) ((MenuItem) e.getSource()).getUserData();
                Donnees.supprimerQuestion(laQuestion);
                EchangeurDeVue.echangerAvec(
                        EnsembleDesVues.VUE_GESTION_DONNEES);
            }
        };

        // creation d'une arraylist qui contiendra les treeitem de categorie
        List<TreeItem<CustomBtn>> listeTreeItem = new ArrayList<>();
        for (Categorie item : Donnees.listeCategorie) {
            // creation du menuButton de modification
            MenuButton btnModifierCategorie = new MenuButton("Gérer");
            MenuItem menuItemModifier = new MenuItem("Modifier");
            MenuItem menuItemSuprimer = new MenuItem("Supprimer");

            // la categorie est mise en parametre des menuItem
            menuItemModifier.setUserData(item);
            menuItemSuprimer.setUserData(item);
            // les eventHandler sont placer sur les menuItem
            menuItemModifier.setOnAction(modifierCat);
            menuItemSuprimer.setOnAction(suprimerCat);
            // on ajoute les menuitem dans le menuButton
            btnModifierCategorie.getItems().add(menuItemModifier);
            btnModifierCategorie.getItems().add(menuItemSuprimer);
            // ajout d'un treeitem avec en parametre la categorie 
            // et le menuButton
            listeTreeItem.add(new TreeItem<CustomBtn>(
                    new CustomBtn(item, btnModifierCategorie)));
        }
        // pour chaque treeitem (donc pour chaque categorie)
        for (TreeItem<CustomBtn> leTreeItem : listeTreeItem) {
            // pour chaque question
            for (Question laQuestion : Donnees.listeQuestions) {
                // on recupere la categorie
                Categorie cat = leTreeItem.getValue().getCategorie();
                // si la categorie et la categorie de la question sont la meme
                if (cat.getLibelle().equals(laQuestion.getCategorie().toString())) {
                    // ajout du btn de modification de question
                    MenuButton btnModifierQuestion = new MenuButton("Gérer");
                    MenuItem menuItemModifier = new MenuItem("Modifier");
                    MenuItem menuItemSuprimer = new MenuItem("Supprimer");
                    // ajout de la question dans les menuItems
                    menuItemModifier.setUserData(laQuestion);
                    menuItemSuprimer.setUserData(laQuestion);
                    // on place les eventHandler
                    menuItemModifier.setOnAction(modifierQuest);
                    menuItemSuprimer.setOnAction(supprimerQuest);
                    // on ajoute les menuitem dans le menuButton
                    btnModifierQuestion.getItems().add(menuItemModifier);
                    btnModifierQuestion.getItems().add(menuItemSuprimer);
                    // on cree un treeitem avec en parametre la question et 
                    // le menuButton et on l'ajoute au treeitem de la categorie
                    leTreeItem.getChildren()
                            .add(new TreeItem<CustomBtn>(
                                    new CustomBtn(
                                            laQuestion, btnModifierQuestion)));
                }

            }
        }
        // on cree le root du treeview puis on le cache
        treeViewData.setRoot(new TreeItem<>());
        treeViewData.setShowRoot(false);
        // on ajoute tout les treeitem des categorie
        treeViewData.getRoot().getChildren().addAll(listeTreeItem);

    }

}
