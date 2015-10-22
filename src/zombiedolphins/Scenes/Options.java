/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiedolphins.Scenes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import zombiedolphins.GraphicsView;
import zombiedolphins.Misc.KeyMap;

/**
 *
 * @author anton
 */
public class Options extends BorderPane implements Controllable {

    GraphicsView graphicsView;
    KeyMap km;
    Label key;
    Button btnNext;
    private boolean up, down, left, right, shoot, reload;

    public Options(GraphicsView gv) {
        km = new KeyMap();
        up = false;
        down = false;
        left = false;
        right = false;
        shoot = false;
        reload = false;
        this.graphicsView = gv;
        super.setStyle("-fx-background-color: yellow;");
        //Return button
        Button btnReturn = new Button("Return");
        btnReturn.setMinWidth(100);
        btnReturn.setMinHeight(20);

        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                graphicsView.setKeyMap(km);
                graphicsView.returnScene();
            }
        });
        Button btnKeyBinds = new Button("set keybindings");
        btnKeyBinds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setKeyUp();
            }
        });
        Button btnLoadKeyBinds = new Button("Load keybindings");
        btnLoadKeyBinds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    loadKeyBinds();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        });
        super.setPadding(new Insets(10, 10, 10, 10));
        super.setBottom(btnReturn);
        super.setCenter(btnKeyBinds);
        super.setRight(btnLoadKeyBinds);
    }

    private void setKeyUp() {
        key = new Label("UP");
        super.setLeft(key);
        up = true;
        btnNext = new Button("next");
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setKeyDown();
            }
        });
        super.setCenter(btnNext);
    }

    private void setKeyDown() {
        up = false;
        down = true;
        key = new Label("Down");
        super.setLeft(key);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setKeyLeft();
            }
        });
    }

    private void setKeyLeft() {
        down = false;
        left = true;
        key = new Label("Left");
        super.setLeft(key);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setKeyRight();
            }
        });
    }

    private void setKeyRight() {
        left = false;
        right = true;
        key = new Label("Right");
        super.setLeft(key);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setKeyShoot();
            }
        });
    }

    private void setKeyShoot() {
        right = false;
        shoot = true;
        key = new Label("Shoot");
        super.setLeft(key);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setKeyReload();
            }
        });
    }

    private void setKeyReload() {
        shoot = false;
        reload = true;
        key = new Label("Reload");
        super.setLeft(key);
        Button btnSave = new Button("save");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                reload = false;
                try {
                    saveKeyBinds();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        super.setCenter(btnSave);

    }

    private void loadKeyBinds() throws Exception {
        ObjectInputStream ois = null;
        try {
            FileInputStream fin = new FileInputStream("KeyMap.ser");
            ois = new ObjectInputStream(fin);
            km = (KeyMap) ois.readObject();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
            }
        }

    }

    private void saveKeyBinds() throws Exception {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fout = new FileOutputStream("KeyMap.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(km);
            System.out.println("hejsan");
        } finally {
            if (oos != null) {
                oos.close();
            }
        }

    }

    @Override
    public void onKeyEvent(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            if (up) {
                km.setUp(event.getCode());
            } else if (down) {
                km.setDown(event.getCode());
            } else if (left) {
                km.setLeft(event.getCode());
            } else if (right) {
                km.setRight(event.getCode());
            } else if (shoot) {
                km.setShoot(event.getCode());
            } else if (reload) {
                km.setReload(event.getCode());
            }
        }
    }
}
