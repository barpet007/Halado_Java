package Jegy;

import java.time.LocalDate;
import java.util.Comparator;

public class ICjegy extends Jegy {
	
	private String icname;
	private int kocsiosztaly;
	private int tavolsag;
	private int kmAr;
	private static final int potjegy=450;
	private double mertek;

	
	public ICjegy(String icname, LocalDate datum, Kedvezmeny kedvezmeny, int kocsiosztaly, int tavolsag) {
		super(datum, kedvezmeny);
		this.icname = icname;
		this.kocsiosztaly = kocsiosztaly;
		this.tavolsag = tavolsag;
		switch (kocsiosztaly) {
			case 1: setKmAr(30);
					break;
			case 2:setKmAr(25);
					break;}
		switch (kedvezmeny) {
		case DIAK:mertek=50.0/100.0;
			break;
		case NYUGDIJAS:mertek=90.0/100.0;
			break;	
		case TELJESARU:mertek=1;
			break;
		}
		
	} 
	


	public ICjegy(String icname,LocalDate datum, Kedvezmeny kedvezmeny, int kocsiosztaly) {
		
		this(icname,datum,kedvezmeny,kocsiosztaly,(int) (Math.random()*1001)+10);
	
		}
	
	

	public String getName() {
		return icname;
	}

	public void setName(String name) {
		this.icname = name;
	}

	public int getKocsiosztaly() {
		return kocsiosztaly;
	}

	public void setKocsiosztaly(int kocsiosztaly) {
		this.kocsiosztaly = kocsiosztaly;
	}

	public int getTavolsag() {
		return tavolsag;
	}

	public void setTavolsag(int tavolsag) {
		this.tavolsag = tavolsag;
	}

	public int getKmAr() {
		return kmAr;
	}

	public void setKmAr(int kmAr) {
		this.kmAr = kmAr;
	}

	public static int getPotjegy() {
		return potjegy;
	}

	@Override
	public int Jegyar(int jegyar,Kedvezmeny kedvezmeny) {
		return jegyar=(int) (((tavolsag*kmAr)*mertek)+potjegy);
	}
	
	@Override
	public String toString() {
		return "ICjegy " + super.toString() + "IC neve=" + icname + ", kocsiosztaly=" + kocsiosztaly + ", tavolsag=" + tavolsag + ", kmAr=" + kmAr
				+ ", kedvezmeny mértéke =" +  mertek + ", ";
	}
	
	public static class NameSorter implements Comparator<ICjegy> {
		public int compare(ICjegy j1, ICjegy j2) {
			return j1.getName().compareTo(j2.getName());
		}
	}
	
	


	
	
	
	
	
	

}
