package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_LIBROS")
public class Libro {
	@Id
	private int id;
	@Column(length = 20, nullable = false)
	private String titulo;
	@Column(length = 20, nullable = false)
	private String autor;
	private int year;
	private boolean disponible;

	public Libro() {
		super();
	}

	public Libro(int id, String titulo, String autor, int year, boolean disponible) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.year = year;
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", year=" + year + ", disponible="
				+ disponible + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

}
