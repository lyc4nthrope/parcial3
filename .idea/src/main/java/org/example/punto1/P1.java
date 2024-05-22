package org.example.punto1;

import java.io.*;
import java.sql.Array;

class P1 extends Thread {
	private Tuberia tuberia;
	private String alfabeto = reader();
	char[] arreglo = alfabeto.toCharArray();

	private static String reader(){
		File ficheroNuevo=new File("letras.txt");
		String alfabeto = "";
		try(BufferedReader br=new BufferedReader(new FileReader(ficheroNuevo));){
			alfabeto=br.readLine();
		}catch(IOException e){
			System.out.println("Error E/S: "+e);
		}
		return alfabeto;
	}

	public P1(Tuberia t)
	{
		// Mantiene una copia propia del objeto compartido
		tuberia = t;
	}

	@Override
	public void run() {
		char c;

		// Mete 12 letras.txt en la tuber�a
		while (!Tuberia.fin) {
			c = arreglo[ (int)( Math.random()*arreglo.length ) ];
			if (isVocal(c)){
				tuberia.lanzar(c);
				//System.out.println("Productor1 lanzo " + c + " a la tuberia.");
				try
				{
					sleep(150 );
				}
				catch( InterruptedException e )
				{
					System.out.println(e);
				}
			}
		}
		System.out.println("fin hilo1");
	}

	private boolean isVocal(char c){
		return c=='a'||c=='e'||c=='i'||c=='o'||c=='u'
				||c=='A'||c=='E'||c=='I'||c=='O'||c=='U';
	}
}