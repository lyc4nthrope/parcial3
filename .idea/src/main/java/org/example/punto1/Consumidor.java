package org.example.punto1;

import java.io.IOException;
import java.util.ArrayList;

public class Consumidor extends Thread {
	private Tuberia tuberia;
	private String palabra;
	char [] arregloAux;
	char [] arregloResultado;
	boolean fin= false;
	ArrayList<Character> listaAux;

	public Consumidor(Tuberia t , String palabra, ArrayList<Character>listaAux)
	{
		// Mantiene una copia propia del objeto compartido
		tuberia = t;
		this.palabra=palabra;
		this.listaAux=listaAux;
		this.arregloAux=this.palabra.toCharArray();
		this.arregloResultado= new char[this.palabra.length()];
	}

	@Override
	public void run() {
		char c =' ';
		String palabraObteniendo="";
		while(!Tuberia.fin){
			c = tuberia.recoger();
			int indice = constainsChar(arregloAux,c);
			if (indice!=-1) {
				this.arregloResultado[indice]=arregloAux[indice];
				this.arregloAux[indice]=0;
				palabraObteniendo = arrayToString(arregloResultado);
				System.out.println(palabraObteniendo + "    " + palabra);
			} else {
				listaAux.add(c);
//				System.out.println("No se acepto a : "+c);
			}

			c = tuberia.recoger();
			indice = constainsChar(arregloAux,c);
			if (indice!=-1) {
				this.arregloResultado[indice]=arregloAux[indice];
				this.arregloAux[indice]=0;
				palabraObteniendo = arrayToString(arregloResultado);
				System.out.println(palabraObteniendo + "    " + palabra);
			} else {
				listaAux.add(c);
//				System.out.println("No se acepto a : "+c);
			}
			Tuberia.fin = palabra.equals(palabraObteniendo);
			try {
				sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
        try {
            Ejecucion.guardarArchivo("letrasSobrantesEjercicioClase.txt",listaAux.toString(),false);
			Ejecucion.archivoRegistro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

		System.out.println("Finalizo \na");
		System.out.println(palabraObteniendo+" = "+palabra);
		System.out.println(listaAux.toString());
	}



	private int constainsChar(char [] arregloChar, char c){
		boolean existe = false;
		int index = -1;
		for (int i = 0; i < arregloChar.length && !existe; i++) {
			if (arregloChar[i]==c) {
				existe=true;
				index=i;
			}
		}
		return index;
	}

//	private char[] addChar(char [] arreglo, char c , int indice){
//		ArrayList<Character> listaArreglo = arrayToListChar(arreglo);
//		int i = 0;
//		while (i<listaArreglo.size() && indice > arregloAux.get(i)) {
//			i++;
//		}
//		if (i!=0){
//			listaArreglo.add(i-1,c);
//			arregloAux.add(i-1,indice);
//		}else {
//			listaArreglo.add(i,c);
//			arregloAux.add(i,indice);
//		}
//
//		char [] newArray = new char[listaArreglo.size()];
//		for (int j = 0; j < listaArreglo.size(); j++) {
//			newArray[j]=listaArreglo.get(j);
//		}
//		return newArray;
//	}
	private String arrayToString(char [] arreglo){
		String palabra="";
		for (int i = 0; i < arreglo.length; i++) {
			if (arreglo[i]!=0){
				palabra=palabra+arreglo[i];
			}
		}
		return palabra;
	}

	private String deleteChar(String palabra, int index){
		StringBuilder prueba = new StringBuilder(palabra);
		prueba.deleteCharAt(index);
		return prueba.toString();
	}
	private ArrayList<Character> arrayToListChar (char[]array){
		ArrayList<Character>listaAux = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			listaAux.add(array[i]);
		}
		return listaAux;
	}
	private ArrayList<Integer> arrayToListInt (int[]array){
		ArrayList<Integer>listaAux = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			listaAux.add(array[i]);
		}
		return listaAux;
	}


}



