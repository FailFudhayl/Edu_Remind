package eduremind.scene;

import java.util.ArrayList;

import eduremind.Create_Delete_Task.CreateDeleteTask;
import eduremind.controller.ControllerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteScene extends CreateDeleteTask {
    // HANDLING STAGE
    private Stage stage;
    private int id;
    // handling container dan method textfield yang ingin dibuat
    private VBox tugasBox;
    ObservableList<String> notes = FXCollections.observableArrayList();

    public NoteScene(Stage stage, int id) {
        this.stage = stage;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void show() {
        // Init VBOX
        tugasBox = new VBox();
        tugasBox.getStyleClass().add("scene1");
        // init task container
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
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-hbar-policy: never; -fx-vbar-policy: never;");

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
        plus.setPrefWidth(61);
        plus.setPrefHeight(58);
        plus.setAlignment(Pos.BOTTOM_RIGHT);
        plus.getStyleClass().add("plusback");
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
        Button penginfgta = new Button(null, lonPutih);
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

        HBox noteBox = new HBox(sidebar, root);
        noteBox.setMaxWidth(1400);
        noteBox.setMaxHeight(850);
        noteBox.getStyleClass().add("scene1");

        // kondisi jika kosong
        if (tugasBox.getChildren().isEmpty()) {
            Label kosonglb = new Label("                           Tidak ada catatan terbaru");
            kosonglb.getStyleClass().add("kosongg");
            tugasBox.getChildren().add(kosonglb);
            tugasBox.setAlignment(Pos.TOP_LEFT);
            tugasBox.setSpacing(5);
            tugasBox.setPadding(new Insets(30, 10, 35, 10));
        }

        //getchildren semua task container
        root.getChildren().addAll(logobox, scroll,plusbackbox);

        // init scene
        Scene remindScene = new Scene(noteBox, 1200, 800);
        remindScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(remindScene);
        stage.show();
    }

    @Override
    public void createTask() {
        if (tugasBox.getChildren().get(0) instanceof Label) {
            tugasBox.getChildren().remove(0);
        }

        // buat container judul tugas
        TextArea taskTF = new TextArea();
        taskTF.setEditable(false);
        taskTF.setPromptText("Catatan ");
        taskTF.getStyleClass().add("noteTF");
        taskTF.setPrefWidth(9000);
        taskTF.setWrapText(true);
        KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        taskTF.setOnKeyPressed(evn -> {
            if (saveCombination.match(evn)) {
                taskTF.setEditable(false);
                if (ControllerDB.foundCatatan(getId(), taskTF.getText())) {
                    ControllerDB.updateCatatan(getId(), taskTF.getText());
                } else {
                    ControllerDB.insertCatatan(getId(), taskTF.getText());
                }
                evn.consume();
            }
        });

        // commit feat : buat tombol silang/hapus
        Image silang = new Image(getClass().getClassLoader().getResourceAsStream("img/Silang.png"));
        ImageView kali = new ImageView(silang);
        kali.setFitWidth(65);
        kali.setFitHeight(65);
        Button hapus = new Button(null, kali);
        hapus.setPrefWidth(20);
        hapus.setPrefHeight(35);
        hapus.getStyleClass().add("tombolRM");
        hapus.setOnAction(env -> {
            ControllerDB.deleteCatatan(getId(), taskTF.getText());
            NoteScene noteScene = new NoteScene(stage, this.getId());
            noteScene.show();
        });
        StackPane exx = new StackPane(hapus);
        exx.setPrefWidth(5);
        exx.setPadding(new Insets(0, 2, 0, 2));

        // commit feat : buat tombol edit textfield
        Image tulis = new Image(getClass().getClassLoader().getResourceAsStream("img/tulis.png"));
        ImageView nulis = new ImageView(tulis);
        nulis.setFitWidth(65);
        nulis.setFitHeight(65);
        Button write = new Button();
        write.setGraphic(nulis);
        write.setPrefWidth(20);
        write.setPrefHeight(35);
        write.getStyleClass().add("tombolRM");
        write.setOnAction(env -> {
            ControllerDB.deleteCatatan(getId(), taskTF.getText());
            taskTF.setEditable(true);
        });
        StackPane menulis = new StackPane(write);
        menulis.setPrefWidth(5);
        menulis.setPadding(new Insets(0, 2, 0, 2));

        VBox tombolCttn = new VBox(menulis, exx);
        tombolCttn.setSpacing(25);

        HBox creabox = new HBox(taskTF, tombolCttn);
        creabox.setAlignment(Pos.CENTER);
        creabox.setSpacing(10);
        creabox.setPadding(new Insets(30, 10, 35, 10));

        tugasBox.getChildren().add(creabox);
    }

    @Override
    public void deleteTask(int i) {
        if (notes.size() >= 0) {
            tugasBox.getChildren().remove(i);
            notes.remove(i);
        }

        if (notes.isEmpty()) {
            Label kosonglb = new Label("                           Tidak ada catatan terbaru");
            kosonglb.getStyleClass().add("kosongg");
            tugasBox.getChildren().add(kosonglb);
            tugasBox.setAlignment(Pos.TOP_LEFT);
            tugasBox.setSpacing(5);
            tugasBox.setPadding(new Insets(30, 10, 35, 10));
        }
        updateVBox();
    }

    private void updateVBox() {
        tugasBox.getChildren().clear();
        notes.addAll(ControllerDB.getAllCatatan(getId()));
        try {
            for (int i = 0; i < notes.size(); i++) {
                // final int index = i;
                // buat container judul tugas
                TextArea taskTF = new TextArea();
                taskTF.setEditable(false);
                taskTF.setText(notes.get(i));
                taskTF.setPromptText("Catatan ");
                taskTF.getStyleClass().add("noteTF");
                taskTF.setPrefWidth(9000);
                taskTF.setWrapText(true);
                KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
                taskTF.setOnKeyPressed(evn -> {
                    if (saveCombination.match(evn)) {
                        taskTF.setEditable(false);
                        if (ControllerDB.foundCatatan(getId(), taskTF.getText())) {
                            System.out.println("apa");
                            ControllerDB.updateCatatan(getId(), taskTF.getText());
                        } else {
                            ControllerDB.insertCatatan(getId(), taskTF.getText());
                        }
                        evn.consume();
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
                    ControllerDB.deleteCatatan(getId(), taskTF.getText());
                    NoteScene noteScene = new NoteScene(stage, this.getId());
                    noteScene.show();
                });
                StackPane exx = new StackPane(hapus);
                exx.setPrefWidth(5);
                exx.setPadding(new Insets(0, 2, 0, 2));

                // commit feat : buat tombol edit textfield
                Image tulis = new Image(getClass().getClassLoader().getResourceAsStream("img/tulis.png"));
                ImageView nulis = new ImageView(tulis);
                nulis.setFitWidth(65);
                nulis.setFitHeight(65);
                Button write = new Button();
                write.setGraphic(nulis);
                write.setPrefWidth(20);
                write.setPrefHeight(35);
                write.getStyleClass().add("tombolRM");
                write.setOnAction(env -> {
                    ControllerDB.deleteCatatan(getId(), taskTF.getText());
                    taskTF.setEditable(true);
                });
                StackPane menulis = new StackPane(write);
                menulis.setPrefWidth(5);
                menulis.setPadding(new Insets(0, 2, 0, 2));

                VBox tombolCttn = new VBox(menulis, exx);
                tombolCttn.setSpacing(25);

                HBox creabox = new HBox(taskTF, tombolCttn);
                creabox.setAlignment(Pos.CENTER);
                creabox.setSpacing(10);
                creabox.setPadding(new Insets(30, 10, 35, 10));

                tugasBox.getChildren().add(creabox);
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
    }
}
