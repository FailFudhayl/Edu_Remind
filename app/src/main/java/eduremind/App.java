/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package eduremind;

import eduremind.scene.SplashScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setResizable(true);
        stage.setTitle("Edu Remind");

        SplashScene initsScene = new SplashScene(stage);
        initsScene.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
