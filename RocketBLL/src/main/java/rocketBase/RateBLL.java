package rocketBase;

import org.apache.poi.ss.formula.functions.*;

import exceptions.IncomeException;
import exceptions.RateException;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	private static RateDomainModel Rate = new RateDomainModel();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		double interestRate = 0;
		
		for (RateDomainModel value : RateDAL.getAllRates()) {
			if (GivenCreditScore >= value.getiMinCreditScore()) {
				interestRate = value.getdInterestRate();
			}
		}
		
		if (interestRate == 0) {
			throw new RateException("No Rate Found");
		}
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return interestRate;
		
		
	}
	
	public static boolean AcceptableIncome (LoanRequest lq) throws IncomeException {
		boolean AI = true;
		if (((lq.getIncome()/12) * .28) < lq.getdPayment()) {
			AI = false;
		}
		if ((((lq.getIncome()/12)-(lq.getExpenses())*.36) < lq.getdPayment())) {
			AI = false;
		}
		if (AI == false) {
			throw new IncomeException("Income is not high enough");
		}
		return AI;
	}
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t) * -1;
	}
}
