import java.util.Comparator;

public class Cliente {
    /*ATRIBUTOS*/
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;

    /*CONSTRUCTOR*/
    public Cliente(int id, String nombre, String telefono, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.matricula = matricula.toUpperCase();
    }

    /*MÉTODOS*/
    //Getters y Setters para todos los atributos
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getMatricula() {
        return matricula;
    }

    //Comparar dos matrículas (No se pueden repetir)
    /*
    public int 'compare' (Cliente c1, Cliente c2) {
        return c1.getMatricula().compareToIgnoreCase(c2.getMatricula());
    }
    */

    //Comparar dos id en caso de que se repita algún nombre
    /*
    public int 'compare'(Cliente c1, Cliente c2) {

        return c1.getId() - c2.getId();
    }
    */

    //Como se mostrará en el fichero correspondiente
    public String toCSV() {
        return "[ID: "+id+" || Nombre: "+getNombre()+" || Teléfono: "+getTelefono()+" || Matrícula: "+getMatricula()+"]\t";
    }

    //Reconstruir el objeto desde CSV
    public static Cliente fromCSV (String linea) {
        String[] partes = linea.split(";", -1);
        return new Cliente ( Integer.parseInt(partes[0]), partes[1], partes[2], partes[3]);
    }

    //Devuelve por pantalla la información del cliente
    @Override
    public String toString() {
        return "[ID: "+id+" || Nombre: "+getNombre()+" || Teléfono: "+getTelefono()+" || Matrícula: "+getMatricula()+"]\t";
    }
}