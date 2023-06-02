package eduremind.scene;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SplashScene {
    private Stage stage;

    public SplashScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // git commit -m "feat : Menambahkan image logo"
        Image logoss = new Image(getClass().getClassLoader().getResourceAsStream("img/notification.png"));
        ImageView SplashImage = new ImageView(logoss);
        SplashImage.setFitWidth(300);
        SplashImage.setFitHeight(300);

        // // git commit -m "feat : menambahkan/membuat judul"
        // Label titleText1 = new Label("EDU");
        // titleText1.setId("mainJudul1");
        // Label titleText2 = new Label("REMIND");
        // titleText2.setId("mainJudul2");
        // HBox titleContBox = new HBox(titleText1, titleText2);
        // titleContBox.setSpacing(10);
        // titleContBox.setAlignment(Pos.CENTER);

        // // git commit -m "feat : membuat vbox untuk tampung logo dan title aplikasi"
        // VBox root = new VBox(SplashImage, titleContBox);
        // root.setSpacing(15);
        // root.setAlignment(Pos.CENTER);

        // // git commit -m "feat : init scene"
        // Scene splashScene = new Scene(root, 1440, 800);
        // splashScene.getRoot().getStyleClass().add("scene1");
        // splashScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        // stage.setScene(splashScene);
        // stage.show();
        
//         //jangan dulu di commit
//         Thread splashThread = new Thread(() -> {
//             try {
//                 Thread.sleep(3000);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }

//             Platform.runLater(() -> {
//                 stage.close();
//                 LoginScene loginScene = new LoginScene(stage);
//                 loginScene.show();
//             });
//         });
//         splashThread.start();
    }
}
