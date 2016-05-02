package car;

public class Car {
	private Integer regNr;
	private boolean rented = false;
	
	public void setRented(boolean rented){
		this.rented = rented;
	}
	
	public boolean getRented(){
		return rented;
	}
	
	public void setRegNr(Integer nr){
		regNr = nr;
	}
	
	public String toString(){
		return "XX" + regNr.toString();
	}
}
