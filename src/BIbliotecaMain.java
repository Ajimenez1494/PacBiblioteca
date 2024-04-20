import java.util.Scanner;

import Dao.ConsultaDao;
import Dao.GestionDao;

public class BIbliotecaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String menu = "1- Insertar Libro\n2- Insertar Lector\n3- Listado de Libros\n4- Listado de lectores\n5- Ver Libro por ID\n6- Ver Lector por ID\n7- Salir";
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
		while (respuesta != 7) {
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
			default:
				System.out.println("Opción inválida. Inténtalo de nuevo.");
			}

			respuesta = pideDatoNumerico(menu);
		}
	}
}
