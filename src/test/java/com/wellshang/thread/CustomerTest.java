package com.wellshang.thread;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

public class CustomerTest {

	Customer customer;

    @Before  
    public void setUp() {
    	customer = PowerMockito.spy(new Customer());
    }

	@Test  
	public void testRun() throws Exception {
		
		PowerMockito.doReturn("AAAAAAAAAAA").when(customer, "getCustomerInfo");
		customer.run();
		
		Assert.assertEquals("AAAAAAAAAAA", customer.getInfo());
//		PowerMockito.verifyPrivate(customer, Mockito.times(1)).invoke("getCustomerInfo"); 
	}
}
