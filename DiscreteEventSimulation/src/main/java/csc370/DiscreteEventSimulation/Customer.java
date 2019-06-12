package csc370.DiscreteEventSimulation;

 

public class Customer { 

	private double arrivalTime;
	private double serviceTime;
	private int custId;
	
	public Customer(double arrival, double service, int cid) {
		this.arrivalTime = arrival;
		this.serviceTime = service;
		this.custId = cid;
	}
	
	
	public double getArrivalTime() {	
		return arrivalTime;
	}
	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public double getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}
	public int getCustId() {
		return custId;
	}


	public void setCustId(int custId) {
		this.custId = custId;
	}
	
}
