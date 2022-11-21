package com.fca.calidad.Calidad2022_Martinez;

public class Aritmetica {
	private float ultimoResultado;

	public float suma(float primerSumando, float segundoSumando) {
		return ultimoResultado = primerSumando + segundoSumando;
	}
	
	public float resta(float minuendo, float sustraendo) {
		return ultimoResultado = minuendo - sustraendo;
	}
	
	public float multiplicacion(float primerFactor, float segundoFactor) {
		return ultimoResultado = primerFactor * segundoFactor;
	}
	
	public float division(double dividendo, double divisor) {
		return ultimoResultado = (float) (dividendo / divisor);
	}
	public float divisionEntera(int dividendo, int divisor) {
		return ultimoResultado = dividendo / divisor;
	}
	
	public float getUltimoResultado() {
		return ultimoResultado;
	}
	
}
