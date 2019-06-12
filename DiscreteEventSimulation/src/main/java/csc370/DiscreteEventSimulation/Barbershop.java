package csc370.DiscreteEventSimulation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Barbershop {

	private static Random rand = new Random();
	private static double Clock = 0;
	private static int custId = 0;
	private static List<Customer> listOfCustomers;
	private static List<Double> waitTimes;

	public void run() {
		listOfCustomers = new ArrayList<Customer>();
		waitTimes = new ArrayList<Double>();
		boolean invalidList = false;
		do {

			Customer customer = newCustomer();
			if (custId > 11) {
				invalidList = false;
				System.out.println("\nMean Wait Time: " + findMeanWaitTime(waitTimes));
				break;
			}
			if (listOfCustomers.size() > 1) {
				invalidList = false;
				if (listOfCustomers.get(0).getServiceTime() > listOfCustomers.get(1).getArrivalTime()) {
					CustomerArrives(listOfCustomers.get(1));
					double waitTime = Clock;
					CustomerDone(listOfCustomers.get(0));
					findWaitTime(waitTime);
					System.out.println("Customer " + listOfCustomers.get(1).getCustId() + " Acquires Barber | Time: "
							+ String.valueOf(Clock));
					listOfCustomers.remove(0);
					invalidList = true;

				} else {
					CustomerDone(listOfCustomers.get(0));
					CustomerArrives(listOfCustomers.get(1));
					System.out.println("Customer " + listOfCustomers.get(1).getCustId() + " Acquires Barber | Time: "
							+ String.valueOf(Clock));
					listOfCustomers.remove(0);
					invalidList = true;
				}
			} else {
				CustomerArrives(customer);
				invalidList = true;
			}

		} while (invalidList);
	}

	public double findMeanWaitTime(List<Double> wait) {
		double sum = 0;
		for (Double w : wait) {
			sum += w;
		}
		return sum / wait.size();
	}

	public void findWaitTime(double arrival) {
		double waitTime = this.Clock - arrival;
 		waitTimes.add(waitTime);
	}

	public void CustomerDone(Customer c) {
		System.out.println(
				"Customer " + c.getCustId() + " Finished Haircut " + "| Time: " + updateClock(c.getServiceTime()));
	}

	public void CustomerArrives(Customer c) {
		System.out.println(
				"Customer " + c.getCustId() + " Arrives at Shop " + "| Time: " + updateClock(c.getArrivalTime()));
	}

	public double findActualTime(int meanTime) {
		return -Math.log(1-rand.nextDouble()) * meanTime;
	}

	public Customer newCustomer() {
		Customer c = new Customer(findActualTime(20), findActualTime(15), custId);
		listOfCustomers.add(c);
		custId++;
		return c;
	}

	public double updateClock(double d) {
		Clock = Clock + d;
		return Clock;
	}
}
