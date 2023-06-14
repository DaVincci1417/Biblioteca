package Controller;

import Model.Biblioteca;
import Model.Data.DBGenerator;
import Model.Libro;

public class Launcher {
    public static void inicializar() throws ClassNotFoundException {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal","Calle Avelleneda, Temuco", "08:00 a 16:00");
        Libro libro = new Libro("El señor de la noche", "Don Omar","01/06/2006","Poesia");
        biblioteca.agregarLibro(libro);
        biblioteca.buscarLibroPorAutor("El señor de la noche","Don Omar");
        biblioteca.prestarLibro("El señor de la noche");
        biblioteca.devolverLibro("El señor de la noche");
        biblioteca.eliminarLibro("El señor de la noche");

    }
    public static void main(String[]args) throws ClassNotFoundException {
        inicializar();
    }
}
