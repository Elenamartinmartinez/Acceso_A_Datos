public class Cliente {
    /*ATRIBUTOS*/
    private final int id ;
    private String nombre ;
    private String tlfno ;
    private String matricula ;

    /*CONSTRUCTOR*/
    public Cliente (int id, String nombre, String tlfno, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.tlfno = tlfno;
        this.matricula = matricula.trim().toUpperCase();
    }

    /*MÉTODOS*/
    //Métodos getters y setters para todos los atributos que se pidan por pantalla
    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setTlfno(String tlfno) {
        this.tlfno = tlfno;
    }
    public String getTlfno() {
        return tlfno;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getMatricula() {
        return matricula;
    }

    //Devuelve por pantalla la información del cliente
    @Override
    public String toString() {
        return "[ID: "+id+" || Nombre: "+getNombre()+" || Teléfono: "+getTlfno()+" || Matrícula: "+getMatricula()+"]\t";
    }
}
