import javax.swing.JOptionPane;

public class AFD {
	
	//Funcion encargada de convertir un simbolo en un indice para la matriz de transiccion
	public static  int index(String caracter,String Lenguaje[]) {
		for(int a=0;a<Lenguaje.length;a++) {
			if(caracter.equals(Lenguaje[a])) {
				return a;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		
		int STotales=Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos símbolos tiene tu lenguaje?"));
		//Arreglo encargado de guardar todos los simboloes
		String Lenguaje[]=new String[STotales];
		//Pedir todos los simbolos al usuario
		for(int i=0;i<STotales;i++) {
			String simbolo=JOptionPane.showInputDialog("Digite su "+(i+1)+"° elemento");
			Lenguaje[i]=simbolo;
		}
		//Pedir al usuarios cuantos estados contiene el AFD
		int QTotales=Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos estados tiene su AFD? "));
		//Matriz donde Guarda todas las transicciones de afd
		int MatrixdeTransiccion[][]=new int[QTotales][STotales];
		
		//Pedir al usuario cada una de las transicciones del AFD
		for(int i=0;i<MatrixdeTransiccion.length;i++){
			int a=0;
			while(a<Lenguaje.length){
				String trans=JOptionPane.showInputDialog("¿Cúal es la transición de q"+i+ " con el elemento "+Lenguaje[a]+"?");
				int transiccion=Integer.parseInt(trans.substring(1));
				if(transiccion<0||transiccion>=QTotales) {
					JOptionPane.showMessageDialog(null, "Transición no valida", "Error", 0);
					continue;
				}
				MatrixdeTransiccion[i][a]=transiccion;
				a++;
			}
		}
		//Pedir al usuario los estados finales
		int EFTotales=Integer.parseInt(JOptionPane.showInputDialog("¿Cúantos estados finales?"));
		int[] EstadosFinales=new int[EFTotales];
		for(int i=0;i<EstadosFinales.length;i++) {
			String estado=JOptionPane.showInputDialog("Ingrese el "+(i+1)+"° estado final");
			int num=Integer.parseInt(estado.substring(1));
			EstadosFinales[i]=num;
		}
		
		//Cadena que el programa va a analizar
		String cadena=JOptionPane.showInputDialog("Ingrese la cadena a revisar:");
		//Ciclo que analiza las cadenas que ingrese el usuario 
		do{
		int EstadoActual=0;
		//Comprobar en que estado va a terminar la cadena
		for(int i=0;i<cadena.length();i++) {
			String caracter=cadena.substring(i,i+1);
			int index=index(caracter,Lenguaje);
			if(index==-1) {
				JOptionPane.showMessageDialog(null, "Un simbolo de la cadena ingresada no es válido", "Error", 0);
				EstadoActual=-1;
				break;
			}
			EstadoActual=MatrixdeTransiccion[EstadoActual][index];
			
		}
		//Revisar si el estado donde termino la cadena esta en uno de los estados finales permitidos 
		int Aceptada=0;
		for(int i=0;i<EstadosFinales.length;i++) {
			if(EstadoActual==EstadosFinales[i]) {
				Aceptada=1;
			}
		}
		//Imprimir mensaje de rechazar cadena o denegarla
		if(Aceptada==1) {
			JOptionPane.showMessageDialog(null, "Cadena aceptada", "Resultado", 2);
		}
		else {
			JOptionPane.showMessageDialog(null, "Cadena no aceptada", "Resultado", 0);
		}
		//Volver a preguntar sobre una nueva cadena
		cadena=JOptionPane.showInputDialog("Ingrese la cadena a revisar:");
		
		}while(cadena!=null);
	}
}