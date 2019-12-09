package model;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class SearchQuery extends UnicastRemoteObject implements Search {
	private static int battleNum = 0;
	public SearchQuery() throws RemoteException{
		super();
	}
	
	public void setVillan(person person) throws RemoteException{
		File location = new File("battle-zone.txt");
		while(location.exists()) {
			
		}
		
		//creating battle zone file and writing villan to it
		try(FileWriter fw = new FileWriter("battle-zone.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("\n"+person.getName());
			    System.out.println("Villan added");
			    
			    
			} catch (IOException e) {
				System.out.println(e);
			    
			}
		
		
	}

	@Override
	public String getVillan() throws RemoteException {
		try {
			File location = new File("battle-zone.txt");
			BufferedReader br = new BufferedReader(new FileReader(location));
			String st; 
			  while ((st = br.readLine()) != null) 
			  {
				 
				  String[] parts = st.split(" ");
				 
				  if(parts[0].equals("Bad")) // sees if the villans name contains bad, if so its a villan
				  {
					 
					  br.close();
					  
					  return parts[1]; // returns the rest of the villans name excluding bad
				  }
					  
				  
			  }
			  br.close();
	
		} 
		
		catch (Exception e) { // catches error when reading in from file
			// TODO Auto-generated catch block
			
		}
		return null;
		
		
	}

	@Override
	public void defeatVillan(person person) throws RemoteException {
		
		System.out.println("Writting Hero");
		
		
		try(FileWriter fw = new FileWriter("battle-zone.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
			{
				out.println("\n"+ person.getName()); // writes hero name to the battle-zone file
				System.out.println("Hero added");
		    
		    
			} catch (IOException e) {
				System.out.println(e);
		    
			}
	
		try {
			Files.move //moves the battle-zone file to saved the world again
					(Paths.get("battle-zone.txt"),  
							Paths.get("savedTheWorldAgain\\battle-zone"+battleNum+".txt"));
					System.out.println("File renamed and moved successfully"); 
			battleNum += 1;
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to move the file"); 
		} 
		//Server.needHero = false;
	}
		
		
		
		
}


