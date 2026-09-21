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

    //Devuelve por pantalla la información del cliente
    @Override
    public String toString() {
        return "[ID: "+id+" || Nombre: "+getNombre()+" || Teléfono: "+getTelefono()+" || Matrícula: "+getMatricula()+"]\t";
    }
}
