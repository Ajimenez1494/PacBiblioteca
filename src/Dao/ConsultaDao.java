package Dao;

import java.util.List;
import java.util.Scanner;

import Entity.Lector;
import Entity.Libro;
import Entity.Prestamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConsultaDao {
	// Libros actualmente prestados a un lector.
	// Libros disponibles para préstamo.
	// Historial de préstamos por lector.

	public void listarLectores() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();

		List<Lector> lectores = ent.createQuery("FROM Lector", Lector.class).getResultList();

		for (Lector lector : lectores) {
			System.out.println("ID: " + lector.getId() + ", Nombre: " + lector.getNombre());
		}
		ent.close();
	}

	public void listarLibros() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();
		List<Libro> libros = ent.createQuery("FROM Libro", Libro.class).getResultList();

		for (Libro libro : libros) {
			System.out.println("ID: " + libro.getId() + ", Nombre: " + libro.getTitulo());
		}
		ent.close();

	}

	public void verLibroID() {
		Scanner respuesta = new Scanner(System.in);
		System.out.println("Introduce el ID del libro:");
		int idLibro = respuesta.nextInt();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();

		Libro libro = ent.find(Libro.class, idLibro);

		if (libro != null) {
			System.out.println("El nombre del libro con ID '" + idLibro + "' es: " + libro.getTitulo()
					+ ". Actualmente está disponible: " + libro.isDisponible());
		} else {
			System.out.println("No se encontró ningún libro con el ID '" + idLibro + "'.");
		}

		ent.close();
	}

	public void verLectorID() {
		Scanner respuesta = new Scanner(System.in);
		System.out.println("Introduce el ID del lector:");
		int idLector = respuesta.nextInt();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();

		Lector lector = ent.find(Lector.class, idLector);

		if (lector != null) {
			System.out.println("El nombre del lector con ID '" + idLector + "' es: " + lector.getNombre());
		} else {
			System.out.println("No se encontró ningún lector con el ID '" + idLector + "'.");
		}

		ent.close();
	}

	public void listarLibrosPrestados() {
		Scanner respuesta = new Scanner(System.in);
		System.out.println("Introduce el ID del lector:");
		int idLector = respuesta.nextInt();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();

		List<Prestamo> prestamos = ent.createQuery("FROM Prestamo WHERE lector.id = :lectorId", Prestamo.class)
				.setParameter("lectorId", idLector).getResultList();

		if (!prestamos.isEmpty()) {
			System.out.println("Libros prestados al lector con ID '" + idLector + "':");
			for (Prestamo prestamo : prestamos) {
				System.out.println("- " + prestamo.getLibro().getTitulo());
			}
		} else {
			System.out.println("No se encontraron libros prestados al lector con el ID '" + idLector + "'.");
		}

		ent.close();
	}

	public void listarLibrosDisponibles() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();

		List<Libro> librosDisponibles = ent.createQuery("FROM Libro WHERE disponible = true", Libro.class)
				.getResultList();

		if (!librosDisponibles.isEmpty()) {
			System.out.println("Libros disponibles:");
			for (Libro libro : librosDisponibles) {
				System.out.println("- " + libro.getTitulo());
			}
		} else {
			System.out.println("No hay libros disponibles para préstamo en este momento.");
		}

		ent.close();
	}

	public void historialPrestamosPorLector() {
		Scanner respuesta = new Scanner(System.in);
		System.out.println("Introduce el ID del lector:");
		int idLector = respuesta.nextInt();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();

		List<Prestamo> prestamos = ent.createQuery("FROM Prestamo WHERE lector.id = :lectorId", Prestamo.class)
				.setParameter("lectorId", idLector).getResultList();

		if (!prestamos.isEmpty()) {
			System.out.println("Historial de préstamos del lector con ID '" + idLector + "':");
			for (Prestamo prestamo : prestamos) {
				System.out.println(
						"- " + prestamo.getLibro().getTitulo() + ", Fecha de préstamo: " + prestamo.getFechaPrestamo());
			}
		} else {
			System.out.println("No se encontraron préstamos para el lector con el ID '" + idLector + "'.");
		}

		ent.close();
	}
}
