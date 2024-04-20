package Dao;

import java.time.LocalDate;
import java.util.Scanner;

import Entity.Lector;
import Entity.Libro;
import Entity.Prestamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class GestionDao {
	// Gestión de préstamos, incluyendo asignar un libro a un lector y registrar la
	// devolución.
	public void insertarLibro() {

		// int id, String titulo, String autor, int year, boolean disponible
		System.out.println("Dime el nombre del libro que quieres registrar");
		Scanner respuesta = new Scanner(System.in);
		String titulo = respuesta.nextLine();
		System.out.println("Dime el nombre del autor que quieres registrar");
		String autor = respuesta.nextLine();
		System.out.println("Dime el año de publicacion");
		int year = respuesta.nextInt();
		System.out.println("Dime el id que quieres asignarle");
		int ID = respuesta.nextInt();

		Libro libro = new Libro(ID, titulo, autor, year, true);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();
		ent.getTransaction().begin();
		try {
			ent.persist(libro);
			System.out.println("Inserto Libro" + libro);
			ent.getTransaction().commit();

		} catch (Exception e) {
			ent.getTransaction().rollback();
		}

	}

	public void insertarLector() {
		// int id, String nombre, String apellido, String email
		System.out.println("Dime el nombre del lector que quieres registrar");
		Scanner respuesta = new Scanner(System.in);
		String nombre = respuesta.nextLine();
		System.out.println("Dime el apellido del lector que quieres registrar");
		String apellido = respuesta.nextLine();
		System.out.println("Dime el email del lector que quieres registrar");
		String email = respuesta.nextLine();
		System.out.println("Dime el id que quieres asignarle");
		int ID = respuesta.nextInt();
		Lector lector = new Lector(ID, nombre, apellido, email);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();
		ent.getTransaction().begin();
		try {
			ent.persist(lector);
			System.out.println("Inserto Lector " + lector);
			ent.getTransaction().commit();

		} catch (Exception e) {
			ent.getTransaction().rollback();
		}

	}

	public void prestarLibro() {
		Scanner respuesta = new Scanner(System.in);
		System.out.println("Dime el id del libro que se va a prestar");
		int idLibro = respuesta.nextInt();
		System.out.println("Dime el id del lector que se lo lleva");
		int idLector = respuesta.nextInt();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();
		Libro libro = ent.find(Libro.class, idLibro);
		Lector lector = ent.find(Lector.class, idLector);

		ent.getTransaction().begin();

		if (libro.isDisponible()) {
			Prestamo prestamo = new Prestamo();
			prestamo.setLibro(libro);
			prestamo.setLector(lector);
			LocalDate fechaActual = LocalDate.now();
			prestamo.setFechaPrestamo(fechaActual);
			libro.setDisponible(false);

			ent.persist(prestamo);
			ent.persist(libro);
			System.out.println("Prestando Libro");
		} else {
			System.out.println("Lo siento, el libro no esta disponible");
		}

		ent.getTransaction().commit();
		ent.close();

	}

	public void devolverLibro() {
		Scanner respuesta = new Scanner(System.in);
		System.out.println("Dime el id del libro que se devuelve:");
		int idLibro = respuesta.nextInt();
		System.out.println("Dime el id del lector que lo devuelve:");
		int idLector = respuesta.nextInt();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
		EntityManager ent = emf.createEntityManager();
		ent.getTransaction().begin();

		Libro libro = ent.find(Libro.class, idLibro);
		Lector lector = ent.find(Lector.class, idLector);

		if (libro != null && lector != null) {
			TypedQuery<Prestamo> query = ent.createQuery(
					"SELECT p FROM Prestamo p WHERE p.libro.id = :libroId AND p.lector.id = :lectorId", Prestamo.class);
			query.setParameter("libroId", libro.getId());
			query.setParameter("lectorId", lector.getId());

			Prestamo prestamo = query.getSingleResult();
			if (prestamo != null) {
				libro.setDisponible(true);
				LocalDate fechaActual = LocalDate.now();

				prestamo.setFechaDevolución(fechaActual);
				ent.merge(libro);
				ent.merge(prestamo);
				System.out.println("Libro devuelto.");
			} else {
				System.out.println("Este libro no está prestado a este lector.");
			}
		} else {
			System.out.println("No se encontró el libro o el lector.");
		}

		ent.getTransaction().commit();
		ent.close();
	}

}
