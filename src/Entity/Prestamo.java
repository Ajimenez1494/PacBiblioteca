package Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRESTAMOS")
public class Prestamo {
	@Id
	private int id;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolución;

	@ManyToOne
	@JoinColumn(name = "libro_id")
	private Libro libro;
	@ManyToOne
	@JoinColumn(name = "lector_id")
	private Lector lector;

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolución=" + fechaDevolución
				+ ", libro=" + libro + ", lector=" + lector + "]";
	}

	public Prestamo() {
		super();
	}

	public Prestamo(int id, LocalDate fechaPrestamo, LocalDate fechaDevolución, Libro libro, Lector lector) {
		super();
		this.id = id;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolución = fechaDevolución;
		this.libro = libro;
		this.lector = lector;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolución() {
		return fechaDevolución;
	}

	public void setFechaDevolución(LocalDate fechaDevolución) {
		this.fechaDevolución = fechaDevolución;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

}
