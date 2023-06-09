package eduremind.scene;

import eduremind.Create_Delete_Task.CreateDeleteTask;
import eduremind.controller.ControllerDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReminderScene extends CreateDeleteTask {
    // HANDLING STAGE
    private Stage stage;
    // handling container dan method textfield yang ingin dibuat
    private VBox tugasBox;
    ObservableList<String> tasks = FXCollections.observableArrayList();

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
        tugasBox.getStyleClass().add("scene1");
        tugasBox.setMaxWidth(1700);
        tugasBox.minWidth(1700);
        // container tugss
        VBox root = new VBox();
        root.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        root.getStyleClass().add("scene1");

        // Buat title & logo
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
        logobox.setPadding(new Insets(32, 200, 30, 100));

        updateVBox();

        ScrollPane scroll = new ScrollPane(tugasBox);
        scroll.getStyleClass().add("scene1");
        scroll.setMaxWidth(1700);
        scroll.setFitToHeight(true);
        scroll.setFitToHeight(true);
        scroll.setStyle("-fx-hbar-policy: never; -fx-vbar-policy: never;");
        // root.setCenter(scroll);

        // buat tombol back and plus
        Image arrow = new Image(getClass().getClassLoader().getResourceAsStream("img/Arrow.png"));
        ImageView backs = new ImageView(arrow);
        Button back = new Button();
        back.setGraphic(backs);
        back.setPrefWidth(55);
        back.setAlignment(Pos.BOTTOM_LEFT);
        back.getStyleClass().add("plusback");
        back.setOnAction(env -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        Image tambah = new Image(getClass().getClassLoader().getResourceAsStream("img/Plus.png"));
        ImageView pluslus = new ImageView(tambah);
        Button plus = new Button();
        plus.setGraphic(pluslus);
        plus.getStyleClass().add("plusback");
        plus.setPrefWidth(61);
        plus.setPrefHeight(58);
        plus.setAlignment(Pos.BOTTOM_RIGHT);
        plus.setOnAction(env -> {
            createTask();
        });

        HBox plusbackbox = new HBox(back, plus);
        plusbackbox.setSpacing(780);
        plusbackbox.setAlignment(Pos.CENTER);
        plusbackbox.setPadding(new Insets(10, 30, 54, 30));

        // membuat sidebar
        Image lonceng = new Image(getClass().getClassLoader().getResourceAsStream("img/lonceng_putih.png"));
        ImageView lonPutih = new ImageView(lonceng);
        lonPutih.setFitWidth(90);
        lonPutih.setFitHeight(90);
        Button penginfgta = new Button();
        penginfgta.setGraphic(lonPutih);
        penginfgta.getStyleClass().add("sidebarBut");
        penginfgta.setOnAction(env -> {
            ReminderScene reminderScene = new ReminderScene(stage, getId());
            reminderScene.show();
        });
        StackPane pengingat = new StackPane(penginfgta);
        pengingat.setAlignment(Pos.CENTER);

        Image note = new Image(getClass().getClassLoader().getResourceAsStream("img/note_putih.png"));
        ImageView notePutih = new ImageView(note);
        notePutih.setFitWidth(97);
        notePutih.setFitHeight(97);
        Button catatan = new Button();
        catatan.setGraphic(notePutih);
        catatan.getStyleClass().add("sidebarBut");
        catatan.setOnAction(env -> {
            NoteScene noteScene = new NoteScene(stage, getId());
            noteScene.show();
        });
        StackPane notess = new StackPane(catatan);
        notess.setAlignment(Pos.CENTER);

        VBox sidebar = new VBox(40, pengingat, notess);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(200);
        sidebar.setFillWidth(true);
        sidebar.setPadding(new Insets(40, 10, 0, 0));

        // init container utama
        HBox remindBox = new HBox(sidebar, root);
        remindBox.setMaxWidth(1400);
        remindBox.setMaxHeight(850);
        remindBox.getStyleClass().add("scene1");

        if (tugasBox.getChildren().isEmpty()) {
            Label kosonglb = new Label("                           Tidak ada tugas terbaru       ");
            kosonglb.setMinWidth(Region.USE_PREF_SIZE);
            kosonglb.setMinHeight(Region.USE_PREF_SIZE);
            kosonglb.setMaxWidth(Double.MAX_VALUE);
            kosonglb.setMaxHeight(Double.MAX_VALUE);
            kosonglb.getStyleClass().add("kosongg");
            tugasBox.getChildren().add(kosonglb);
            tugasBox.setAlignment(Pos.TOP_LEFT);
            tugasBox.setSpacing(5);
            tugasBox.setPadding(new Insets(30, 10, 35, 10));
            tugasBox.setMinWidth(800);
        }

        //getchildren all task
        root.getChildren().addAll(logobox, scroll, plusbackbox);

        // init scene
        Scene remindScene = new Scene(remindBox, 1200, 800);
        remindScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(remindScene);
        stage.show();
    }

    @Override
    public void createTask() {
        if (tugasBox.getChildren().get(0) instanceof Label) {
        tugasBox.getChildren().remove(0);
        }
        // buat textfield untuk judul tugas
        TextField taskTF = new TextField();
        taskTF.setEditable(false);
        taskTF.setPromptText("Tugas ");
        taskTF.getStyleClass().add("remindTF");
        taskTF.setMinWidth(800);
        taskTF.setOnAction(env -> {
            taskTF.setEditable(false);
            if (ControllerDB.foundTugas(getId(), taskTF.getText())) {
                ControllerDB.updateTugas(getId(), taskTF.getText());
            } else {
                ControllerDB.insertTugas(getId(), taskTF.getText());
            }
        });

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
        hapus.setOnAction(env -> {
            ControllerDB.deleteTugas(getId(), taskTF.getText());
            ReminderScene reminderScene = new ReminderScene(stage, this.getId());
            reminderScene.show();
        });
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
        tulis.setOnAction(env -> {
            ControllerDB.deleteTugas(getId(), taskTF.getText());
            taskTF.setEditable(true);
        });
        tulis.getStyleClass().add("tombolRM");
        StackPane menulis = new StackPane(tulis);
        menulis.setPrefWidth(5);
        menulis.setPadding(new Insets(0, 2, 0, 2));

        HBox creaBox = new HBox(taskTF, menulis, exx);
        creaBox.setAlignment(Pos.CENTER);
        creaBox.setSpacing(10);
        creaBox.setPadding(new Insets(30, 10, 35, 10));

        tugasBox.getChildren().add(creaBox);
        tugasBox.setMinWidth(800);
    }

    @Override
    public void deleteTask(int i) {
        if (tasks.size() >= 0) {
            tugasBox.getChildren().remove(i);
            tugasBox.setMinWidth(800);
            tasks.remove(i);
        }

        if (tasks.isEmpty()) {
            Label kosonglb = new Label("                           Tidak ada tugas terbaru");
            kosonglb.getStyleClass().add("kosongg");
            kosonglb.setMinWidth(Region.USE_PREF_SIZE);
            kosonglb.setMinHeight(Region.USE_PREF_SIZE);
            kosonglb.setMaxWidth(Double.MAX_VALUE);
            kosonglb.setMaxHeight(Double.MAX_VALUE);
            tugasBox.getChildren().add(kosonglb);
            tugasBox.setAlignment(Pos.TOP_LEFT);
            tugasBox.setSpacing(5);
            tugasBox.setPadding(new Insets(30, 10, 35, 10));
        }
        updateVBox();
    }

    private void updateVBox() {
        tugasBox.getChildren().clear();
        tasks.addAll(ControllerDB.getAllTugas(getId()));
        try {
            for (int i = 0; i < tasks.size(); i++) {
                // final int index = i;
                // buat container judul tugas
                // buat textfield untuk judul tugas
                TextField taskTF = new TextField();
                taskTF.setEditable(false);
                taskTF.setText(tasks.get(i));
                taskTF.setPromptText("Tugas ");
                taskTF.getStyleClass().add("remindTF");
                taskTF.setMinWidth(800);
                taskTF.setOnAction(env -> {
                    taskTF.setEditable(false);
                    if (ControllerDB.foundTugas(getId(), taskTF.getText())) {
                        ControllerDB.updateTugas(getId(), taskTF.getText());
                    } else {
                        ControllerDB.insertTugas(getId(), taskTF.getText());
                    }
                });

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
                hapus.setOnAction(env -> {
                    ControllerDB.deleteTugas(getId(), taskTF.getText());
                    ReminderScene reminderScene = new ReminderScene(stage, this.getId());
                    reminderScene.show();
                });
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
                tulis.setOnAction(env -> {
                    ControllerDB.deleteTugas(getId(), taskTF.getText());
                    taskTF.setEditable(true);
                });
                tulis.getStyleClass().add("tombolRM");
                StackPane menulis = new StackPane(tulis);
                menulis.setPrefWidth(5);
                menulis.setPadding(new Insets(0, 2, 0, 2));

                HBox creaBox = new HBox(taskTF, menulis, exx);
                creaBox.setAlignment(Pos.CENTER);
                creaBox.setSpacing(5);
                creaBox.setPadding(new Insets(30, 10, 35, 10));

                tugasBox.getChildren().add(creaBox);
                tugasBox.setAlignment(Pos.CENTER);
                tugasBox.setMinWidth(800);
            }
        } catch (Exception e) {
            Label kosonglb = new Label("                           Tidak ada tugas terbaru");
            kosonglb.getStyleClass().add("kosongg");
            kosonglb.setMinWidth(Region.USE_PREF_SIZE);
            kosonglb.setMinHeight(Region.USE_PREF_SIZE);
            kosonglb.setMaxWidth(Double.MAX_VALUE);
            kosonglb.setMaxHeight(Double.MAX_VALUE);
            tugasBox.getChildren().add(kosonglb);
            tugasBox.setAlignment(Pos.TOP_LEFT);
            tugasBox.setSpacing(5);
            tugasBox.setPadding(new Insets(30, 10, 35, 10));
            tugasBox.setAlignment(Pos.CENTER);
        }

    }
}
