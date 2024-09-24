package Jegy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

import Jegy.Jegy.Kedvezmeny;


public class Jegy_test {

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		int icjegydb = readInt();
		ICjegy[] icjegyArray = new ICjegy[icjegydb];
		
		
		
		for (int i = 0; i < icjegyArray.length; i++) {
			System.out.println("IC"+(i+1)+ "neve:");
			
//			System.out.print("neve: ");
			String icname = input.nextLine();
//			input.nextLine();
			
			System.out.print("Dátum: ");
			String datummost = input.nextLine();
			LocalDate datum = LocalDate.parse(datummost);
//			input.nextLine();
			
			
			System.out.print("Kedvezmeny: ");
			String kedvezmeny = input.nextLine();
			Kedvezmeny kedvezmenytype = Kedvezmeny.TELJESARU;
			switch (kedvezmeny) {
			case "DIAK": kedvezmenytype=Kedvezmeny.DIAK;
				break;
			case "NYUGDIJAS": kedvezmenytype=Kedvezmeny.NYUGDIJAS;
				break;
			case "TELJESARU": kedvezmenytype=Kedvezmeny.TELJESARU;
				break;
			}
			
			int kocsiosztaly = readcarClass();
//			input.nextLine();
			
			int tavolsag = readDistance();
//			input.nextLine();
			
			icjegyArray[i] = new ICjegy(icname, datum, kedvezmenytype, kocsiosztaly,tavolsag);	
			
		}
		
		input.close();
		//Kiiratas
		
//		for (int i = 0; i < icjegyArray.length; i++) {
//			System.out.println("#"+(i+1)+"IC: " + icjegyArray[i].toString());
		listArray(icjegyArray);
		
		Arrays.sort(icjegyArray, new ICjegy.NameSorter());
		System.out.println("IC Név szerinti rendezés:");
		listArray(icjegyArray);
		
		
		System.out.println("A legtávolabb utazó:" + getLogenstway(icjegyArray));
		
		
		
		}

	
		
		
//	}
	
	public static void listArray(ICjegy[] icjegyArray) {
			for(ICjegy item: icjegyArray) {
				System.out.println(item.toString());
			}
		}
	
	private static int readInt() {
	    Scanner sc = new Scanner(System.in);
	    int icjegydb = 0;
	    boolean ok = false;

	    do {
	        try {
	            System.out.print("Mennyi IC jegy lesz (max. 4 db): ");
	            String input = sc.nextLine();
	            icjegydb = Integer.parseInt(input);
	            if (icjegydb >= 1 && icjegydb <= 4) {
	                ok = true;
	            } else {
	                System.out.println("Hibás értéket adott meg (nem 1 és 4 közé esik");
	            }
	        } catch (Exception e) {
	            System.out.println("Hibás input, kérlek adj meg érvényes értéket (számot 1 és 4 között");
	        }
	    } while (!ok);
	    
	    return icjegydb;
	    
	}
	
	private static int readcarClass() {
	    Scanner sc = new Scanner(System.in);
	    int kocsiosztaly = 0;
	    boolean ok = false;

	    do {
	        try {
	            System.out.print("Milyen kocsiosztaly (1. vagy 2.)?: ");
	            String input = sc.nextLine();
	            kocsiosztaly = Integer.parseInt(input);
	            if (kocsiosztaly >= 1 && kocsiosztaly <= 2) {
	                ok = true;
	            } else {
	                System.out.println("Hibás értéket adott meg (nem 1 vagy 2 ");
	            }
	        } catch (Exception e) {
	            System.out.println("Hibás input, kérlek adj meg érvényes értéket (egy számot 1 vagy 2-t)");
	        }
	    } while (!ok);
	    
	    return kocsiosztaly;
	    
	}
	private static int readDistance() {
	    Scanner sc = new Scanner(System.in);
	    int tavolsag = 0;
	    boolean ok = false;

	    do {
	        try {
	            System.out.print("Mekkora távolságra utazik (10-1000 km között)?: ");
	            String input = sc.nextLine();
	            tavolsag = Integer.parseInt(input);
	            if (tavolsag >= 10 && tavolsag <= 1000) {
	                ok = true;
	            } else {
	                System.out.println("Hibás értéket adott meg (nem 10 és 1000 km között ");
	            }
	        } catch (Exception e) {
	            System.out.println("Hibás input, kérlek adj meg érvényes értéket (10 és 1000 között egy számot)");
	        }
	    } while (!ok);
	    
	    return tavolsag;
	    
	}
	
	public static ICjegy getLogenstway (ICjegy[] icjegyArray) 
		{
			{
		    	int max = 0;
		    	int maxIndex = 0;
		    	for (int i = 0; i < icjegyArray.length; i++) 
			    	{
						if (icjegyArray[i].getTavolsag() > max) 
						{
							max = icjegyArray[i].getTavolsag();
							maxIndex = i;
						}
		
					}
				return icjegyArray[maxIndex];
		    }
		}
	}
	





