public class Cliente {
    /*ATRIBUTOS*/
    private int id ;
    private String nombre ;
    private int tlfno ;
    private String matricula ;


    /*MÉTODOS*/
    //Métodos getters y setters para todos los atributos que se pidan por pantalla
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setTlfno(int tlfno) {
        this.tlfno = tlfno;
    }
    public int getTlfno() {
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
        return "[ID: "+id+" || Nombre: "+getNombre()+" || Teléfono: "+getTlfno()+" || Matrícula: "+getMatricula()+"]";
    }
}
