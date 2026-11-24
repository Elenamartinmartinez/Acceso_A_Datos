package dominio.estaciones;

import java.time.LocalDate;

/** Contiene los datos de facturación de una estación. */
public class FichaFacturacion {
    private final Integer id, idEstacion;
    private final String razonSocial, identificadorFiscal;
    private final LocalDate fechaAlta;
    public FichaFacturacion(Integer id, Integer idEstacion, String razonSocial, String identificadorFiscal, LocalDate fechaAlta) {
        if (idEstacion == null || razonSocial == null || razonSocial.trim().isEmpty() || identificadorFiscal == null || identificadorFiscal.trim().isEmpty() || fechaAlta == null) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.idEstacion = idEstacion; this.razonSocial = razonSocial.trim(); this.identificadorFiscal = identificadorFiscal.trim(); this.fechaAlta = fechaAlta;
    }
    public Integer getId() { return id; }
    public Integer getIdEstacion() { return idEstacion; }
    public String getRazonSocial() { return razonSocial; }
    public String getIdentificadorFiscal() { return identificadorFiscal; }
    public LocalDate getFechaAlta() { return fechaAlta; }
}
