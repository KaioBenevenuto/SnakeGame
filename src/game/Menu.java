package game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
//import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Menu extends Board {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu frame = new Menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @return 
     */
    
    
//    *public void Board(){
//    *    super();
//    *}
    public Menu() {
        
//        *setTitle("SnakeGame\r\n");
//        *setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\gihof\\OneDrive\\Imagens\\programação\\drive-download-20220914T180511Z-001\\cabecaBaixa.png"));
//        *setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        *setBounds(100, 100, Board.Largura_Tela, Board.Altura_Tela);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(50, 60, 99));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        *setLocationRelativeTo(null);  
//        
//        *setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{235, 57, 51, 0};
        gbl_contentPane.rowHeights = new int[]{23, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override 
                    public void run() {
                        JFrame ex = new Snake();
                        ex.setVisible(true);
                    }
                });
                setVisible(false);
                //start - se timer == 0, volta pro menu
            }
        });
        start.setBackground(new Color(152, 251, 152));
        GridBagConstraints gbc_start = new GridBagConstraints();
        gbc_start.anchor = GridBagConstraints.SOUTH;
        gbc_start.fill = GridBagConstraints.HORIZONTAL;
        gbc_start.gridwidth = 2;
        gbc_start.insets = new Insets(0, 0, 5, 5);
        gbc_start.gridx = 1;
        gbc_start.gridy = 0;
        contentPane.add(start, gbc_start);
        
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setBackground(new Color(250, 128, 114));
        GridBagConstraints gbc_exit = new GridBagConstraints();
        gbc_exit.anchor = GridBagConstraints.NORTH;
        gbc_exit.fill = GridBagConstraints.HORIZONTAL;
        gbc_exit.gridwidth = 2;
        gbc_exit.insets = new Insets(0, 0, 0, 5);
        gbc_exit.gridx = 1;
        gbc_exit.gridy = 1;
        contentPane.add(exit, gbc_exit);
        
    }
    

}
