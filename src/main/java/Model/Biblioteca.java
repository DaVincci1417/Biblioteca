package Model;

import Model.Data.DAO.LibroDAO;
import Model.Data.DBConnector;
import Model.Data.DBGenerator;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private String direccion;
    private String horarioAtencion;

    public Biblioteca(String nombre, String direccion, String horarioAtencion) {
        setNombre(nombre);
        setDireccion(direccion);
        setHorarioAtencion(horarioAtencion);
    }

    public String getNombre() {
        return nombre;
    }
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    private void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getHorarioAtencion() {
        return horarioAtencion;
    }
    private void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    //Metodos que conectan a la base de datos
    public String agregarLibro(Libro libro) throws ClassNotFoundException {
        DBGenerator.iniciarBD("Biblioteca");
        DSLContext query = DBGenerator.conectarBD("Biblioteca");
        List<Libro> libros = LibroDAO.definirExistenciaLibro(query, libro.getTitulo());
        if(libros.size() !=0 ){
            return  "Ya existe el libro";
        }
        else{
            libro.modificarDisponibilidad("si");
            LibroDAO.agregarLibro(query,libro);
            return  "El libro se ha agregado con exito";
        }
    }
    public String eliminarLibro(String tituloLibro) throws ClassNotFoundException {
        DBGenerator.iniciarBD("Biblioteca");
        DSLContext query = DBGenerator.conectarBD("Biblioteca");
        List<Libro> libros = LibroDAO.definirExistenciaLibro(query, tituloLibro);
        if(libros.size() == 0){
            return  "Ha ocurrido un error, no tenemos ese libro";
        }
        else{
            LibroDAO.eliminarLibro(query,tituloLibro);
            return  "El libro se ha eliminado con exito";
        }
    }
    public String buscarLibroPorAutor(String tituloLibro, String autorLibro)throws ClassNotFoundException {
        DBGenerator.iniciarBD("Biblioteca");
        DSLContext query = DBGenerator.conectarBD("Biblioteca");
        List<Libro> libros = LibroDAO.obtenerLibroPorAutor(query, autorLibro);
        List<Libro> libroEncontrado = new ArrayList<>();
        if(libros.size() == 0){
            return  "El autor no tiene libros en esta biblioteca";
        }
        else{
            for(int i = 0; i < libros.size(); i++){
                if(libros.get(i).getTitulo().equalsIgnoreCase(tituloLibro)){
                    libroEncontrado.add(libros.get(i));
                }
            }
            if(libroEncontrado.size() == 0){
                return "El autor no tiene una obra llamada " + tituloLibro;
            } else {
                return libroEncontrado.get(0).toString();
            }
        }
    }
    public String prestarLibro(String tituloLibro)throws ClassNotFoundException {
        DBGenerator.iniciarBD("Biblioteca");
        DSLContext query = DBGenerator.conectarBD("Biblioteca");
        List<Libro> libros = LibroDAO.definirExistenciaLibro(query, tituloLibro);
        if(libros.size() == 0){
            return  "Ha ocurrido un error, no tenemos ese libro";
        }
        else{
            if(libros.get(0).getDisponibilidad().equalsIgnoreCase("no")){
                return "El libro no esta disponible";
            }else {
                LibroDAO.modificarDisponibilidadLibro(query,tituloLibro,"no");
                return "Se ha prestado con exito";
            }
        }
    }
    public String devolverLibro(String tituloLibro)throws ClassNotFoundException {
        DBGenerator.iniciarBD("Biblioteca");
        DSLContext query = DBGenerator.conectarBD("Biblioteca");
        List<Libro> libros = LibroDAO.definirExistenciaLibro(query, tituloLibro);
        if(libros.size() == 0){
            return  "Ha ocurrido un error, no tenemos ese libro";
        }
        else{
            if(libros.get(0).getDisponibilidad().equalsIgnoreCase("si")){
                return "Ha ocurrido un error, no hemos prestado el libro.";
            }else {
                LibroDAO.modificarDisponibilidadLibro(query,tituloLibro,"no");
                return "Se ha devuelto con exito";
            }
        }
    }
}
