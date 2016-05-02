package car;

public class Car {
	private String regNr;
	private boolean isRented = false;
	
	public void setIsRented(boolean rented){
		this.isRented = rented;
	}
	
	public boolean getIsRented(){
		return isRented;
	}
	
	public void setRegNr(String nr){
		regNr = nr;
	}
	
	public String getRegNr(){
		return regNr;
	}
	
	public String toString(){
		return "XX" + getRegNr();
	}
}
