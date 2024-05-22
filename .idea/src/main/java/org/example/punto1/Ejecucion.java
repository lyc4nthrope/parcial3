package org.example.punto1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Ejecucion {
	private static final Logger LOGGER = Logger.getLogger(Ejecucion.class.getName());

	public static void main( String args[] ) throws IOException {
		ArrayList<Character>listaAux= new ArrayList<>();
		Tuberia t = new Tuberia();
		P1 p1 = new P1(t);
		P2 p2 = new P2(t);
		Consumidor c = new Consumidor( t ,"universid@d#2024-2%", listaAux);
		p1.start();
		p2.start();
		c.start();

	}

	public static void guardarArchivo(String ruta,String contenido, Boolean flagAnexarContenido) throws IOException {
		File existencia= new File(ruta);
		if (!existencia.exists()){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, false));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		FileWriter fw = new FileWriter(ruta,flagAnexarContenido);
		BufferedWriter bfw = new BufferedWriter(fw);
		bfw.write(contenido);
		bfw.close();
		fw.close();
	}

	public static void archivoRegistro()
	{
		String log = "";
		Logger LOGGER = Logger.getLogger("Se completo la palabra");
		FileHandler fileHandler =  null;
		cargarFechaSistema();
		try {
			fileHandler = new FileHandler("MyLogEjercicioClase.txt", true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
			LOGGER.log(Level.INFO, "Se completo la palabra - " + cargarFechaSistema());
		} catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

			fileHandler.close();
		}
	}
	private static String cargarFechaSistema() {

		String diaN = "";
		String mesN = "";
		String añoN = "";
		String horaN="";
		String minutoN="";

		Calendar cal1 = Calendar.getInstance();

		int  dia = cal1.get(Calendar.DATE);
		int mes = cal1.get(Calendar.MONTH)+1;
		int año = cal1.get(Calendar.YEAR);
		int hora = cal1.get(Calendar.HOUR);
		int minuto = cal1.get(Calendar.MINUTE);

		if(dia < 10){
			diaN+="0"+dia;
		}
		else{
			diaN+=""+dia;
		}
		if(mes < 10){
			mesN+="0"+mes;
		}
		else{
			mesN+=""+mes;
		}
		if(hora < 10){
			horaN+="0"+hora;
		}
		else{
			horaN+=""+hora;
		}
		if(minuto < 10){
			minutoN+="0"+minuto;
		}
		else{
			minutoN+=""+minuto;
		}

		return año+"-"+mesN+"-"+diaN+" / "+horaN+":"+minutoN;
	}









































	static String prueba= "░░░░░░░░░░░░▄▄▄█▀▀▀▀▀▀▀▀█▄▄▄░░░░░░░░░░░░\n" +
			"░░░░░░░░▄▄█▀▀░░░░░░░░░░░░░░▀▀█▄▄░░░░░░░░\n" +
			"░░░░░░▄█▀░░░░▄▄▄▄▄▄▄░░░░░░░░░░░▀█▄░░░░░░\n" +
			"░░░░▄█▀░░░▄██▄▄▄▄▄▄▄██▄░░░░▄█▀▀▀▀██▄░░░░\n" +
			"░░░█▀░░░░█▀▀▀░░▄██░░▄▄█░░░██▀▀▀███▄██░░░\n" +
			"░░█░░░░░░▀█▀▀▀▀▀▀▀▀▀██▀░░░▀█▀▀▀▀███▄▄█░░\n" +
			"░█░░░░░░░░░▀▀█▄▄██▀▀░░░░░░░░▀▄▄▄░░░▄▄▀█░\n" +
			"█▀░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▀▀▀▀▀░░▀█\n" +
			"█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄░░░░█\n" +
			"█░░░░░░░░░░░░░░░░░░░░░░░░▄▄▄▄▄██░░▀█░░░█\n" +
			"█░░░░░░░░░░░░░░█░░░▄▄▄█▀▀▀░░░▄█▀░░░░░░░█\n" +
			"█░░░░░░░░░░░░░░░░░░▀░░░░░░░░█▀░░░░░░░░░█\n" +
			"█▄░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄█\n" +
			"░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█░\n" +
			"░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█░░\n" +
			"░░░█▄░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄█░░░\n" +
			"░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░░░░░░▄█▀░░░░\n" +
			"░░░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░░▄█▀░░░░░░";
}