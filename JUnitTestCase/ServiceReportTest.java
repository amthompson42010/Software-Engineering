package team7.chocAn.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import team7.chocAn.*;

import org.junit.Before;
import org.junit.Test;

public class ServiceReportTest {


	String date = "12/01/2014";
	int memNum = 100000001;
	int provNum = 500000001;
	int servCode = 700001;
	String comment = "Service was provided";
	
	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException, IOException {
	BillingData billing = new BillingData();
		billing.writeServiceReport(date, memNum, provNum, servCode, comment);
	}
	
	@Test
	public void testGetProviderNum() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream input =
				 new ObjectInputStream(new FileInputStream("ServiceReports.dat"));
				
		ArrayList<ServiceReport> allServices = (ArrayList<ServiceReport>) (input.readObject());
		input.close();
		ServiceReport service = allServices.get(0);
		assertEquals(provNum, service.getProviderNum());

	}

}
