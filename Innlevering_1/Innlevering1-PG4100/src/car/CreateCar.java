package car;

import rental.Utleie;
import printing.MainPrinting;
import logic.RngRegNr;

public class CreateCar {
	private Utleie utleie = new Utleie();
	private MainPrinting pr = new MainPrinting();
	private RngRegNr rng = new RngRegNr();
	
	public void create() {
		for (int i = 0; i < 5; i++) {
			Car car = new Car();
			car.setRegNr(rng.rngRegNr());
			utleie.getCarList()[i] = car;
		}
		pr.statusPrint(utleie.getCarList());
	}
}
