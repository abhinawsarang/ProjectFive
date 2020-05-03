package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import Model.*;
import Model.Button;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * This class creates a View.Frame and adds two JPanels to the frame.
 *
 * @author Kartik Mathpal
 * @version 1.0
 */
public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;
    public  static RightPanel rightPanel;
    private Dimension screenSize = new Dimension(1800, 1000);
    public static JTabbedPane tabbedPane;
    static List<RightPanel> panelList = new ArrayList<>();
    static List<JTabbedPane> tabList = new ArrayList<>();
    static Map<JTabbedPane,RightPanel> map = new HashMap<>();
    public JButton button ;


    static int tabCount=0;
    JTabbedPane tab;

    public Frame() {
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        System.out.println(screenSize);
        String TITLE = "Project 5 - Team 8";
        setTitle(TITLE);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createTopPanel() {
        try {
            TopPanel topPanel = new TopPanel();
            Button button = new Button();

            button.addButtonsToTopPanel(topPanel);
            topPanel.setBounds(screenSize.width / 20, 0, 4 * screenSize.width / 6, screenSize.height/10);
            topPanel.setVisible(true);
            this.add(topPanel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createLeftPanel() {
        try {
            LeftPanel leftPanel = new LeftPanel();
            Button button = new Button();

            button.addButtonsToLeftPanel(leftPanel);
            leftPanel.setBounds(0, 0, screenSize.width / 8, screenSize.height);
            leftPanel.setVisible(true);
            this.add(leftPanel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createRightpanel() {
        try {
            tabbedPane = new JTabbedPane();
            tabbedPane.setBounds(screenSize.width / 7, screenSize.height/10, 4 * screenSize.width / 7, screenSize.height/2);
            tabbedPane.setVisible(true);
            rightPanel = new RightPanel();
            tabbedPane.add("Home Tab", rightPanel);//-----------
            tabList.add(tabbedPane);//base
            panelList.add(rightPanel); //default case
            //JButton button = new JButton("+ Tab");
            //button.addActionListener(new MyAction());
            //rightPanel.add(button);
            this.add(tabbedPane);
            tabbedPane.addChangeListener(new MyChange());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




public class MyChange implements ChangeListener{

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));   
        rightPanel=panelList.get(index);

    }
}
    public static class MyAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if (str.equals("#")) {
                String st = JOptionPane.showInputDialog(null, "Enter Tab Name.");
                if (!st.equals("")) {
                    RightPanel rp = new RightPanel();
                    tabCount++;
                    JTabbedPane tb = new JTabbedPane(JTabbedPane.TOP);
                    tb.setVisible(true);
                    tb.revalidate();
                    tb.repaint();
                    tb.add(st,rp);
                    tabList.add(tb);
                    tabbedPane.addTab(st,tb);
                    rightPanel=rp;
                    panelList.add(rp);
                    map.put(tb,rightPanel);
                }

            } else if (str.equals("Remove Tab")) {
            }
        }

    }

    public void createMenu() {
        this.setJMenuBar(new MenuBar());
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
//        frame.setLayout(new BorderLayout( ));
        frame.createLeftPanel();
        frame.createTopPanel();
        frame.createRightpanel();
        frame.createMenu();
//        JScrollBar hbar=new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 500);
//        JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
//        frame.getContentPane().add(hbar, BorderLayout.SOUTH);
//        frame.getContentPane().add(vbar, BorderLayout.EAST);
        frame.setVisible(true);




    }

}
