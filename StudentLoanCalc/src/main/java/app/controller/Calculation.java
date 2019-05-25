package app.controller;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.apache.poi.ss.formula.functions.Finance;

public class Calculation {
	private double LoanAmount;
	
	private double AdditionalPayment;
	
	private int NbrOfYears;
	
	private double InterestRate;
	
	public Calculation(double LoanAmount, double AdditionalPayment, int NbrOfYears, double InterestRate) {
		this.LoanAmount = LoanAmount;
		this.AdditionalPayment = AdditionalPayment;
		this.NbrOfYears = NbrOfYears;
		this.InterestRate = InterestRate;
	}

	public double getAdditionalPayment() {
		return AdditionalPayment;
	}

	public void setAdditionalPayment(double addPay) {
		AdditionalPayment = addPay;
	}

	public double getNbrOfYears() {
		return NbrOfYears;
	}

	public void setNbrOfYears(int years) {
		NbrOfYears = years;
	}

	public double getInterestRate() {
		return InterestRate;
	}

	public void setInterestRate(double rate) {
		InterestRate = rate;
	}

	public void setLoanAmount(double amount) {
		LoanAmount = amount;
	}
	
	
	public double InterestCalculation(double loan) {
		double rate = InterestRate/12;
		double interest = loan*rate;
		return interest;
	}
	public double PMTCalculation() {
		double r = InterestRate/12;
		double n = NbrOfYears*12;
		double p = LoanAmount;
		double f = 0;
		boolean t = false;
		
		double ConstantPayment = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		return ConstantPayment;
	}
	
	public double TotalPayment() {
		double interest = 0;
		double PPMT = 0;
		double pv = LoanAmount;
		
		while(PPMT + AdditionalPayment < pv) {
			double PMT = PMTCalculation();
			PPMT = PMT - InterestCalculation(pv);
			pv -= PPMT + AdditionalPayment;
			interest += PMT-PPMT;
			System.out.println(PPMT+AdditionalPayment);
		}
		Double FinalInterest = InterestCalculation(pv);
		return interest + LoanAmount + FinalInterest;
	}
	
	public double CalculateTotalInterest() {
		double TotalInterest = TotalPayment() - LoanAmount;
		return TotalInterest;
	}
}
