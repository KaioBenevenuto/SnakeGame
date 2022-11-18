package game;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Snake extends JFrame{
    
    public Snake() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\gihof\\OneDrive\\Imagens\\programação\\drive-download-20220914T180511Z-001\\cabecaBaixa.png"));
        add (new Board());
		
		setResizable(false);
		pack();
		
		setTitle("SnakeGame");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
    

}
