import java.util.Locale;

public class Cliente {
    /*ATRIBUTOS*/
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;

    /*CONSTRUCTOR*/
    public Cliente(int id, String nombre, String telefono, String matricula) {
        if (id <= 0) throw new IllegalArgumentException("El ID debe ser positivo.");
        this.id = id;
        this.nombre = obligatorio(nombre, "Nombre");
        this.telefono = obligatorio(telefono, "Teléfono");
        this.matricula = obligatorio(matricula, "Matrícula").toUpperCase(Locale.ROOT);
    }

    //No permite que los campos seleccionados se queden en blancos, el cliente no sería válido
    private static String obligatorio(String s, String campo) {
        if (s == null || s.trim().isEmpty()) {
            System.out.println("El campo ["+campo+"] es obligatorio");
        }
        return s.trim();
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


    //Devuelve por pantalla la información del cliente
    @Override
    public String toString() {
        return "[ID: "+id+" || Nombre: "+getNombre()+" || Teléfono: "+getTelefono()+" || Matrícula: "+getMatricula()+"]\t";
    }
}