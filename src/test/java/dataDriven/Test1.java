package dataDriven;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 {
	
	
	
	//@DataProvider
	// 2, 5 = 7
	// 3, 11 = 14
	// 5, 4 = 9
	

	
	
	@Test(dataProvider = "invalidDataType")
	public void test(int num1, int num2, int expectingTotal) {
		
		int actualTotal = num1+num2;
		
		Assert.assertEquals(actualTotal, expectingTotal, "calculation is wrong");
	}
	

	
	@DataProvider
	public Object[][] dataSet() {
		Object[][] calculation = {
				{2,5,7},
				{3,11,14},
				{5,4,9}
		};
		return calculation;
	}
	
	@DataProvider(name="invalidData")
	public Object[][] dataSet2() {
				{2,5},
				{3,11},
				{5,4}
		};
		return calculation;
	}
	
	@DataProvider(name="invalidDataType")
	public Object[][] dataSet3() {
		Object[][] calculation = {
				{2,5,"8"},
				{3,11,"34"},
				{5,4,"23"}
		};
		return calculation;
	}
	
	

}
