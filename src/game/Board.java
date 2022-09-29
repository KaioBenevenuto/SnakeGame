package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{

	private final int Largura_Tela = 650; //  largura do tabuleiro
	private final int Altura_Tela = 650; // altura do tabuleiro
	private final int Tamanho_Ponto = 25; // tamanho da maça
	private final int Todos_Pontos = Largura_Tela * Altura_Tela / (Tamanho_Ponto * Tamanho_Ponto); // número de pontos do tabuleiro (900 = (300*300)/(10*10))
	private final int Posicao_Aleatoria = 19; // usado para calcular uma posição aleatória
	private final int DELAY = 450; // velocidade do game
	
	private final int x_coordenada_snake[] = new int[Todos_Pontos]; // coordenadas x_coordenada_snake do snake
	private final int y_coordenada_snake[] = new int[Todos_Pontos]; // coordenadas y_coordenada_snake do snake
	
	private int dots;
	private int apple_x;
	private int apple_y;
	
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean inGame = true;
	
	private Timer timer;
	private Image ball;
	private Image apple;
	private Image head;
	
	private void loadImages() {	
		//usar piskel para criar outro corpo e cabeça 
		ImageIcon iid = new ImageIcon(getClass().getResource("corpo.png"));
		ball = iid.getImage();
		
		ImageIcon iia = new ImageIcon(getClass().getResource("maca.png"));
		apple = iia.getImage();
		
		ImageIcon iih = new ImageIcon(getClass().getResource("cabecaDireita.png"));
		head = iih.getImage();
	}

	private void initGame() {
		dots = 2;
		
		for(int z = 0; z < dots; z++) {
			x_coordenada_snake[z] = 50 - z * Tamanho_Ponto;//alterei de 10 para Tamanho_Ponto se não a cobra começa expremida
			y_coordenada_snake[z] = 50;
			
			locateApple();
		
			timer = new Timer(DELAY, this);
			timer.start();
		}
	}	
		
		private void checkApple() {
			if((x_coordenada_snake[0] == apple_x) && (y_coordenada_snake[0] == apple_y)) {
				
				dots++;
				locateApple();
			}
		}
		
		private void locateApple() {
			int r = (int) (Math.random() * Posicao_Aleatoria);
			apple_x = ((r * Tamanho_Ponto));
			
			r = (int) (Math.random() * Posicao_Aleatoria);
			apple_y = ((r * Tamanho_Ponto));
		}
		
		private void move() {
			for(int z = dots; z > 0; z--){
				x_coordenada_snake[z] = x_coordenada_snake[(z-1)];
				y_coordenada_snake[z] = y_coordenada_snake[(z-1)];
			}
			//Deixar mais automatico, tem muita repetição
			if(leftDirection) {
				x_coordenada_snake[0] -= Tamanho_Ponto;
				ImageIcon iih = new ImageIcon(getClass().getResource("cabecaEsquerda.png"));
				head = iih.getImage();
			}
			
			if(rightDirection) {
				x_coordenada_snake[0] += Tamanho_Ponto;
				ImageIcon iih = new ImageIcon(getClass().getResource("cabecaDireita.png"));
				head = iih.getImage();
			}
			
			if(upDirection) {
				y_coordenada_snake[0] -= Tamanho_Ponto;
				ImageIcon iih = new ImageIcon(getClass().getResource("cabecaCima.png"));
				head = iih.getImage();
			}
			
			if(downDirection) {
				y_coordenada_snake[0] += Tamanho_Ponto;
				ImageIcon iih = new ImageIcon(getClass().getResource("cabecaBaixa.png"));
				head = iih.getImage();
			}
			
		}
		
		private void checkCollision() {
			for(int z = dots; z > 0; z--) {
				if((z > 4) && (x_coordenada_snake[0] == x_coordenada_snake[z] && (y_coordenada_snake[0] == y_coordenada_snake[z]))) {
					inGame = false;
				}
			}
			
			if(y_coordenada_snake[0] >= Altura_Tela) {
				inGame = false;
			}
			if(y_coordenada_snake[0] < 0) {
				inGame = false;
			}
			if(x_coordenada_snake[0] >= Largura_Tela) {
				inGame = false;
			}
			if(x_coordenada_snake[0] < 0) {
				inGame = false;
			}
			
			if(!inGame) {
				timer.stop();
			}
			
		}
		
		public Board() {
			addKeyListener(new TAdapter());
			setBackground(Color.black);
			setFocusable(true);
			
			setPreferredSize(new Dimension(Largura_Tela, Altura_Tela) );
			loadImages();
			initGame();
		}
		
		@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				doDrawing(g);
		}
		
		private void doDrawing(Graphics g) {
			if(inGame) {
				g.drawImage(apple, apple_x, apple_y, this);
				
				for(int z = 0; z < dots; z++) {
					if(z == 0) {
						g.drawImage(head, x_coordenada_snake[z], y_coordenada_snake[z], this);
					}else {
						g.drawImage(ball, x_coordenada_snake[z], y_coordenada_snake[z], this);
					}
				}
				Toolkit.getDefaultToolkit().sync();
			}else {
				gameOver(g);
			}
		}
		
		private void gameOver(Graphics g) {
			String msg = "Game Over";
			Font small = new Font("Helvetica", Font.BOLD, 14);
			FontMetrics metr = getFontMetrics(small);
			
			g.setColor(Color.red);
			g.setFont(small);
			g.drawString(msg,(Largura_Tela - metr.stringWidth(msg)) / 2, Altura_Tela / 2);
		}
		
		@Override
		public void  actionPerformed(ActionEvent e) {
			if(inGame) {
				checkApple();
				checkCollision();
				move();
			}
			repaint();
		}
		//Classe Interna --> 
		private class TAdapter implements KeyListener {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Stub de método gerado automaticamente
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
				if((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
					leftDirection = true;
					upDirection = false;
					downDirection = false;
				}
				
				if((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
					rightDirection = true;
					upDirection = false;
					downDirection = false;
				}
				
				if((key == KeyEvent.VK_UP) && (!downDirection)) {
					upDirection = true;
					rightDirection = false;
					leftDirection = false;
				}
				
				if((key == KeyEvent.VK_DOWN) && (!upDirection)) {
					downDirection = true;
					rightDirection = false;
					leftDirection = false;
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Stub de método gerado automaticamente
				
			}
						
		}
	}