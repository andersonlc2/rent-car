package com.rentcar.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.rentcar.model.entities.CarRental;
import com.rentcar.model.entities.Vehicle;
import com.rentcar.model.services.BrazilTaxServices;
import com.rentcar.model.services.RentalServices;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
		System.out.println("Enter rental data");
		System.out.println("Car model: ");
		String carModel = sc.nextLine();
		System.out.println("Pickup (dd/MM/yyyy HH:ss): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.println("Return (dd/MM/yyyy HH:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.println("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalServices rentalService = new RentalServices(pricePerDay, pricePerHour, new BrazilTaxServices());
		rentalService.processInvoice(carRental);
		
		System.out.println("INVOICE");
		System.out.println("Basic payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));
		
		sc.close();

	}

}
