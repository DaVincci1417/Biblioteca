package Model.Data.DAO;

import Model.Biblioteca;
import Model.Libro;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class BibliotecaDAO{
    public static void agregarBiblioteca(DSLContext query, Biblioteca biblioteca){
        Table tablaBiblioteca = table(name("Biblioteca"));
        Field[] columnas = tablaBiblioteca.fields("nombre","direccion","horario_atencion");
        query.insertInto(tablaBiblioteca, columnas[0], columnas[1], columnas[2])
                .values(biblioteca.getNombre(), biblioteca.getDireccion(), biblioteca.getHorarioAtencion())
                .execute();
    }
}
