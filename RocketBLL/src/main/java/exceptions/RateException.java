package exceptions;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	private String ErrorMsg;
	
	public RateException(String ErrorMsg) {
		super();
		this.ErrorMsg = ErrorMsg;
	}
}
