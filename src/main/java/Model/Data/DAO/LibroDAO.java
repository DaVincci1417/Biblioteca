package Model.Data.DAO;

import Model.Libro;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class LibroDAO {
    public static void agregarLibro(DSLContext query, Libro libro){
        Table tablaLibro = table(name("Libro"));
        Field[] columnas = tablaLibro.fields("titulo","autor","fecha_publicacion","genero","disponibilidad");
        query.insertInto(tablaLibro, columnas[0], columnas[1], columnas[2], columnas[3], columnas[4])
                .values(libro.getTitulo(), libro.getAutor(), libro.getFechaPublicacion(), libro.getGenero(), libro.getDisponibilidad())
                .execute();
    }
    public static void eliminarLibro(DSLContext query, String nombre){
        Table tablaLibro = table(name("Libro"));
        query.delete(DSL.table("Libro")).where(DSL.field("titulo").eq(nombre)).execute();
    }
    public static List obtenerLibroPorAutor(DSLContext query, String dato){
        Result resultados = query.select().from(DSL.table("Libro")).where(DSL.field("autor").eq(dato)).fetch();
        return obtenerListaLibros(resultados);
    }
    public static List definirExistenciaLibro(DSLContext query, String dato){
        Result resultados = query.select().from(DSL.table("Libro")).where(DSL.field("titulo").eq(dato)).fetch();
        return obtenerListaLibros(resultados);
    }
    public static void modificarDisponibilidadLibro(DSLContext query, String nombre, Object dato){
        query.update(DSL.table("Libro")).set(DSL.field("disponibilidad"),dato).
                where(DSL.field("titulo").eq(nombre)).execute();
    }
    private static List obtenerListaLibros(Result resultados){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        for(int fila=0; fila < resultados.size() ; fila++){
            String titulo = (String) resultados.getValue(fila,"titulo");
            String autor = (String) resultados.getValue(fila,"autor");
            String fechaPublicacion = (String) resultados.getValue(fila,"fecha_publicacion");
            String genero = (String) resultados.getValue(fila,"genero");
            String disponibilidad = (String) resultados.getValue(fila, "disponibilidad");

            Libro libro = new Libro(titulo, autor, fechaPublicacion, genero);
            libro.modificarDisponibilidad(disponibilidad);
            libros.add(libro);

        }
        return libros;
    }
}
