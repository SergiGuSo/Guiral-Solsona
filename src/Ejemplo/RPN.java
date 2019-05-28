package Ejemplo;

public class RPN {
	
	private String commando;
	private NodoPila arriba;
	
	public void pushPila(double nuevo_dato) {
		NodoPila NewNodo = new NodoPila(nuevo_dato, arriba);
		arriba = NewNodo;
	}

	public double popPila() {
		double DatoArriba = arriba.dato;
		arriba = arriba.abajo;
		return DatoArriba;
	}

	public RPN(String commando) {
		arriba = null;
		this.commando = commando;
	}

	public double resultado() {
		double a, b;
		for (int i = 0; i < commando.length(); i++) {
// si es un digito
			if (Character.isDigit(commando.charAt(i))) {
				double numero;
// obtener un string a partir del numero
				String temp = "";
				for (int j = 0; (j < 100)
						&& (Character.isDigit(commando.charAt(i)) || (commando.charAt(i) == '.')); j++, i++) {
					temp = temp + String.valueOf(commando.charAt(i));
				}
// convertir a double y añadir a la pila
				numero = Double.parseDouble(temp);
				pushPila(numero);
			} else if (commando.charAt(i) == '+') {
				b = popPila();
				a = popPila();
				pushPila(a + b);
			} else if (commando.charAt(i) == '-') {
				b = popPila();
				a = popPila();
				pushPila(a - b);
			} else if (commando.charAt(i) == '*') {
				b = popPila();
				a = popPila();
				pushPila(a * b);
			} else if (commando.charAt(i) == '/') {
				b = popPila();
				a = popPila();
				pushPila(a / b);
			} else if (commando.charAt(i) == '^') {
				b = popPila();
				a = popPila();
				pushPila(Math.pow(a, b));
			} else if (commando.charAt(i) == '%') {
				b = popPila();
				a = popPila();
				pushPila(a % b);
			} else if (commando.charAt(i) != ' ') {
				throw new IllegalArgumentException();
			}
		}
		double val = popPila();
		if (arriba != null) {
			throw new IllegalArgumentException();
		}
		return val;
	}
}