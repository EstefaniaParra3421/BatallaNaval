package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI_BatallaNaval extends JFrame {
    public static final String PATH ="/resources/";

    //Botones
    private JButton botonPVP, botonPVC;
    private Header headerProject;
    private JPanel panelTablero, panelBotones, panelLogo;
    private ImageIcon tablero, bg, logo, imagen;
    private JLabel labelTablero, labelBg, labelLogo;
    private Escucha escucha;

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

        //bg
        bg = new ImageIcon(getClass().getResource(PATH+"bg-2.jpg"));
        labelBg = new JLabel("",bg,JLabel.CENTER);
        labelBg.setBounds(0,0,1920,1080);
        add(labelBg);
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

        //bg
        labelBg = new JLabel();
        bg = new ImageIcon(getClass().getResource(PATH+"bg.jpg"));
        labelBg.setIcon(bg);
        //this.add(labelBg);

        //tablero
        labelTablero = new JLabel();
        tablero = new ImageIcon(getClass().getResource(PATH+"table-visual.jpg"));
        labelTablero.setIcon(tablero);
        //this.add(labelTablero);

        //panelBotones
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2,1));

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

        //panelLogo
        labelLogo = new JLabel();
        logo = new ImageIcon(getClass().getResource(PATH+"logo.png"));
        Image resizedLogo = logo.getImage().getScaledInstance(582,267, Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        labelLogo.setIcon(logo);
        panelLogo = new JPanel();
        panelLogo.add(labelLogo);
        panelLogo.setBackground(null);

        panelBotones.add(botonPVP);
        panelBotones.add(botonPVC);

        add(panelLogo,BorderLayout.CENTER);
        add(panelBotones,BorderLayout.SOUTH);

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
        public void actionPerformed(ActionEvent e) {

        }
    }
}
