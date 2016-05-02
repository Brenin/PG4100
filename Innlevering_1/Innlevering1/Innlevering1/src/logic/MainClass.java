package logic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import car.Car;
import customer.Customer;

public class MainClass {
	private static Utleie utleie = new Utleie();
	private static Rng rng = new Rng();
	final static CountDownLatch latch = new CountDownLatch(5);
	
	public static void main(String[] args) {
		utleie.create();
		
		ExecutorService executor = Executors.newFixedThreadPool(11);
		
		for(int i = 0; i < 10; i++){
			executor.execute(new RentalTask(latch));
		}
		executor.shutdown();
	}
	
	public static class RentalTask implements Runnable{
		private final CountDownLatch latch;
		
		public RentalTask(CountDownLatch latch){
			this.latch = latch;
		}
		
		@Override
		public void run() {
			Customer cu = new Customer();
			System.out.println(cu.toString());
			latch.countDown();
			try {
				latch.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Random rand = new Random();
			
			try{
				while(true){
					int tmp = rng.waitTime();
					Thread.sleep(tmp);
					
					int carToRent = rand.nextInt(5);
					utleie.rentCar(carToRent, cu.getName());
					
					int tmp2 = rng.sleepTime();
					Thread.sleep(tmp2);
					utleie.returnCar(carToRent, cu.getName());
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public static class Utleie {
		private static Lock lock = new ReentrantLock();
		private static Condition returnCar = lock.newCondition();
		private static Car[] carList = new Car[5];
		
		public void create() {
			Integer tmp = 11111;
			for(int i = 0; i < 5; i++){
				Car car = new Car();
				car.setRegNr(tmp);
				carList[i] = car;
				tmp += 11111;
			}
			for(Car c : carList){
				System.out.println("Reg nr is: " + c.toString());
			}
		}
		
		public void rentCar(int carToRent, String name){
			lock.lock();
			try {
				if(carList[carToRent].getRented() == true){
					System.out.println("Car not available");
					returnCar.await();
				} else {
					System.out.println(carList[carToRent].toString()
							+ " Rented by: " + name);
					carList[carToRent].setRented(true);;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
		public void returnCar(int carToRent, String name){
			lock.lock();
			try {
				if(carList[carToRent].getRented() == true){
					System.out.println("\t\t\t\t" + name + " returned car: " 
							+ carList[carToRent].toString());
					carList[carToRent].setRented(false);
					returnCar.signal();
				} else {

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
