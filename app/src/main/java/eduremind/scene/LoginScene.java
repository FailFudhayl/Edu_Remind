package eduremind.scene;


import eduremind.controller.ControllerDB;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginScene {
    private Stage stage;

    public LoginScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // init borderpone
        BorderPane root = new BorderPane();
        root.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        root.getStyleClass().add("scene1");

        // git commit -m feat : "Menambahkan image logo "
        Image logoss = new Image(getClass().getClassLoader().getResourceAsStream("img/notification.png"));
        ImageView loginLogos = new ImageView(logoss);
        loginLogos.setPreserveRatio(true);
        loginLogos.setFitWidth(150);
        loginLogos.setFitHeight(150);
        //git commit -m "feat : setID logo"
        loginLogos.setId("logo1");
        //git commit -m "feat : masukkan logo dalam hbox"
        HBox logoBox = new HBox(loginLogos);
        //git commit -m "feat : atur agar logobox di tengah lalu letakkan di atas"
        logoBox.setAlignment(Pos.CENTER);
        root.setTop(logoBox);

        // Email Field dan Password Field
        //git commit -m "feat : buat label great "
        Label welcome = new Label("Hellooo👋");
        welcome.setTextAlignment(TextAlignment.LEFT);
        welcome.getStyleClass().add("welcome");
        Label greeting = new Label("Welcome to EDUREMIND, login to remind yourr taskk ");
        greeting.setTextAlignment(TextAlignment.LEFT);
        greeting.getStyleClass().add("greet");
        VBox greetBox = new VBox(welcome, greeting);

        Label emailLB = new Label("Username");
        emailLB.setAlignment(Pos.CENTER_LEFT);
        emailLB.getStyleClass().add("loginLB");
        TextField emailTF = new TextField();
        emailTF.setPromptText("Username");
        emailTF.setAlignment(Pos.CENTER_LEFT);
        emailTF.getStyleClass().add("loginTF");
        Label passLB = new Label("Password");
        passLB.setAlignment(Pos.CENTER_LEFT);
        passLB.getStyleClass().add("loginLB");
        PasswordField passTF = new PasswordField();
        passTF.setPromptText("Password");
        passTF.setAlignment(Pos.CENTER_LEFT);
        passTF.getStyleClass().add("loginTF");
        VBox logTFBox = new VBox(emailLB, emailTF, passLB, passTF);
        logTFBox.setSpacing(10);

        VBox loginBox = new VBox(greetBox, logTFBox);
        loginBox.setPadding(new Insets(130, 191, 102, 191));
        loginBox.setSpacing(20);

        root.setCenter(loginBox);

        // Tombol Login & Register
        Label validLB= new Label("  ");
        validLB.getStyleClass().add("rgisLB");
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("logBut");
        loginButton.setOnAction(env -> {
            String username = emailTF.getText();
            String password = passTF.getText();
            try {
                int id = ControllerDB.LoginValidation(username, password);
                validLB.setText("Login Berhasil");
                Thread validThread = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        
                    Platform.runLater(() -> {
                        stage.close();
                        ReminderScene reminderScene = new ReminderScene(stage, id);
                        reminderScene.show();
                    });
                });
                validThread.start();
            } catch (Exception e) {
                validLB.setText("Login Gagal");
            }
        });
        VBox loginss = new VBox(validLB, loginButton);
        loginss.setAlignment(Pos.CENTER);
        loginss.setSpacing(5);
        Label regisLB = new Label("Don't have an account yet");
        regisLB.getStyleClass().add("rgisLB");
        Button regisButton = new Button("Register");
        regisButton.getStyleClass().add("regisbut");
        regisButton.setOnAction(env -> {
            RegisterScene registerScene = new RegisterScene(stage);
            registerScene.show();
        });
        VBox registt = new VBox(regisLB, regisButton);
        registt.setAlignment(Pos.CENTER);
        registt.setSpacing(5);

        VBox loginButBox = new VBox(loginss, registt);
        loginButBox.setPadding(new Insets(20));
        loginButBox.setAlignment(Pos.CENTER);
        loginButBox.setSpacing(10);

        root.setBottom(loginButBox);

        // init scroll
        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);

        // init scene
        Scene loginScene = new Scene(scroll, 1440, 800);
        loginScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(loginScene);
        stage.show();
    }
}
