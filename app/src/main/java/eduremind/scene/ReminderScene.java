package eduremind.scene;

import java.util.ArrayList;

import eduremind.controller.ControllerDB;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReminderScene {
    // HANDLING STAGE
    private Stage stage;
    // handling container dan method textfield yang ingin dibuat
    private int textFieldcount;
    private VBox tugasBox;
    private int id;

    public ReminderScene(Stage stage, Integer id) {
        this.stage = stage;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void show() {
        tugasBox = new VBox();
        // init borderpane
        BorderPane root = new BorderPane();
        root.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        root.getStyleClass().add("scene1");

//         // Buat title & logo
        Image logoss = new Image(getClass().getClassLoader().getResourceAsStream("img/notification.png"));
        ImageView mainLogos = new ImageView(logoss);
        mainLogos.setPreserveRatio(true);
        mainLogos.setFitWidth(85);
        mainLogos.setFitHeight(90);
        Label title1 = new Label("Edu");
        title1.getStyleClass().add("eduW");
        Label title2 = new Label("Remind");
        title2.getStyleClass().add("remindW");
        HBox logobox = new HBox(mainLogos, title1, title2);
        logobox.setSpacing(10);
        logobox.setAlignment(Pos.CENTER);
        logobox.setPadding(new Insets(32, 100, 66, 100));

        root.setTop(logobox);
        BorderPane.setAlignment(logobox, Pos.CENTER);

//         // tampilkan jika kosong
//         // Label kosonglb = new Label(" Tidak ada tugas terbaru");
//         // kosonglb.getStyleClass().add("kosongg");
//         // tugasBox = new VBox();
//         // tugasBox.getStyleClass().add("scene1");
//         // tugasBox.setMaxWidth(1800);
//         // tugasBox.setAlignment(Pos.CENTER);
//         // if(tugasBox.getChildren().isEmpty()){
//         // tugasBox.getChildren().add(kosonglb);
//         // tugasBox.setAlignment(Pos.TOP_LEFT);
//         // tugasBox.setSpacing(5);
//         // tugasBox.setPadding(new Insets(30, 10, 35, 10));
//         // }
        try {
            ArrayList<String> tasks = ControllerDB.getAllTugas(getId());
            for (String task : tasks) {
                // buat container judul tugas
                // buat textfield untuk judul tugas
                TextField taskTF = new TextField();
                taskTF.setText(task);
                taskTF.setPromptText("Tugas " + textFieldcount);
                taskTF.getStyleClass().add("remindTF");
                taskTF.setPrefWidth(9000);
                // taskTF.setOnAction(env -> {
                //     taskTF.setEditable(false);
                //     ControllerDB.insertTugas(getId(), taskTF.getText());
                // });

                // commit feat : buat tombol silang/hapus
                Image silang = new Image(getClass().getClassLoader().getResourceAsStream("img/Silang.png"));
                ImageView kali = new ImageView(silang);
                kali.setFitWidth(65);
                kali.setFitHeight(65);
                Button hapus = new Button();
                hapus.setGraphic(kali);
                hapus.setPrefWidth(20);
                hapus.setPrefHeight(35);
                hapus.getStyleClass().add("tombolRM");
                // hapus.setOnAction(env -> {
                //     deleteTF();
                //     ControllerDB.deleteTugas(getId(), taskTF.getText());
                // });
                StackPane exx = new StackPane(hapus);
                exx.setPrefWidth(5);
                exx.setPadding(new Insets(0, 2, 0, 2));

                // commit feat : buat tombol edit textfield
                Image writepic = new Image(getClass().getClassLoader().getResourceAsStream("img/tulis.png"));
                ImageView writer = new ImageView(writepic);
                writer.setFitWidth(65);
                writer.setFitHeight(65);
                Button tulis = new Button();
                tulis.setGraphic(writer);
                tulis.setPrefWidth(20);
                tulis.setPrefHeight(35);
                // tulis.setOnAction(env -> {
                //     taskTF.setEditable(true);
                //     ControllerDB.updateTugas(getId(), taskTF.getText());
                // });
                tulis.getStyleClass().add("tombolRM");
                StackPane menulis = new StackPane(tulis);
                menulis.setPrefWidth(5);
                menulis.setPadding(new Insets(0, 2, 0, 2));

                HBox creaBox = new HBox(taskTF, menulis, exx);
                creaBox.setAlignment(Pos.CENTER);
                creaBox.setSpacing(10);
                creaBox.setPadding(new Insets(30, 10, 35, 10));

                tugasBox.getChildren().add(creaBox);
                tugasBox.setAlignment(Pos.CENTER);
            }
        } catch (Exception e) {
            Label kosonglb = new Label("                           Tidak ada catatan terbaru");
            kosonglb.getStyleClass().add("kosongg");
            tugasBox.getChildren().add(kosonglb);
            tugasBox.setAlignment(Pos.TOP_LEFT);
            tugasBox.setSpacing(5);
            tugasBox.setPadding(new Insets(30, 10, 35, 10));
            tugasBox.setAlignment(Pos.CENTER);
        }
        ScrollPane scroll = new ScrollPane(tugasBox);
        scroll.setFitToHeight(true);
        scroll.setFitToHeight(true);
        scroll.setStyle("-fx-hbar-policy: never; -fx-vbar-policy: never;");
        root.setCenter(scroll);

        // buat tombol back and plus
        Image arrow = new Image(getClass().getClassLoader().getResourceAsStream("img/Arrow.png"));
        ImageView backs = new ImageView(arrow);
        Button back = new Button();
        back.setGraphic(backs);
        back.setPrefWidth(55);
        back.setAlignment(Pos.BOTTOM_LEFT);
        back.getStyleClass().add("plusback");
//         back.setOnAction(env -> {
//             LoginScene loginScene = new LoginScene(stage);
//             loginScene.show();
//         });

        Image tambah = new Image(getClass().getClassLoader().getResourceAsStream("img/Plus.png"));
        ImageView pluslus = new ImageView(tambah);
        Button plus = new Button();
        plus.setGraphic(pluslus);
        plus.getStyleClass().add("plusback");
        plus.setPrefWidth(61);
        plus.setPrefHeight(58);
        plus.setAlignment(Pos.BOTTOM_RIGHT);
//         plus.setOnAction(env -> {
//             createTF();
//         });

        HBox plusbackbox = new HBox(back, plus);
        plusbackbox.setSpacing(869);
        plusbackbox.setAlignment(Pos.CENTER);
        plusbackbox.setPadding(new Insets(10, 30, 54, 95));

        root.setBottom(plusbackbox);

        Image putih = new Image(getClass().getClassLoader().getResourceAsStream("img/lonceng_putih.png"));
        ImageView white = new ImageView(putih);
        VBox right = new VBox(white);
        right.setMaxWidth(1);
        right.getStyleClass().add("scene1");
        root.setRight(right);

        // membuat sidebar
        Image lonceng = new Image(getClass().getClassLoader().getResourceAsStream("img/lonceng_putih.png"));
        ImageView lonPutih = new ImageView(lonceng);
        lonPutih.setFitWidth(90);
        lonPutih.setFitHeight(90);
        Button penginfgta = new Button();
        penginfgta.setGraphic(lonPutih);
        penginfgta.getStyleClass().add("sidebarBut");
//         penginfgta.setOnAction(env -> {
//             ReminderScene reminderScene = new ReminderScene(stage, getId());
//             reminderScene.show();
//         });
        StackPane pengingat = new StackPane(penginfgta);
        pengingat.setAlignment(Pos.CENTER);

        Image note = new Image(getClass().getClassLoader().getResourceAsStream("img/note_putih.png"));
        ImageView notePutih = new ImageView(note);
        notePutih.setFitWidth(97);
        notePutih.setFitHeight(97);
        Button catatan = new Button();
        catatan.setGraphic(notePutih);
        catatan.getStyleClass().add("sidebarBut");
//         catatan.setOnAction(env -> {
//             NoteScene noteScene = new NoteScene(stage, getId());
//             noteScene.show();
//         });
        StackPane notess = new StackPane(catatan);
        notess.setAlignment(Pos.CENTER);

        VBox sidebar = new VBox(40, pengingat, notess);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(200);
        sidebar.setFillWidth(true);
        sidebar.setPadding(new Insets(40, 10, 0, 0));

//         // init container utama
//         HBox remindBox = new HBox(sidebar, root);
//         remindBox.setMaxWidth(1600);
//         remindBox.setMaxHeight(850);
//         remindBox.getStyleClass().add("scene1");

//         if (tugasBox.getChildren().isEmpty()) {
//             Label kosonglb = new Label("                           Tidak ada tugas terbaru");
//             kosonglb.getStyleClass().add("kosongg");
//             tugasBox.getChildren().add(kosonglb);
//             tugasBox.setAlignment(Pos.TOP_LEFT);
//             tugasBox.setSpacing(5);
//             tugasBox.setPadding(new Insets(30, 10, 35, 10));
//         }

//         // borderpane margin
//         BorderPane.setMargin(right, new Insets(5));

//         // init scene
//         Scene remindScene = new Scene(remindBox, 1440, 800);
//         remindScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
//         stage.setScene(remindScene);
//         stage.show();
    }

//     private void createTF() {
//         if (tugasBox.getChildren().get(0) instanceof Label) {
//             tugasBox.getChildren().remove(0);
//         }

//         textFieldcount++;

//         // buat container judul tugas
//         // buat textfield untuk judul tugas
//         TextField taskTF = new TextField();
//         taskTF.setPromptText("Tugas " + textFieldcount);
//         taskTF.getStyleClass().add("remindTF");
//         taskTF.setPrefWidth(9000);
//         taskTF.setOnAction(env -> {
//             taskTF.setEditable(false);
//             ControllerDB.insertTugas(getId(), taskTF.getText());
//         });

//         // commit feat : buat tombol silang/hapus
//         Image silang = new Image(getClass().getClassLoader().getResourceAsStream("img/Silang.png"));
//         ImageView kali = new ImageView(silang);
//         kali.setFitWidth(65);
//         kali.setFitHeight(65);
//         Button hapus = new Button();
//         hapus.setGraphic(kali);
//         hapus.setPrefWidth(20);
//         hapus.setPrefHeight(35);
//         hapus.getStyleClass().add("tombolRM");
//         hapus.setOnAction(env -> {
//             deleteTF();
//             ControllerDB.deleteTugas(getId(), taskTF.getText());
//         });
//         StackPane exx = new StackPane(hapus);
//         exx.setPrefWidth(5);
//         exx.setPadding(new Insets(0, 2, 0, 2));

//         // commit feat : buat tombol edit textfield
//         Image writepic = new Image(getClass().getClassLoader().getResourceAsStream("img/tulis.png"));
//         ImageView writer = new ImageView(writepic);
//         writer.setFitWidth(65);
//         writer.setFitHeight(65);
//         Button tulis = new Button();
//         tulis.setGraphic(writer);
//         tulis.setPrefWidth(20);
//         tulis.setPrefHeight(35);
//         tulis.setOnAction(env -> {
//             taskTF.setEditable(true);
//             ControllerDB.updateTugas(getId(), taskTF.getText());
//         });
//         tulis.getStyleClass().add("tombolRM");
//         StackPane menulis = new StackPane(tulis);
//         menulis.setPrefWidth(5);
//         menulis.setPadding(new Insets(0, 2, 0, 2));

//         HBox creaBox = new HBox(taskTF, menulis, exx);
//         creaBox.setAlignment(Pos.CENTER);
//         creaBox.setSpacing(10);
//         creaBox.setPadding(new Insets(30, 10, 35, 10));

//         tugasBox.getChildren().add(creaBox);
//     }

//     private void deleteTF() {
//         if (textFieldcount >= 0) {
//             tugasBox.getChildren().remove(textFieldcount - 1);
//             textFieldcount--;
//         }

//         if (tugasBox.getChildren().isEmpty()) {
//             Label kosonglb = new Label("                           Tidak ada tugas terbaru");
//             kosonglb.getStyleClass().add("kosongg");
//             tugasBox.getChildren().add(kosonglb);
//             tugasBox.setAlignment(Pos.TOP_LEFT);
//             tugasBox.setSpacing(5);
//             tugasBox.setPadding(new Insets(30, 10, 35, 10));
//         }

//     }
}
