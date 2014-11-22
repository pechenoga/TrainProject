package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Light on 05.11.2014.
 */
public class Application {
    JPanel mainPanel;
    MapPanel mapPanel;
    JPanel elementsPanel;
    JPanel interruptsPanel;
    JFrame frame;

    BufferedImage train1Image;
    JLabel train1Label;
    JMenu startButton;

    Hashtable<String, Integer> prop;

    public Application() {
        // get properties
        prop = Config.getProperties();

        frame = new JFrame("TrainProject");
        mainPanel = new JPanel();
        elementsPanel = new JPanel();
        interruptsPanel = new JPanel();

        // map
        mapPanel = new MapPanel();

        mainPanel.setLayout(null);
        mapPanel.setBounds(prop.get("Map.PADDING_LEFT"), prop.get("Map.PADDING_TOP"), prop.get("Map.WIDTH"), prop.get("Map.HEIGHT"));
        elementsPanel.setBounds(prop.get("Map.WIDTH") + prop.get("ElementsPanel.PADDING_LEFT"), prop.get("ElementsPanel.PADDING_TOP"), prop.get("ElementsPanel.WIDTH"), prop.get("Map.HEIGHT"));
        interruptsPanel.setBounds(prop.get("InterruptsPanel.PADDING_LEFT"), prop.get("Map.HEIGHT") + prop.get("InterruptsPanel.PADDING_TOP"), prop.get("Window.WIDTH"), prop.get("InterruptsPanel.HEIGHT"));

        elementsPanel.setLayout(new GridLayout(5, 2));
        // temporarily settings
        mapPanel.setBackground(Color.ORANGE);
        elementsPanel.setBackground(Color.GREEN);
        interruptsPanel.setBackground(Color.BLUE);

        // main menu
        setMenuBar();

        // Application.class.getClassLoader().getResource("test_train.png").getPath();
        try {
            train1Image = ImageIO.read(new File("resources\\img\\test_train.png"));
        } catch (IOException e) {
            System.out.println("Problem with train image file.");
        } catch (NullPointerException e) {
            System.out.println("Error. Invalid path to train image.");
        }
        train1Label = new JLabel(new ImageIcon(train1Image));
        mapPanel.add(train1Label);

        // elements
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));
        elementsPanel.add(new JLabel("Test"));

        // add all parts to main Panel
        mainPanel.add(mapPanel);
        mainPanel.add(elementsPanel);
        mainPanel.add(interruptsPanel);

        frame.add(mainPanel);
        frame.setBounds(0,0,prop.get("Window.WIDTH"),prop.get("Window.HEIGHT"));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        train1Label.setBounds(0, 0, 23, 21);
        runApp();
    }

    private void setMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuItem = getMenu();
        menuBar.add(menuItem);
        menuItem = getTranslation();
        menuBar.add(menuItem);
        startButton = new JMenu("Пуск");
        menuBar.add(startButton);
        startButton.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                System.out.println("menuSelected");
//                runApp();
//                mapPanel.repaint();
            }
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });
        frame.setJMenuBar(menuBar);
    }

    private JMenu getMenu() {
        JMenu myMenu = new JMenu("Меню");
        JMenuItem myItem = new JMenuItem("Пункт 1                    ");
        myMenu.add(myItem);
        myItem = new JMenuItem("Пункт 2");
//        myItem.setEnabled(false);
        myMenu.add(myItem);
        myItem = new JMenuItem("Пункт 3");
        myMenu.add(myItem);
        myItem = new JMenuItem("Пункт 4");
        myMenu.add(myItem);
        myMenu.addSeparator();
        myItem = new JMenuItem("Вихід");
        myMenu.add(myItem);
        myItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int exitResult = JOptionPane.showConfirmDialog(null, "Ви впевнені, що хочете завершити роботу програми?", "Вихід з програми", JOptionPane.ERROR_MESSAGE);
                if (exitResult == 0) {
                    System.exit(0);
                }
            }
        });
        return myMenu;
    }

    private JMenu getTranslation() {
        JMenu myMenu = new JMenu("Трансляція");
        JMenuItem myItem = new JMenuItem("Пункт 1          ");
        myMenu.add(myItem);
        myItem = new JMenuItem("Пункт 2");
        myMenu.add(myItem);
        myItem = new JMenuItem("Пункт 3");
        myMenu.add(myItem);
        return myMenu;
    }

    private void runApp() {
        Thread thread = new Thread();
        for (int i = 0; i < 20; i++) {
            train1Label.setBounds(i*10, 0, 23, 21);
            try {
                Thread.sleep(100);
//                System.out.println("test");
            } catch (InterruptedException ignored) {
            }
            mapPanel.repaint();
        }
    }

    public static void main(String[] args) {
        Application app = new Application();
    }

}
