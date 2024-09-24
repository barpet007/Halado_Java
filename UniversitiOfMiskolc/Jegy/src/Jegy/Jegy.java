package Jegy;

import java.time.LocalDate;

public abstract class Jegy {

	private LocalDate datum;
	
	public enum Kedvezmeny {DIAK,NYUGDIJAS,TELJESARU}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	};
	
	public Jegy (LocalDate datum, Kedvezmeny kedvezmeny) {
		this.datum =datum;
	}
	
	
	@Override
	public String toString() {
		return " Dátum=" + datum + " ";
	}

	public abstract int Jegyar(int jegyar,Kedvezmeny kedvezmeny);
	
}