package myProject;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI_BatallaNaval extends JFrame {
    public static final String PATH ="/resources/";

    //Botones del juego
    private JButton botonPlay, botonHTP, mostrarPanel, empezarJuego;
    private Header headerProject;
    private JPanel panelBotones, panelLogo, panelMain, panelHTP, panelPrin, panelPos, panelPlayer, panelComputer, panelEnemigo, panelControlador;
    private ImageIcon tablero, bg, logo, imagen, tocado, agua, hundido;
    private JLabel labelTablero, labelBg, labelLogo;
    private Escucha escucha;
    private JTextArea textoHTP;
    private Celda[][] matrizCeldasprincipales = new Celda[11][11];
    private Celda[][] matrizCeldasPosicion = new Celda[11][11];
    private Celda[][] matrizCeldasEnemigas = new Celda[11][11];
    private int flagEnemigo = 1, aciertos = 0, aciertosEnemigo = 0;

    /**
     * Constructor of GUI class
     */
    public GUI_BatallaNaval(){
        initGUI();

        //Default JFrame configuration
        setTitle("Sea Battle");
        setSize(818,840);
        //pack();
        setResizable(true);
        setVisible(true);
        //Color de la ventana
        //this.getContentPane().setBackground(new Color(255,202,202));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource(PATH+"icon.png")).getImage());

        //Background
        bg = new ImageIcon(getClass().getResource(PATH+"bg-3.jpg"));
        labelBg = new JLabel("",bg,JLabel.CENTER);
        labelBg.setBounds(0,0,1920,1080);
        labelBg.setOpaque(true);
        add(labelBg);
    }

    /**
     * Añade las letras a la primera fila sin incluir la primera casilla [0][0]
     * @param celda
     * @param panel
     */
    private void añadirLetras(Celda[][] celda, JPanel panel){
        for(int i=1; i <=10; i++){
            String letra = null;
            celda[0][i].setBackground(Color.WHITE);

            if(i==1){
                letra = "A";
            }else if (i==2){
                letra = "B";
            }
            else if (i==3){
                letra = "C";
            }
            else if (i==4){
                letra = "D";
            }
            else if (i==5){
                letra = "E";
            }
            else if (i==6){
                letra = "F";
            }
            else if (i==7){
                letra = "G";
            }
            else if (i==8){
                letra = "H";
            }
            else if (i==9){
                letra = "I";
            }
            else if (i==10){
                letra = "J";
            }
            celda[0][i].setText(letra+"");
            celda[0][i].removeActionListener(escucha);
            panel.updateUI();
        }
    }

    /**
     * Añade los numeros a la primera columna sin incluir la primera casilla [0][0]
     * @param celda
     * @param panel
     */
    private void añadirNumeros(Celda[][] celda, JPanel panel){
        for(int i=1; i <=10; i++){
            celda[i][0].setBackground(Color.WHITE);
            celda[i][0].setText(i+"");
            celda[0][i].removeActionListener(escucha);
            celda[0][0].setBackground(Color.WHITE);
            panel.updateUI();
        }
    }

    /**
     * Ventana que contiene la logica del juego, incluye los tableros del jugador y del enemigo
     */
    private void ventanaPlay(){
        panelMain.setVisible(false);
        labelBg.setVisible(false);
        setResizable(false);
        //les añade los valores a las imagenes utilizadas
        tocado = new ImageIcon(getClass().getResource(PATH+"hurt.png"));
        agua = new ImageIcon(getClass().getResource(PATH+"water.png"));
        hundido = new ImageIcon(getClass().getResource(PATH+"defeated.png"));

        //tablero principal
        headerProject = new Header("TABLERO PRINCIPAL", Color.BLACK, new Font("Berlin Sans FB", Font.BOLD,15));
        labelTablero = new JLabel();
        tablero = new ImageIcon(getClass().getResource(PATH+"table-visual.jpg"));
        labelTablero.setIcon(tablero);

        //Panel principal
        panelPrin = new JPanel();
        panelPrin.setPreferredSize(new Dimension(600,400));
        panelPrin.setBorder(BorderFactory.createTitledBorder("Tablero prinicipal"));
        //Matriz del juego
        for(int i=0; i < 11; i++){
            for(int indice = 0; indice < 11; indice++){
                //logica para dibujar posiciones
                matrizCeldasprincipales[i][indice] = new Celda(i,indice,false);
                matrizCeldasprincipales[i][indice].setBackground(new Color(11,119,158));
                matrizCeldasprincipales[i][indice].setPreferredSize(new Dimension(48,27));
                //agrega el escucha
                matrizCeldasprincipales[i][indice].addActionListener(escucha);
                //pinta la matriz
                panelPrin.add(matrizCeldasprincipales[i][indice]);
            }
        }

        añadirLetras(matrizCeldasprincipales, panelPrin);
        añadirNumeros(matrizCeldasprincipales, panelPrin);

        //tablero de posicion
        headerProject = new Header("TABLERO DE POSICION", Color.BLACK, new Font("Berlin Sans FB", Font.BOLD,15));
        labelTablero = new JLabel();
        tablero = new ImageIcon(getClass().getResource(PATH+"table-visual.jpg"));
        labelTablero.setIcon(tablero);

        //Panel de posicion
        panelPos = new JPanel();
        panelPos.setPreferredSize(new Dimension(600,400));
        panelPos.setBorder(BorderFactory.createTitledBorder("Tablero de posición"));
        //Matriz del juego
        for(int i=0; i < 11; i++){
            for(int indice = 0; indice < 11; indice++){
                //logica para dibujar posiciones
                matrizCeldasPosicion[i][indice] = new Celda(i,indice,false);
                matrizCeldasPosicion[i][indice].setBackground(new Color(11,119,158));
                matrizCeldasPosicion[i][indice].setPreferredSize(new Dimension(48,27));
                //agrega el escucha
                matrizCeldasPosicion[i][indice].addActionListener(escucha);
                //pinta la matriz
                panelPos.add(matrizCeldasPosicion[i][indice]);
            }
        }

        añadirLetras(matrizCeldasPosicion, panelPos);
        añadirNumeros(matrizCeldasPosicion, panelPos);

        //tablero de enemigo
        headerProject = new Header("TABLERO ENEMIGO", Color.BLACK, new Font("Berlin Sans FB", Font.BOLD,15));
        labelTablero = new JLabel();
        tablero = new ImageIcon(getClass().getResource(PATH+"table-visual.jpg"));
        labelTablero.setIcon(tablero);

        //Panel de enemigo

        panelEnemigo = new JPanel();
        panelEnemigo.setPreferredSize(new Dimension(600,400));
        panelEnemigo.setBorder(BorderFactory.createTitledBorder("Tablero enemigo"));
        //Matriz del juego
        for(int i=0; i < 11; i++){
            for(int indice = 0; indice < 11; indice++){
                //logica para dibujar posiciones
                matrizCeldasEnemigas[i][indice] = new Celda(i,indice,false);
                matrizCeldasEnemigas[i][indice].setBackground(new Color(11,119,158));
                matrizCeldasEnemigas[i][indice].setPreferredSize(new Dimension(48,27));
                //pinta la matriz
                panelEnemigo.add(matrizCeldasEnemigas[i][indice]);
                //pone los botones invisibles inicialmente
                matrizCeldasEnemigas[i][indice].setVisible(false);
            }
        }

        añadirLetras(matrizCeldasEnemigas, panelEnemigo);
        añadirNumeros(matrizCeldasEnemigas, panelEnemigo);

        panelControlador = new JPanel();
        panelControlador.setPreferredSize(new Dimension(600,400));
        panelControlador.setBorder(BorderFactory.createTitledBorder("Tablero controlador"));
        mostrarPanel = new JButton();
        mostrarPanel.setBackground(new Color(11,119,158));
        mostrarPanel.setText("Mostrar tablero enemigo");
        //agrega el escucha
        mostrarPanel.addActionListener(escucha);

        empezarJuego = new JButton();
        empezarJuego.setBackground(new Color(11,119,158));
        empezarJuego.setText("Iniciar juego");
        //agrega el escucha
        empezarJuego.addActionListener(escucha);

        //pinta los botones en el panel controlador

        panelControlador.add(empezarJuego);
        panelControlador.add(mostrarPanel);

        //Pinta los paneles en la pantalla
        panelPlayer = new JPanel();
        panelPlayer.setLayout(new GridLayout(2,2));

        panelPlayer.add(panelPos);
        panelPlayer.add(panelPrin);
        panelPlayer.add(panelEnemigo);
        panelPlayer.add(panelControlador);

        add(panelPlayer);
        pack();
    }

    /**
     * Ventana que contiene el modo de uso del juego, como jugar y sus caracteristicas
     */
    private void ventanaHTP(){
        panelMain.setVisible(false);
        labelBg.setVisible(false);
        //setResizable(false);
        //setSize(818,840);

        panelHTP = new JPanel();
        panelHTP.setBorder(BorderFactory.createTitledBorder(null, "HOW TO PLAY", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Berlin Sans FB", Font.PLAIN,40), new Color(255,219,0)));
        //Configuracion para el textoHTPlay
        textoHTP = new JTextArea(5, 5);
        textoHTP.setText("The game consists of defeating all 10 enemy ships before the enemy defeats your 10 ships.\n" +
                "To start, you must organize the ships on your position board, once the ships are organized, the game will begin.\n" +
                "The enemy ships and yours, cover X number of squares, to be able to shoot the enemy ship, you choose a square on the main board,\n" +
                "if a ship or part of a ship is in that square, it will be marked with a bomb,\n" +
                "if you hit the shot you can shoot again until you miss, when you miss, it will be marked with an X.\n" +
                "When you defeat a ship a Skull will appear which means you defeated an enemy ship.\n" +
                "The game will continue until one of the two players runs out of fleet ships to play.\n" +
                "Are you ready? See you in Sea Battle");
        textoHTP.setBackground(null);
        textoHTP.setFont(new Font("Berlin Sans FB", Font.PLAIN,20));
        this.add(panelHTP);
        //Agregar el texto al panel
        panelHTP.add(textoHTP);
        panelHTP.setBackground(new Color(11,119,158));
        pack();
    }

    /**
     * Se encarga de realizar los tiros del enemigo
     * @param panel
     * @param celdas
     */
    public void disparosEnemigo(JPanel panel, Celda[][] celdas){
        Random random = new Random();
        int indice1 = random.nextInt(10)+1;
        int indice2 = random.nextInt(10)+1;
        hundido = new ImageIcon(getClass().getResource(PATH+"hurt.png"));
        if(celdas[indice1][indice2].isAreaUtilizada()){
            celdas[indice1][indice2].setIcon(hundido);
            panel.updateUI();
            aciertosEnemigo++;
        }
        if(!celdas[indice1][indice2].isAreaUtilizada()){
            celdas[indice1][indice2].setIcon(agua);
            panel.updateUI();
        }

    }

    /**
     * Se encarga de pintar los barcos del enemigo
     * @param panel
     * @param matrizCeldas
     */
    public void movimientosEnemigo(JPanel panel, Celda[][] matrizCeldas){
        Random random = new Random();

        int espacio = 0, primeraVez =1;


        if (flagEnemigo == 1) {
            //Crea aleatoriamente el indice de los barcos
            int indice1 = random.nextInt(10)+1;
            int indice2 = random.nextInt(10)+1;
            //indica aleatoriamente si el barco va a estar en posición vertical u horizontal
            //1 = posición vertical, 2 = posición horizontal
            int indicaPosicion = random.nextInt(2)+1;
            if (indicaPosicion == 1) {
                if (matrizCeldas[indice1][indice2].getFilas() >= 1 && matrizCeldas[indice1][indice2].getFilas() <= 3 && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                        && !matrizCeldas[indice1 + 1][indice2].isAreaUtilizada() && !matrizCeldas[indice1 + 2][indice2].isAreaUtilizada() && !matrizCeldas[indice1 + 3][indice2].isAreaUtilizada()) {
                    matrizCeldas[indice1][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1 + 1][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1 + 1][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1 + 2][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1 + 2][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1 + 3][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1 + 3][indice2].setAreaUtilizada(true);
                    flagEnemigo++;
                    panel.updateUI();
                }
                if (matrizCeldas[indice1][indice2].getFilas() > 3 && matrizCeldas[indice1][indice2].getFilas() <= 10  && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                        && !matrizCeldas[indice1 - 1][indice2].isAreaUtilizada() && !matrizCeldas[indice1 - 2][indice2].isAreaUtilizada() && !matrizCeldas[indice1 - 3][indice2].isAreaUtilizada()) {
                    matrizCeldas[indice1][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1 - 1][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1 - 1][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1 - 2][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1 - 2][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1 - 3][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1 - 3][indice2].setAreaUtilizada(true);
                    flagEnemigo++;
                    panel.updateUI();
                }
            } else if (indicaPosicion == 2) {
                if (matrizCeldas[indice1][indice2].getColumnas() >= 1 && matrizCeldas[indice1][indice2].getColumnas() < 8  && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                        && !matrizCeldas[indice1][indice2 + 1].isAreaUtilizada() && !matrizCeldas[indice1][indice2 + 2].isAreaUtilizada() && !matrizCeldas[indice1][indice2 + 3].isAreaUtilizada()) {
                    matrizCeldas[indice1][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1][indice2 + 1].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2 + 1].setAreaUtilizada(true);
                    matrizCeldas[indice1][indice2 + 2].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2 + 2].setAreaUtilizada(true);
                    matrizCeldas[indice1][indice2 + 3].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2 + 3].setAreaUtilizada(true);
                    flagEnemigo++;
                    panel.updateUI();
                }
                if (matrizCeldas[indice1][indice2].getColumnas() >= 8 && matrizCeldas[indice1][indice2].getColumnas() <= 10  && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                        && !matrizCeldas[indice1][indice2 - 1].isAreaUtilizada() && !matrizCeldas[indice1][indice2 - 2].isAreaUtilizada() && !matrizCeldas[indice1][indice2 - 3].isAreaUtilizada()) {
                    matrizCeldas[indice1][indice2].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                    matrizCeldas[indice1][indice2 - 1].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2 - 1].setAreaUtilizada(true);
                    matrizCeldas[indice1][indice2 - 2].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2 - 2].setAreaUtilizada(true);
                    matrizCeldas[indice1][indice2 - 3].setBackground(Color.BLUE);
                    matrizCeldas[indice1][indice2 - 3].setAreaUtilizada(true);
                    flagEnemigo++;
                    panel.updateUI();
                }
            }

        }
        if (flagEnemigo == 2) {

            while(espacio < 2) {
                //Crea aleatoriamente el indice de los barcos
                int indice1 = random.nextInt(10)+1;
                int indice2 = random.nextInt(10)+1;
                while(matrizCeldas[indice1][indice2].isAreaUtilizada()){
                    indice1 = random.nextInt(10)+1;
                    indice2 = random.nextInt(10)+1;
                }
                //indica aleatoriamente si el barco va a estar en posición vertical u horizontal
                //1 = posición vertical, 2 = posición horizontal
                int indicaPosicion = random.nextInt(2)+1;
                if (indicaPosicion == 1) {
                    if (matrizCeldas[indice1][indice2].getFilas() >= 1 && matrizCeldas[indice1][indice2].getFilas() <= 3 && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1 + 1][indice2].isAreaUtilizada() && !matrizCeldas[indice1 + 2][indice2].isAreaUtilizada() ) {
                        matrizCeldas[indice1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 + 1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1 + 1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 + 2][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1 + 2][indice2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getFilas() > 3 && matrizCeldas[indice1][indice2].getFilas() <= 10  && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1 - 1][indice2].isAreaUtilizada() && !matrizCeldas[indice1 - 2][indice2].isAreaUtilizada() ) {
                        matrizCeldas[indice1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 - 1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1 - 1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 - 2][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1 - 2][indice2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                } else if (indicaPosicion == 2) {
                    if (matrizCeldas[indice1][indice2].getColumnas() >= 1 && matrizCeldas[indice1][indice2].getColumnas() < 8  && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1][indice2 + 1].isAreaUtilizada() && !matrizCeldas[indice1][indice2 + 1].isAreaUtilizada() ) {
                        matrizCeldas[indice1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 + 1].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2 + 1].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 + 2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2 + 2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getColumnas() >= 8 && matrizCeldas[indice1][indice2].getColumnas() <= 10  && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1][indice2 - 1].isAreaUtilizada() && !matrizCeldas[indice1][indice2 - 2].isAreaUtilizada() ) {
                        matrizCeldas[indice1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 - 1].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2 - 1].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 - 2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2 - 2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                }
            }
            if(espacio == 2) {
                flagEnemigo++;
                espacio = 0;
            }
        }
        if (flagEnemigo == 3) {
            //Crea aleatoriamente el indice de los barcos
            while(espacio < 3) {
                //Crea aleatoriamente el indice de los barcos
                int indice1 = random.nextInt(10)+1;
                int indice2 = random.nextInt(10)+1;
                while(matrizCeldas[indice1][indice2].isAreaUtilizada()){
                    indice1 = random.nextInt(10)+1;
                    indice2 = random.nextInt(10)+1;
                }
                //indica aleatoriamente si el barco va a estar en posición vertical u horizontal
                //1 = posición vertical, 2 = posición horizontal
                int indicaPosicion = random.nextInt(2)+1;

                if (indicaPosicion == 1) {
                    if (matrizCeldas[indice1][indice2].getFilas() >= 1 && matrizCeldas[indice1][indice2].getFilas() <= 3 && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1 + 1][indice2].isAreaUtilizada()) {
                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 + 1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1 + 1][indice2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getFilas() > 3 && matrizCeldas[indice1][indice2].getFilas() <= 10 && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1 - 1][indice2].isAreaUtilizada()) {
                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 - 1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1 - 1][indice2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                } else if (indicaPosicion == 2) {
                    if (matrizCeldas[indice1][indice2].getColumnas() >= 1 && matrizCeldas[indice1][indice2].getColumnas() < 8 && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1][indice2 + 1].isAreaUtilizada()) {

                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 + 1].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2 + 1].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getColumnas() >= 8 && matrizCeldas[indice1][indice2].getColumnas() <= 10 && !matrizCeldas[indice1][indice2].isAreaUtilizada()
                            && !matrizCeldas[indice1 - 1][indice2].isAreaUtilizada()) {
                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 - 1].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2 - 1].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                }
            }
            if(espacio == 3) {
                flagEnemigo++;
                espacio = 0;
            }
        }
        if (flagEnemigo == 4) {
            //Crea aleatoriamente el indice de los barcos
            while(espacio < 4) {
                //Crea aleatoriamente el indice de los barcos
                int indice1 = random.nextInt(10)+1;
                int indice2 = random.nextInt(10)+1;
                while(matrizCeldas[indice1][indice2].isAreaUtilizada()){
                    indice1 = random.nextInt(10)+1;
                    indice2 = random.nextInt(10)+1;
                }
                if(!matrizCeldas[indice1][indice2].isAreaUtilizada()) {
                    matrizCeldas[indice1][indice2].setBackground(Color.YELLOW);
                    matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                    espacio++;
                    panel.updateUI();
                }
            }

            if(espacio == 4) {
                espacio = 4;
                flagEnemigo ++;
            }
        }
    }


    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        //Set up JComponents
        //headerProject = new Header("Header ...", Color.BLACK);
        //this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        //panelBotones
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2,1));
        panelBotones.setOpaque(false);

        //botonPlay
        imagen = new ImageIcon(getClass().getResource(PATH+"play.png"));
        botonPlay = new JButton(imagen);
        botonPlay.setBorder(null);
        botonPlay.setContentAreaFilled(false);
        botonPlay.addActionListener(escucha);

        //botonHTP
        imagen = new ImageIcon(getClass().getResource(PATH+"how-to-play.png"));
        botonHTP = new JButton(imagen);
        botonHTP.setBorder(null);
        botonHTP.setContentAreaFilled(false);
        botonHTP.addActionListener(escucha);

        panelBotones.add(botonPlay);
        panelBotones.add(botonHTP);

        //panelLogo
        labelLogo = new JLabel();
        logo = new ImageIcon(getClass().getResource(PATH+"logo.png"));
        Image resizedLogo = logo.getImage().getScaledInstance(582,267, Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        labelLogo.setIcon(logo);
        panelLogo = new JPanel();
        panelLogo.add(labelLogo);
        panelLogo.setOpaque(false);

        //Panel donde iran los botones y el logo
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(3,1));
        panelMain.add(panelLogo);
        panelMain.add(panelBotones);
        panelMain.setOpaque(false);
        add(panelMain,BorderLayout.CENTER);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI_BatallaNaval miProjectGUI = new GUI_BatallaNaval();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        private int flag = 1, primeraVez = 0, espacio;
        private boolean juegoIniciado, gano, ganoEnemigo;

        @Override
        public void actionPerformed(ActionEvent objectEvent) {
            if (objectEvent.getSource() == botonPlay) {
                ventanaPlay();
            }
            if (objectEvent.getSource() == botonHTP) {
                ventanaHTP();
            }
            if(objectEvent.getSource() == empezarJuego){
                JOptionPane.showMessageDialog(null, "¡Vamos a iniciar a pintar los barcos!");
                flag = 1;
                juegoIniciado = true;
                empezarJuego.setEnabled(false);
            }
            if (objectEvent.getSource() == mostrarPanel){
                for(int i=0; i < 11; i++){
                    for(int indice = 0; indice < 11; indice++){
                        matrizCeldasEnemigas[i][indice].setVisible(true);
                    }
                }
            }else{
                for(int i=0; i < 11; i++){
                    for(int indice = 0; indice < 11; indice++){
                        if(objectEvent.getSource() == matrizCeldasPosicion[i][indice]) {
                            if(juegoIniciado == true) {
                                if (flag == 1) {
                                    int opcionUsuario = JOptionPane.showConfirmDialog(panelPos, "Si desea que el barco sea vertical presione: (SI) de lo contrario se pondrá en horizontal",
                                            "Ventana electiva", JOptionPane.YES_NO_OPTION);
                                    if (opcionUsuario == JOptionPane.YES_OPTION) {
                                        if (matrizCeldasPosicion[i][indice].getFilas() == 1 || matrizCeldasPosicion[i][indice].getFilas() == 2 || matrizCeldasPosicion[i][indice].getFilas() == 3) {
                                            matrizCeldasPosicion[i][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i + 1][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i + 1][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i + 2][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i + 2][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i + 3][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i + 3][indice].setAreaUtilizada(true);
                                            flag++;
                                            panelPos.updateUI();
                                        }
                                        if (matrizCeldasPosicion[i][indice].getFilas() > 3 && matrizCeldasPosicion[i][indice].getFilas() <= 10) {
                                            matrizCeldasPosicion[i][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i - 1][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i - 1][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i - 2][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i - 2][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i - 3][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i - 3][indice].setAreaUtilizada(true);
                                            flag++;
                                            panelPos.updateUI();
                                        }
                                    } else if (opcionUsuario == JOptionPane.NO_OPTION) {
                                        if (matrizCeldasPosicion[i][indice].getColumnas() >= 1 && matrizCeldasPosicion[i][indice].getColumnas() < 8) {
                                            matrizCeldasPosicion[i][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i][indice + 1].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice + 1].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i][indice + 2].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice + 2].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i][indice + 3].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice + 3].setAreaUtilizada(true);
                                            flag++;
                                            panelPos.updateUI();
                                        }
                                        if (matrizCeldasPosicion[i][indice].getColumnas() >= 8 && matrizCeldasPosicion[i][indice].getColumnas() <= 10) {
                                            matrizCeldasPosicion[i][indice].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i][indice - 1].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice - 1].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i][indice - 2].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice - 2].setAreaUtilizada(true);
                                            matrizCeldasPosicion[i][indice - 3].setBackground(Color.BLUE);
                                            matrizCeldasPosicion[i][indice - 3].setAreaUtilizada(true);
                                            flag++;
                                            panelPos.updateUI();
                                        }
                                    }

                                }
                                if (flag == 2) {
                                    if(primeraVez >= 1) {
                                        int opcionUsuario = JOptionPane.showConfirmDialog(panelPos, "Si desea que el barco sea vertical presione: (SI) de lo contrario se pondrá en horizontal",
                                                "Ventana electiva", JOptionPane.YES_NO_OPTION);
                                        if (opcionUsuario == JOptionPane.YES_OPTION) {
                                            if (matrizCeldasPosicion[i][indice].getFilas() == 1 || matrizCeldasPosicion[i][indice].getFilas() == 2 || matrizCeldasPosicion[i][indice].getFilas() == 3) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i + 1][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i + 1][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i + 2][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i + 2][indice].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                            if (matrizCeldasPosicion[i][indice].getFilas() > 3 && matrizCeldasPosicion[i][indice].getFilas() <= 10) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i - 1][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i - 1][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i - 2][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i - 2][indice].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                        } else if (opcionUsuario == JOptionPane.NO_OPTION) {
                                            if (matrizCeldasPosicion[i][indice].getColumnas() >= 1 && matrizCeldasPosicion[i][indice].getColumnas() < 8) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice + 1].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice + 1].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice + 2].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice + 2].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                            if (matrizCeldasPosicion[i][indice].getColumnas() >= 8 && matrizCeldasPosicion[i][indice].getColumnas()<= 10) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice - 1].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice - 1].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice - 2].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice - 2].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                        }
                                    }
                                    primeraVez ++;
                                    if(espacio == 2) {
                                        flag++;
                                        espacio = 0;
                                        primeraVez = 0;
                                    }
                                }
                                if (flag == 3) {
                                    if(primeraVez >= 1) {
                                        int opcionUsuario = JOptionPane.showConfirmDialog(panelPos, "Si desea que el barco sea vertical presione: (SI) de lo contrario se pondrá en horizontal",
                                                "Ventana electiva", JOptionPane.YES_NO_OPTION);
                                        if (opcionUsuario == JOptionPane.YES_OPTION) {
                                            if (matrizCeldasPosicion[i][indice].getFilas() == 1 || matrizCeldasPosicion[i][indice].getFilas() == 2 || matrizCeldasPosicion[i][indice].getFilas() == 3) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i + 1][indice].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i + 1][indice].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                            if (matrizCeldasPosicion[i][indice].getFilas() > 3 && matrizCeldasPosicion[i][indice].getFilas() <= 10) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i - 1][indice].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i - 1][indice].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                        } else if (opcionUsuario == JOptionPane.NO_OPTION) {
                                            if (matrizCeldasPosicion[i][indice].getColumnas() >= 1 && matrizCeldasPosicion[i][indice].getColumnas() < 8) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice + 1].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice + 1].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                            if (matrizCeldasPosicion[i][indice].getColumnas() >= 8 && matrizCeldasPosicion[i][indice].getColumnas() <= 10) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice - 1].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice - 1].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                        }
                                    }
                                    primeraVez ++;
                                    if(espacio == 3) {
                                        flag++;
                                        espacio = 0;
                                        primeraVez = 0;
                                    }
                                }
                                if (flag == 4) {
                                    if(primeraVez >= 1) {
                                        matrizCeldasPosicion[i][indice].setBackground(Color.YELLOW);
                                        matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                        espacio++;
                                        panelPos.updateUI();
                                     }

                                    primeraVez ++;
                                    if(espacio == 4) {
                                        flag++;
                                        espacio = 0;
                                        primeraVez = 0;
                                    }
                                }

                            }
                        }
                    }
                }

                if(flag == 5){
                    movimientosEnemigo(panelEnemigo,matrizCeldasEnemigas);
                    if(aciertosEnemigo == 20){
                        ganoEnemigo = true;
                    }
                    if(aciertos == 20){
                        gano = true;
                    }

                    if(!gano){
                        for(int i=0; i < 11; i++) {
                            for (int indice = 0; indice < 11; indice++) {
                                if(objectEvent.getSource() == matrizCeldasprincipales[i][indice]){
                                    if(matrizCeldasEnemigas[i][indice].isAreaUtilizada()){
                                        matrizCeldasprincipales[i][indice].setIcon(tocado);
                                        aciertos++;
                                        System.out.println(aciertos);
                                    }
                                    if(!matrizCeldasEnemigas[i][indice].isAreaUtilizada()){
                                        matrizCeldasprincipales[i][indice].setIcon(agua);
                                    }
                                }
                            }
                        }
                        if(primeraVez >= 1){
                            disparosEnemigo(panelPos, matrizCeldasPosicion);
                        }
                        primeraVez ++;
                    }else{
                        for(int i=0; i < 11; i++) {
                            for (int indice = 0; indice < 11; indice++) {
                                if(matrizCeldasEnemigas[i][indice].isAreaUtilizada()){
                                    hundido = new ImageIcon(getClass().getResource(PATH+"defeated.png"));
                                    matrizCeldasprincipales[i][indice].setIcon(hundido);
                                    panelPrin.updateUI();

                                }
                            }

                        }
                        JOptionPane.showMessageDialog(null,"¡GANASTE!");
                    }
                    if(ganoEnemigo){
                        for(int i=0; i < 11; i++) {
                            for (int indice = 0; indice < 11; indice++) {
                                if(matrizCeldasPosicion[i][indice].isAreaUtilizada()){
                                    hundido = new ImageIcon(getClass().getResource(PATH+"defeated.png"));
                                    matrizCeldasPosicion[i][indice].setIcon(hundido);
                                    panelPos.updateUI();

                                }
                            }

                        }
                        JOptionPane.showMessageDialog(null,"¡PERDISTE!");
                    }

                }


            }
        }
    }
}
