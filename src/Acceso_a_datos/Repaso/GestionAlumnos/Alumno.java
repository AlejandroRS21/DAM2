package Acceso_a_datos.Repaso.GestionAlumnos;

import java.io.Serializable;
import java.time.LocalDate;

public class Alumno implements Serializable {
    private String dni;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private LocalDate fechaMatriculacion;
    private double mediaExpediente;

    public Alumno(String dni, String nombreCompleto, LocalDate fechaNacimiento, LocalDate fechaMatriculacion, double mediaExpediente) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMatriculacion = fechaMatriculacion;
        this.mediaExpediente = mediaExpediente;
    }

    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public double getMediaExpediente() {
        return mediaExpediente;
    }

    @Override
    public String toString() {
        return "DNI: " + dni + ", Nombre: " + nombreCompleto + ", Fecha Nacimiento: " + fechaNacimiento + ", Fecha Matriculación: " + fechaMatriculacion + ", Media: " + mediaExpediente;
    }
}
