package myProject;

import javax.swing.*;

public class Celda extends JButton {
    private int columnas, filas;
    private boolean areaUtilizada;
    private String nombreBote;

    public Celda(int filas, int columnas, boolean areaUtilizada){
        this.columnas = columnas;
        this.filas = filas;
        this.areaUtilizada = areaUtilizada;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public boolean isAreaUtilizada() {
        return areaUtilizada;
    }

    public void setAreaUtilizada(boolean areaUtilizada) {
        this.areaUtilizada = areaUtilizada;
    }

}
