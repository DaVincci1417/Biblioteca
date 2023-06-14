package Model;

public class Libro {
    private String titulo;
    private String autor;
    private String fechaPublicacion;
    private String genero;
    private String disponibilidad;

    public Libro(String titulo, String autor, String fechaPublicacion, String genero) {
        setTitulo(titulo);
        setAutor(autor);
        setFechaPublicacion(fechaPublicacion);
        setGenero(genero);
        setDisponibilidad("no");
    }
    public Libro(){}

    public String getTitulo() {
        return titulo;
    }
    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    private void setAutor(String autor) {
        this.autor = autor;
    }
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }
    private void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public String getGenero() {
        return genero;
    }
    private void setGenero(String genero) {
        this.genero = genero;
    }
    public String getDisponibilidad() {
        return disponibilidad;
    }
    private void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public void modificarDisponibilidad(String disponibilidad){
        setDisponibilidad(disponibilidad);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", fechaPublicacion='" + fechaPublicacion + '\'' +
                ", genero='" + genero + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                '}';
    }
}

