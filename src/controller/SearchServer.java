package controller;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import model.Search;
import model.SearchQuery;

public class SearchServer {
	
	public static void main(String args[]) {
		
		try {
			
			Search obj = new SearchQuery();
			
			LocateRegistry.createRegistry(1900); // set the rmi registry port number to 1900
			
			Naming.rebind("rmi://localhost:1900/producerconsumer", obj); // binds the url localhost and port number to server object
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
