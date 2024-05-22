package org.example.punto1;

import java.util.Arrays;

public class Tuberia {

	static boolean fin = false;
	
	private char buffer[] = new char[12];
	private int siguiente = 0;
	// Flags para saber el estado del buffer
	private boolean estaLlena = false;
	private boolean estaVacia = true;

	// M�todo para retirar letras.txt del buffer
	public synchronized char recoger() 
	{
		// No se puede consumir si el buffer est� vac�o
		while( estaVacia == true )
		{
			try {
				wait(); // Se sale cuando estaVacia cambia a false
				if (fin) {
					Arrays.fill(buffer,(char)0);
					siguiente=0;
					estaVacia=false;
					notifyAll();
				}
			} catch( InterruptedException e ) {

			}
		}
		// Decrementa la cuenta, ya que va a consumir una letra
		siguiente--;
		// Comprueba si se retir� la �ltima letra
		if( siguiente == 0 )
			estaVacia = true;
		// El buffer no puede estar lleno, porque acabamos
		// de consumir
		estaLlena = false;
		notify();

		// Devuelve la letra al thread consumidor
		return( buffer[siguiente] );
	}
	
	

	// M�todo para a�adir letras.txt al buffer
	public synchronized void lanzar( char c ) 
	{
		// Espera hasta que haya sitio para otra letra
		while( estaLlena == true )
		{
			try {

				wait(); // Se sale cuando estaLlena cambia a false
				if (fin){
					Arrays.fill(buffer,(char)0);
					siguiente=0;
					estaLlena=false;
					notifyAll();
				}
			} catch( InterruptedException e ) {
				;
			}
		}
		// A�ade una letra en el primer lugar disponible
		buffer[siguiente] = c;
		// Cambia al siguiente lugar disponible
		siguiente++;
		// Comprueba si el buffer est� lleno
		if( siguiente == buffer.length )
			estaLlena = true;
		estaVacia = false;
		notify();
	}




}