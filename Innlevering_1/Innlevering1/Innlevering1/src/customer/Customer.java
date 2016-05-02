package customer;

import javax.swing.JOptionPane;

public class Customer {
	private String name;

	public Customer(){
		setName(JOptionPane.showInputDialog
				("Please state your name"));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		if(getName() == null){
			return "";
		} else {
			return "\t\t\t\tKundens namn är: " 
									+ getName();
		}
	}
}
