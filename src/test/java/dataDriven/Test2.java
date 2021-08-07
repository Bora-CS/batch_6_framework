package dataDriven;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {
	
	
	
	@Test(dataProvider = "calculationData" , dataProviderClass = TestDatas.class, enabled = false)
	public void test(int num1, int num2, int expectingTotal) {
		
		int actualTotal = num1+num2;
		
		Assert.assertEquals(actualTotal, expectingTotal, "calculation is wrong");
	}

	@Test(dataProvider = "personInfo", dataProviderClass = TestDatas.class)
	public void test2(HashMap<String, String> info) {
		
		for(String key: info.keySet()) {
			
			// key : value
			System.out.println(key+" : "+info.get(key));
			
		}
		System.out.println("\n\n");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
