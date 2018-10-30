package dotsandboxestry;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Test extends javax.swing.JFrame implements ActionListener {

    private Field field;
    public CounterLabel userCounterLabel;
    // counter widget that displays computer's score
    public CounterLabel computerCounterLabel;
    NewGameDialogue newGameDialogue;
    int level=1;

    // gameplay instructions
    final String HOWTOPLAYTEXT = "How To Play\r\n" + "\r\n"
            + "The game consists of a field of dots.  Take turns with the\r\n"
            + "computer adding lines between the dots.  Complete a box to\r\n"
            + "get another turn.  Your boxes will show O.  The computer's\r\n"
            + "boxes get X.  Complete the most boxes to win!\r\n";

    // about box text
    final String ABOUTTEXT = "This game is developed by\n• Raisa Islam Nidha\n• MD. Ruhul Amin\n";

    /**
     * Set up a new game and show the frame
     *
     * @param cols number of columns of dots
     * @param rows number of rows of dots
     */
    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    public Test() {
        super();
        // set up the window, menu, and status bar
        initGUI();
        this.level=1;
        
    }

    public Test(int row, int col) {
        this();
        newGame(row, col);
        this.level=level;
    }
    public Test(int row, int col, int level) {
        this();
        this.level=level;
        newGame(row, col);
        this.level=level;
    }

    public void newGame(int cols, int rows) {
        // reset the counters

        this.userCounterLabel.reset();
        this.computerCounterLabel.reset();

        // if an old field exists, remove it
        if (this.field != null) {
            this.getContentPane().remove(this.field);
        }

        // create the new field
        this.field = new Field(cols, rows, this);
        System.out.println("Level = "+this.level);
        this.field.level=this.level;
        this.getContentPane().add(this.field);
        this.getContentPane().validate();
        this.field.Y=computerCounterLabel;
        this.field.X=userCounterLabel;
        // show the window (if this is the first game)
        this.setVisible(true);
    }


    public static void main(String[] argv) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewGameDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewGameDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewGameDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewGameDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        
        Test instance = new Test();
        instance.level=1;
        instance.newGame(3, 3);
    }

//	public Test() {
////		super();
//		initGUI();
//	}
    private void initGUI() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Dots and Boxes");
        this.setSize(xSize, ySize);
        BorderLayout thisLayout = new BorderLayout();
        this.getContentPane().setLayout(thisLayout);

        {// menu
            JMenuBar menuBar = new JMenuBar();
            this.setJMenuBar(menuBar);

            // Game menu
            JMenu gameMenu = new JMenu("Game");
            gameMenu.setMnemonic(java.awt.event.KeyEvent.VK_G);
            menuBar.add(gameMenu);

            // New Game menu item
            JMenuItem newGameMenuItem = new JMenuItem("New Game");
            newGameMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_N);
            newGameMenuItem.addActionListener(this);
            gameMenu.add(newGameMenuItem);

            // Exit menu item
            JMenuItem exitMenuItem = new JMenuItem("Exit");
            exitMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_X);
            exitMenuItem.addActionListener(this);
            gameMenu.add(exitMenuItem);

            // Help menu
            JMenu helpMenu = new JMenu("Help");
            helpMenu.setMnemonic(java.awt.event.KeyEvent.VK_H);
            menuBar.add(helpMenu);

            // How to Play menu item
            JMenuItem howToPlayMenuItem = new JMenuItem("How to Play");
            howToPlayMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_P);
            howToPlayMenuItem.addActionListener(this);
            helpMenu.add(howToPlayMenuItem);

            // About menu item
            JMenuItem aboutMenuItem = new JMenuItem("About");
            aboutMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_A);
            aboutMenuItem.addActionListener(this);
            helpMenu.add(aboutMenuItem);
        }

        {// status bar
            JPanel statusBarPanel = new JPanel();
            GridBagLayout statsuBarLayout = new GridBagLayout();
            statsuBarLayout.columnWeights = new double[]{0.05, 0.45, 0.45,
                0.05};
            statsuBarLayout.columnWidths = new int[]{7, 7, 7, 7};
            statusBarPanel.setLayout(statsuBarLayout);
            this.getContentPane().add(statusBarPanel, BorderLayout.SOUTH);
            statusBarPanel.setVisible(true);
            statusBarPanel.setFocusable(false);

            // user count
            this.userCounterLabel = new CounterLabel("Your Score: ");
            GridBagConstraints userLabelConstraints = new GridBagConstraints();
            userLabelConstraints.gridx = 1;
            statusBarPanel.add(userCounterLabel, userLabelConstraints);

            // computer count
            this.computerCounterLabel = new CounterLabel("Computer's Score: ");
            GridBagConstraints computerLabelConstraints = new GridBagConstraints();
            computerLabelConstraints.gridx = 2;
            statusBarPanel.add(computerCounterLabel, computerLabelConstraints);
        }
    }

    /**
     * Handles menu events. Implements ActionListener.actionPerformed().
     *
     * @param e object with information about the event
     */
    public void actionPerformed(ActionEvent e) {
		// System.out.println("actionPerformed: " + e.getActionCommand());

        // New Game menu item was selected
        if (e.getActionCommand().equals("New Game")) {
            // show modal new game dialog box
            new NewGameDialogue().setVisible(true);
            this.dispose();
        } // How to Play menu item was selected
        else if (e.getActionCommand().equals("How to Play")) {
            // show the instructional modal dialog box
            JOptionPane.showMessageDialog(this, HOWTOPLAYTEXT, "How To Play",
                    JOptionPane.PLAIN_MESSAGE);
        } // About menu item was selected
        else if (e.getActionCommand().equals("About")) {
            // show the modal about box
            JOptionPane.showMessageDialog(this, ABOUTTEXT,
                    "About Dots and Boxes", JOptionPane.PLAIN_MESSAGE);
        } // Exit menu item was selected
        else if (e.getActionCommand().equals("Exit")) {
            // dispose of the main window. Java VM will exit if there are no
            // other threads or windows.
            this.dispose();
        }
    }
}
