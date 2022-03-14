package myProject;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI_BatallaNaval extends JFrame {
    public static final String PATH ="/resources/";

    //Botones del juego
    private JButton botonPVP, botonPVC;
    private JButton matriz[][] = new JButton[11][11];
    private Header headerProject;
    private JPanel panelBotones, panelLogo, panelMain, panelPrin, panelPos, panelPlayer, panelComputer;
    private ImageIcon tablero, bg, logo, imagen, mapa, image;
    private JLabel labelTablero, labelBg, labelLogo;
    private Escucha escucha;
    private Celda[][] matrizCeldas = new Celda[11][11];

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
                matrizCeldas[i][indice] = new Celda(i,indice,false);
                matrizCeldas[i][indice].setBackground(Color.CYAN);
                matrizCeldas[i][indice].setPreferredSize(new Dimension(48,27));
                //pinta la matriz
                panelPrin.add(matrizCeldas[i][indice]);
            }
        }

        añadirLetras(matrizCeldas, panelPrin);
        añadirNumeros(matrizCeldas, panelPrin);

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
                matrizCeldas[i][indice] = new Celda(i,indice,false);
                matrizCeldas[i][indice].setBackground(Color.CYAN);
                matrizCeldas[i][indice].setPreferredSize(new Dimension(48,27));
                //pinta la matriz
                panelPos.add(matrizCeldas[i][indice]);
            }
        }

        añadirLetras(matrizCeldas, panelPos);
        añadirNumeros(matrizCeldas, panelPos);

        //Panel de tablero principal y de posicion
        panelPlayer = new JPanel();
        panelPlayer.setLayout(new GridLayout(1,2));
        panelPlayer.add(panelPrin);
        panelPlayer.add(panelPos);

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

        //Panel de tablero principal y de posicion
        panelComputer = new JPanel();
        panelComputer.setLayout(new GridLayout(1,2));
        panelComputer.add(panelPrin);
        panelComputer.add(panelPos);

        add(panelComputer);
        pack();
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

        @Override
        public void actionPerformed(ActionEvent objectEvent) {
            if (objectEvent.getSource() == botonPVP) {
                ventanaPVP();
            }
            if (objectEvent.getSource() == botonPVC) {
                ventanaPVC();
            }
        }
    }
}
