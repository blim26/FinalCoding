package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test(expected = RateException.class)
	public void RateExceptionTest() throws RateException {
		RateBLL.getRate(599);
	}
	
	@Test
	public void getRateTest() throws RateException {
		assertEquals(3.5,RateBLL.getRate(850),.01);
	}
	
	@Test
	public void pmtTest() {
		assertEquals(1432.25,RateBLL.getPayment(.04, 360, 300000, 0, true),.01);
	}
}
