// package eduremind.scene;

// import eduremind.controller.ControllerDB;
// import javafx.application.Platform;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.TextAlignment;
// import javafx.stage.Stage;

// public class RegisterScene {
//     private Stage stage;

//     public RegisterScene(Stage stage) {
//         this.stage = stage;
//     }

//     public void show() {
//         // init borderpone
//         BorderPane root = new BorderPane();
//         root.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
//         root.getStyleClass().add("scene1");

//         // Menambahkan image
//         Image logoss = new Image(getClass().getClassLoader().getResourceAsStream("img/notification.png"));
//         ImageView loginLogos = new ImageView(logoss);
//         loginLogos.setPreserveRatio(true);
//         loginLogos.setFitWidth(150);
//         loginLogos.setFitHeight(150);
//         loginLogos.setId("logo1");
//         ;
//         HBox logoBox = new HBox(loginLogos);
//         logoBox.setAlignment(Pos.CENTER);
//         root.setTop(logoBox);

//         // Email Field dan Password Field

//         Label welcome = new Label("Hellooo👋");
//         welcome.setTextAlignment(TextAlignment.LEFT);
//         welcome.getStyleClass().add("welcome");
//         Label greeting = new Label("Welcome to EDUREMIND, input your profile below ");
//         greeting.setTextAlignment(TextAlignment.LEFT);
//         greeting.getStyleClass().add("greet");
//         VBox greetBox = new VBox(welcome, greeting);

//         Label emailLB = new Label("Username");
//         emailLB.setAlignment(Pos.CENTER_LEFT);
//         emailLB.getStyleClass().add("loginLB");
//         TextField emailTF = new TextField();
//         emailTF.setPromptText("Email Baru");
//         emailTF.setAlignment(Pos.CENTER_LEFT);
//         emailTF.getStyleClass().add("loginTF");
//         Label passLB = new Label("Password");
//         passLB.getStyleClass().add("loginLB");
//         passLB.setAlignment(Pos.CENTER_LEFT);
//         PasswordField passTF = new PasswordField();
//         passTF.setPromptText("Password Baru");
//         passTF.getStyleClass().add("loginTF");
//         passTF.setAlignment(Pos.CENTER_LEFT);
//         VBox logTFBox = new VBox(emailLB, emailTF, passLB, passTF);
//         logTFBox.setSpacing(10);

//         VBox loginBox = new VBox(greetBox, logTFBox);
//         loginBox.setPadding(new Insets(130, 191, 102, 191));
//         loginBox.setSpacing(17.5);

//         root.setCenter(loginBox);

//         // Tombol Register
//         Label validLB= new Label("  ");
//         validLB.getStyleClass().add("rgisLB");
//         Button regisButton = new Button("Register");
//         regisButton.setOnAction(env -> {
//             String username = emailTF.getText();
//             String password = passTF.getText();
//             try {
//                 ControllerDB.insrtRegis(username, password);
//                 validLB.setText("Register Berhasil");
//                 Thread validThread = new Thread(() -> {
//                     try {
//                         Thread.sleep(3000);
//                     } catch (InterruptedException e) {
//                         e.printStackTrace();
//                     }
        
//                     Platform.runLater(() -> {
//                         stage.close();
//                     LoginScene loginScene = new LoginScene(stage);
//                     loginScene.show();
//                     });
//                 });
//                 validThread.start();
//             } catch (Exception e) {
//                 validLB.setText("Register Gagal");
//             }
//         });
//         regisButton.getStyleClass().add("regisbut");
//         VBox registt = new VBox(regisButton);
//         registt.setAlignment(Pos.CENTER);
//         registt.setSpacing(5);

//         //git commit -m "feat : tombol kembali ke login"
//         Button backButton = new Button("Back");
//         backButton.getStyleClass().add("logBut");
//         backButton.setOnAction(env ->{
//             LoginScene loginScene = new LoginScene(stage);
//             loginScene.show();
//         });

//         VBox registbutBox = new VBox(validLB, registt, backButton);
//         registbutBox.setPadding(new Insets(20));
//         registbutBox.setAlignment(Pos.CENTER);
//         registbutBox.setSpacing(10);

//         root.setBottom(registbutBox);

//         // init scroll
//         ScrollPane scroll = new ScrollPane(root);
//         scroll.setFitToWidth(true);

//         // init scene
//         Scene loginScene = new Scene(scroll, 1440, 800);
//         loginScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
//         stage.setScene(loginScene);
//         stage.show();
//     }
// }
