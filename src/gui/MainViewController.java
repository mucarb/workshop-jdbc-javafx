package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	// synchronized garante que o processo não seja interrompido durante multthread
	private synchronized void loadView(String absoluteName) {
		try {
			// carregando arquivo fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			// ao carregar o arquivo, é atribuido em um layout VBox
			VBox newVBox = loader.load();
			// layout principal é atribuido em uma variável
			Scene mainScene = Main.getMainScene();
			// getRoot pega o primeiro elemento da view
			// ScrollPane getContent acessando o conteudo referente do ScrollPane
			// casting para tipo VBox
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			// pega os children do VBox na primeira posicao (main menu)
			Node mainMenu = mainVBox.getChildren().get(0);

			// limpa todos os childrens do main VBox
			mainVBox.getChildren().clear();
			// adicionando o main menu
			mainVBox.getChildren().add(mainMenu);
			// adicionando novo VBox a scene
			mainVBox.getChildren().addAll(newVBox.getChildren());
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
