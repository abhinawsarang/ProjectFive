import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * File Manager to save and open the workspace
 *
 * @author Praveen Kumar P
 * @author Aditya Bajaj
 * @since April 29, 2020
 */
public class FileManager {

    /**
     * Saves all the tabs as a file
     */
    static public void save() {
        FileDialog dialog = new FileDialog(MainFrame.mainFrame, "Enter file name to Save");
        dialog.setMode(FileDialog.SAVE);
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file == null)
            return;
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        Component[] tabsToSave = MainFrame.PANEL_RIGHT.getComponents();
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tabsToSave);
            objectOutputStream.close();
            fileOutputStream.close();
            JOptionPane.showMessageDialog(MainFrame.mainFrame, "Saved as " + file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open a saved file and load it into the software
     */
    static void open() {
        FileDialog dialog = new FileDialog(MainFrame.mainFrame, "Select file to open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file == null)
            return;
        MainFrame.PANEL_RIGHT.removeAll();
        PanelRight.tabNum = 1;

        Component[] tabsToOpen;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            tabsToOpen = (Component[]) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            for (Component component : tabsToOpen) {
                PanelRightTab tab = (PanelRightTab) component;
                PanelRight.tabNum++;
                MainFrame.PANEL_RIGHT.addTab("Tab " + PanelRight.tabNum, tab);
                ListenersPanelRightTab.addAllListenersToTab(tab);
                tab.repaint();
            }
            Database.selectedTab = (PanelRightTab) tabsToOpen[0];

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}



