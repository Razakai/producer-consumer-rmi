package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Search extends Remote {
	
	public void setVillan(person person) throws RemoteException;
	
	public String getVillan() throws RemoteException;
	
	public void defeatVillan(person person) throws RemoteException;

}
