import java.util.Scanner;

import Dao.ConsultaDao;
import Dao.GestionDao;

public class BIbliotecaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String menu = "1- Insertar Libro\n2- Insertar Lector\n3- Listado de Libros\n4- Listado de lectores\n5- Ver Libro por ID\n6- Ver Lector por ID\n7- Prestar libro\n8- Devolver libro\n9- Libros actualmente prestados a un lector\n10- Libros disponibles para préstamo\n11- Historial de préstamos por lector\n12- Salir";
		pintarMenu(menu);
	}

	public static int pideDatoNumerico(String texto) {
		System.out.println(texto);
		System.out.println("Selecciona una opcion:");
		Scanner respuesta = new Scanner(System.in);
		int opcion = respuesta.nextInt();
		return opcion;
	}

	public static void pintarMenu(String menu) {

		int respuesta = pideDatoNumerico(menu);

		ConsultaDao consulta = new ConsultaDao();
		GestionDao gestion = new GestionDao();
		while (respuesta != 12) {
			switch (respuesta) {
			case 1:
				gestion.insertarLibro();
				break;
			case 2:
				gestion.insertarLector();
				break;
			case 3:
				consulta.listarLibros();
				break;
			case 4:
				consulta.listarLectores();
				break;
			case 5:
				consulta.verLibroID();
				break;
			case 6:
				consulta.verLectorID();
				break;
			case 7:
				gestion.prestarLibro();
				break;
			case 8:
				gestion.devolverLibro();
				break;
			case 9:
				consulta.listarLibrosPrestados();
				break;
			case 10:
				consulta.listarLibrosDisponibles();
				break;
			case 11:
				consulta.historialPrestamosPorLector();
				break;
			default:
				System.out.println("Opción inválida. Inténtalo de nuevo.");
			} 
			

			respuesta = pideDatoNumerico(menu);
		}
	}
}
