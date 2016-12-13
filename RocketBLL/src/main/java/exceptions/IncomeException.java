package exceptions;

import rocketDomain.RateDomainModel;

public class IncomeException extends Exception {
	
	private String Error;
	
	public IncomeException(String Error) {
		super();
		this.Error = Error;
	}

}
