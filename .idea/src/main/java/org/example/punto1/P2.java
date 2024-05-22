package org.example.punto1;

class P2 extends Thread {
	private Tuberia tuberia;
	boolean fin;
	private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#/%+:;_";
	char[] arreglo;

	public P2(Tuberia t)
	{
		// Mantiene una copia propia del objeto compartido
		tuberia = t;
	}

	@Override
	public void run() {
		char c;

		// Mete 10 letras.txt en la tuber�a
		while(!Tuberia.fin) {
			c =arreglo[ (int)(Math.random()*arreglo.length ) ];
			if (isConsant(c)){
				tuberia.lanzar( c );
			//System.out.println( "Productor2 lanzo "+c+" a la tuberia." );
				try
				{
					sleep(150);
				}
				catch( InterruptedException e )
				{
					System.out.println(e);;
				}
			}
		}
		System.out.println("fin hilo2");
	}

	private boolean isConsant(char c){
		return !isVocal(c) && !Character.isDigit(c) && ( (97<=c && c<=122) || (65<=c && c<=90) );
	}
	private boolean isVocal(char c){
		return c=='a'||c=='e'||c=='i'||c=='o'||c=='u'
				||c=='A'||c=='E'||c=='I'||c=='O'||c=='U';
	}
}