package dominio.personal;

import java.time.LocalDate;

/** Asocia un empleado con una función y una estación. */
public class AsignacionFuncion {
    private final Integer id, idEmpleado, idEstacion;
    private final TipoFuncion funcion;
    private final LocalDate fechaInicio;
    public AsignacionFuncion(Integer id, Integer idEmpleado, Integer idEstacion, TipoFuncion funcion, LocalDate fechaInicio) {
        if (idEmpleado == null || idEstacion == null || funcion == null || fechaInicio == null) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.idEmpleado = idEmpleado; this.idEstacion = idEstacion; this.funcion = funcion; this.fechaInicio = fechaInicio;
    }
    public Integer getId() { return id; }
    public Integer getIdEmpleado() { return idEmpleado; }
    public Integer getIdEstacion() { return idEstacion; }
    public TipoFuncion getFuncion() { return funcion; }
    public LocalDate getFechaInicio() { return fechaInicio; }
}
