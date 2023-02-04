/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mipromedio;

import java.io.*;
import java.util.*;

/**
 *
 * @author pc
 */
public class Gestionamiento {
    ArrayList<Materia> materias = new ArrayList<>();
    public File archivo;

    public Gestionamiento() {
        //materias.add(new Materia("calculo", 4.0, 3));
        materias = leerMaterias();
        //materias.add(new Materia("calculo", 4.0, 3));
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materia) {
        this.materias = materia;
    }
    
    public void agregarMateria(Materia m){
        materias.add(m);
    }

    public void eliminarMateria(Materia m){
        materias.remove(m);
    }

    public void modificarMateria(Materia m){
        for (int i = 0; i < materias.size(); i++) {
            if(materias.get(i).getNombre().equals(m.getNombre())){
                materias.get(i).setNota(m.getNota());
                materias.get(i).setCreditos(m.getCreditos());
            }
        }
    }

    public float promedio(){
        float promedio = 0;
        int creditos = 0;
        for (int i = 0; i < materias.size(); i++) {
            promedio += materias.get(i).getNota() * materias.get(i).getCreditos();
            creditos += materias.get(i).getCreditos();
        }
        promedio/=creditos;
        return (float) (Math.round(promedio * 100.0) / 100.0);
    }

    public int totalMaterias(){
        return materias.size();
    }

    public int totalCreditos(){
        int creditos = 0;
        for (int i = 0; i < materias.size(); i++) {
            creditos += materias.get(i).getCreditos();
        }
        return creditos;
    }

    public boolean existeMateria(String nombre){
        for (int i = 0; i < materias.size(); i++) {
            if(materias.get(i).getNombre().equalsIgnoreCase(nombre)){
                return true;
            }
        }
        return false;
    }

    public void guardarMaterias(ArrayList<Materia> materias) {
        try (FileWriter fw = new FileWriter("src/mipromedio/DatoMaterias.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            
    
            for (Materia materia : materias) {
                pw.println(materia.getNombre() + "," + materia.getNota()+ "," +materia.getCreditos());
            }
    
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /*public static ArrayList<Materia> leerMaterias() {
        ArrayList<Materia> materias = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("src/mipromedio/DatoMaterias.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] datos = linea.split(",");
                materias.add(new Materia(datos[0], Double.parseDouble(datos[1]), Integer.parseInt(datos[2])));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return materias;
    }*/
    
    public static ArrayList<Materia> leerMaterias() {
        ArrayList<Materia> materias = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("src/mipromedio/DatoMaterias.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    materias.add(new Materia(datos[0], Float.parseFloat(datos[1]), Integer.parseInt(datos[2])));
                } else {
                    System.out.println("La línea " + linea + " no tiene los datos completos y se ignorará.");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return materias;
    }

}
