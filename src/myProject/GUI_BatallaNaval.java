package myProject;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
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
    private JButton botonPVP, botonPVC, mostrarPanel, empezarJuego;
    private Header headerProject;
    private JPanel panelBotones, panelLogo, panelMain, panelPrin, panelPos, panelPlayer, panelComputer, panelEnemigo, panelControlador;
    private ImageIcon tablero, bg, logo, imagen, mapa, image;
    private JLabel labelTablero, labelBg, labelLogo;
    private Escucha escucha;
    private Celda[][] matrizCeldasprincipales = new Celda[11][11];
    private Celda[][] matrizCeldasPosicion = new Celda[11][11];
    private Celda[][] matrizCeldasEnemigas = new Celda[11][11];

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

    private void añadirNumeros(Celda[][] celda, JPanel panel){
        for(int i=1; i <=10; i++){
            celda[i][0].setBackground(Color.WHITE);
            celda[i][0].setText(i+"");
            celda[0][i].removeActionListener(escucha);
            panel.updateUI();
        }
    }

    private void ventanaPVP(){
        panelMain.setVisible(false);
        labelBg.setVisible(false);
        //setResizable(false);
        mapa = new ImageIcon(getClass().getResource(PATH+"mapa.jpg"));

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
                matrizCeldasprincipales[i][indice].setBackground(Color.CYAN);
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
                matrizCeldasPosicion[i][indice].setBackground(Color.CYAN);
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
                matrizCeldasEnemigas[i][indice].setBackground(Color.CYAN);
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
        mostrarPanel.setBackground(Color.CYAN);
        mostrarPanel.setText("Mostrar panel enemigo");
        //agrega el escucha
        mostrarPanel.addActionListener(escucha);

        empezarJuego = new JButton();
        empezarJuego.setBackground(Color.CYAN);
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

    private void ventanaPVC(){
        panelMain.setVisible(false);
        labelBg.setVisible(false);
        setResizable(false);

        //tablero principal
        headerProject = new Header("TABLERO PRINCIPAL", Color.BLACK, new Font("Berlin Sans FB", Font.BOLD,15));
        labelTablero = new JLabel();
        tablero = new ImageIcon(getClass().getResource(PATH+"table-visual.jpg"));
        labelTablero.setIcon(tablero);

        //Panel principal
        panelPrin = new JPanel();
        panelPrin.setLayout(new BorderLayout());
        panelPrin.add(headerProject, BorderLayout.NORTH);
        panelPrin.add(labelTablero);

        //tablero de posicion
        headerProject = new Header("TABLERO DE POSICION", Color.BLACK, new Font("Berlin Sans FB", Font.BOLD,15));
        labelTablero = new JLabel();
        tablero = new ImageIcon(getClass().getResource(PATH+"table-visual.jpg"));
        labelTablero.setIcon(tablero);

        //Panel de posicion
        panelPos = new JPanel();
        panelPos.setLayout(new BorderLayout());
        panelPos.add(headerProject, BorderLayout.NORTH);
        panelPos.add(labelTablero);

        //tablero del enemigo
        headerProject = new Header("TABLERO ENEMIGO", Color.BLACK, new Font("Berlin Sans FB", Font.BOLD,15));
        labelTablero = new JLabel();
        tablero = new ImageIcon(getClass().getResource(PATH+"table-visual.jpg"));
        labelTablero.setIcon(tablero);

        //Panel de posicion
        panelPos = new JPanel();
        panelPos.setLayout(new BorderLayout());
        panelPos.add(headerProject, BorderLayout.NORTH);
        panelPos.add(labelTablero);

        //Panel de tablero principal y de posicion
        panelComputer = new JPanel();
        panelComputer.setLayout(new GridLayout(1,2));
        panelComputer.add(panelPrin);
        panelComputer.add(panelPos);

        add(panelComputer);
        pack();
    }

    public void movimientosEnemigo(JPanel panel, Celda[][] matrizCeldas){
        Random random = new Random();
        //Crea aleatoriamente el indice de los barcos
        int indice1 = random.nextInt(10)+1;
        int indice2 = random.nextInt(10)+1;
        //indica aleatoriamente si el barco va a estar en posición vertical u horizontal
        //1 = vertical, 2 = horizontal
        int indicaPosicion = random.nextInt(2)+1;
        int flagEnemigo = 1, espacio = 0, primeraVez =1;

        if (flagEnemigo == 1) {

            if (indicaPosicion == 1) {
                if (matrizCeldas[indice1][indice2].getFilas() == 1 || matrizCeldas[indice1][indice2].getFilas() == 2 || matrizCeldas[indice1][indice2].getFilas() == 3) {
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
                if (matrizCeldas[indice1][indice2].getFilas() > 3 && matrizCeldas[indice1][indice2].getFilas() <= 10) {
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
                if (matrizCeldas[indice1][indice2].getFilas() >= 1 && matrizCeldas[indice1][indice2].getFilas() < 8) {
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
                if (matrizCeldas[indice1][indice2].getFilas() >= 8 && matrizCeldas[indice1][indice2].getFilas() <= 10) {
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
            if(primeraVez >= 1) {
                if (indicaPosicion == 1) {
                    if (matrizCeldas[indice1][indice2].getFilas() == 1 || matrizCeldas[indice1][indice2].getFilas() == 2 || matrizCeldas[indice1][indice2].getFilas() == 3) {
                        matrizCeldas[indice1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 + 1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1 + 1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 + 2][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1 + 2][indice2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getFilas() > 3 && matrizCeldas[indice1][indice2].getFilas() <= 10) {
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
                    if (matrizCeldas[indice1][indice2].getFilas() >= 1 && matrizCeldas[indice1][indice2].getFilas() < 8) {
                        matrizCeldas[indice1][indice2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 + 1].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2 + 1].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 + 2].setBackground(Color.GREEN);
                        matrizCeldas[indice1][indice2 + 2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getFilas() >= 8 && matrizCeldas[indice1][indice2].getFilas() <= 10) {
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
            primeraVez ++;
            if(espacio == 2) {
                flagEnemigo++;
                espacio = 0;
                primeraVez = 0;
            }
        }
        if (flagEnemigo == 3) {
            if(primeraVez >= 1) {

                if (indicaPosicion == 1) {
                    if (matrizCeldas[indice1][indice2].getFilas() == 1 || matrizCeldas[indice1][indice2].getFilas() == 2 || matrizCeldas[indice1][indice2].getFilas() == 3) {
                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 + 1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1 + 1][indice2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getFilas() > 3 && matrizCeldas[indice1][indice2].getFilas() <= 10) {
                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1 - 1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1 - 1][indice2].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                } else if (indicaPosicion == 2) {
                    if (matrizCeldas[indice1][indice2].getFilas() >= 1 && matrizCeldas[indice1][indice2].getFilas() < 8) {
                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 + 1].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2 + 1].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                    if (matrizCeldas[indice1][indice2].getFilas() >= 8 && matrizCeldas[indice1][indice2].getFilas() <= 10) {
                        matrizCeldas[indice1][indice2].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                        matrizCeldas[indice1][indice2 - 1].setBackground(Color.MAGENTA);
                        matrizCeldas[indice1][indice2 - 1].setAreaUtilizada(true);
                        espacio++;
                        panel.updateUI();
                    }
                }
            }
            primeraVez ++;
            if(espacio == 3) {
                flagEnemigo++;
                espacio = 0;
                primeraVez = 0;
            }
        }
        if (flagEnemigo == 4) {
            if(primeraVez >= 1) {
                matrizCeldas[indice1][indice2].setBackground(Color.YELLOW);
                matrizCeldas[indice1][indice2].setAreaUtilizada(true);
                espacio++;
                panel.updateUI();
            }

            primeraVez ++;
            if(espacio == 4) {
                flagEnemigo++;
                espacio = 0;
                primeraVez = 0;
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

        //botonPVP
        imagen = new ImageIcon(getClass().getResource(PATH+"play-pvp.png"));
        botonPVP = new JButton(imagen);
        botonPVP.setBorder(null);
        botonPVP.setContentAreaFilled(false);
        botonPVP.addActionListener(escucha);

        //botonPVC
        imagen = new ImageIcon(getClass().getResource(PATH+"play-pvc.png"));
        botonPVC = new JButton(imagen);
        botonPVC.setBorder(null);
        botonPVC.setContentAreaFilled(false);
        botonPVC.addActionListener(escucha);

        panelBotones.add(botonPVP);
        panelBotones.add(botonPVC);

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

        private int flag, primeraVez = 0, espacio;
        private boolean juegoIniciado;

        @Override
        public void actionPerformed(ActionEvent objectEvent) {
            if (objectEvent.getSource() == botonPVP) {
                ventanaPVP();
            }
            if (objectEvent.getSource() == botonPVC) {
                ventanaPVC();
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
                                        if (matrizCeldasPosicion[i][indice].getFilas() >= 1 && matrizCeldasPosicion[i][indice].getFilas() < 8) {
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
                                        if (matrizCeldasPosicion[i][indice].getFilas() >= 8 && matrizCeldasPosicion[i][indice].getFilas() <= 10) {
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
                                            if (matrizCeldasPosicion[i][indice].getFilas() >= 1 && matrizCeldasPosicion[i][indice].getFilas() < 8) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice + 1].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice + 1].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice + 2].setBackground(Color.GREEN);
                                                matrizCeldasPosicion[i][indice + 2].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                            if (matrizCeldasPosicion[i][indice].getFilas() >= 8 && matrizCeldasPosicion[i][indice].getFilas() <= 10) {
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
                                            if (matrizCeldasPosicion[i][indice].getFilas() >= 1 && matrizCeldasPosicion[i][indice].getFilas() < 8) {
                                                matrizCeldasPosicion[i][indice].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice].setAreaUtilizada(true);
                                                matrizCeldasPosicion[i][indice + 1].setBackground(Color.MAGENTA);
                                                matrizCeldasPosicion[i][indice + 1].setAreaUtilizada(true);
                                                espacio++;
                                                panelPos.updateUI();
                                            }
                                            if (matrizCeldasPosicion[i][indice].getFilas() >= 8 && matrizCeldasPosicion[i][indice].getFilas() <= 10) {
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


            }
        }
    }
}
