package controller;

import java.rmi.Naming;
import java.util.concurrent.TimeUnit;

import model.Buffer;
import model.Factory;
import model.Search;

public class producerClient extends Thread {
	
	private Factory factory = new Factory();
	private Buffer sharedLocation;
	
	
	public producerClient(Buffer sharedLocation) {
		this.sharedLocation = sharedLocation;
	}
	
	public void run() {
		String answer;
		try {
			Search access =
					(Search)Naming.lookup("rmi://localhost:1900/producerconsumer"); // look for the RMI registry and see if its there in the specified port
			for(int i = 0; i < 10; i++) {  // creates 10 villans
				
				access.setVillan(factory.orderPerson()); // creates random person villan object
				System.out.println("Villan Created");
				sharedLocation.set(); // waits until villan created has been defeated
				Thread.sleep(5000);
				}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
