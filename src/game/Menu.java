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
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import java.awt.SystemColor;

public class Menu extends Snake {

    private JPanel contentPane;

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

    public Menu() {
        
        setTitle("SnakeGame\r\n");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\gihof\\OneDrive\\Imagens\\programação\\drive-download-20220914T180511Z-001\\cabecaBaixa.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);  
        
        setContentPane(contentPane);
        SpringLayout sl_contentPane = new SpringLayout();
        contentPane.setLayout(sl_contentPane);
        
        JButton exit = new JButton("Exit");
        sl_contentPane.putConstraint(SpringLayout.WEST, exit, 247, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, exit, -247, SpringLayout.EAST, contentPane);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setBackground(new Color(250, 128, 114));
        contentPane.add(exit);
        
        JButton start = new JButton("Start");
        sl_contentPane.putConstraint(SpringLayout.NORTH, exit, 6, SpringLayout.SOUTH, start);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, start, -295, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, start, 247, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, start, -247, SpringLayout.EAST, contentPane);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override 
                    public void run() {
                        JFrame ex = new Snake();
                        ex.setVisible(true);
                    }
                    
                });
                ///
                setVisible(false);
                //start - se timer == 0, volta pro menu
            }
        });
        start.setBackground(new Color(152, 251, 152));
        contentPane.add(start);
        
    }
    

}
