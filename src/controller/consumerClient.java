package controller;

import java.rmi.Naming;
import java.util.concurrent.TimeUnit;

import model.Buffer;
import model.Observing;
import model.Search;
import model.heroFactory;

public class consumerClient extends Thread {
	
	   private heroFactory factory = new heroFactory();
	   private Observing observe = new Observing("", null);
	   private Buffer sharedLocation;
	   
	   
	   public consumerClient(Buffer sharedLocation) {
		   this.sharedLocation = sharedLocation; // pass in the shared location
	   }
	
	public void run() {
		observe.registerObserver(factory); // register the factory as the observer
		try {
			Search access =
					(Search)Naming.lookup("rmi://localhost:1900/producerconsumer"); // look for the RMI registry and see if its there in the specified port
			
			for(int i = 0; i < 10; i++) { // deals with 10 villans
			sharedLocation.get(); // waits till villan is created
			String villan = access.getVillan(); // get villan name
			System.out.println("villan name: "+ villan);
			observe.setPresent(villan, access); // set villan as present in observing
			
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
