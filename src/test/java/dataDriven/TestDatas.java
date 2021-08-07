package dataDriven;

import java.util.HashMap;

import org.testng.annotations.DataProvider;

import utilityLibrary.ExcelReader;

public class TestDatas {
	
	
	
	@DataProvider(name = "calculationData")
	public Object[][] dataSet() {
		Object[][] calculation = {
				{2,5,7},
				{3,11,14},
				{5,4,9}
		};
		return calculation;
	}
	
	@DataProvider(name = "personInfo")
	public Object[] dataSet2(){
		
		Object[] personInfos = new Object[3];
		
		HashMap<String, String> info = new HashMap<>();
		info.put("firstname", "john");
		info.put("lastName", "Peter");
		info.put("Email", "Peter@gmail.com");
		info.put("phone", "703-666-5686");
		
		HashMap<String, String> info2 = new HashMap<>();
		info2.put("firstname", "Lilly");
		info2.put("lastName", "Wang");
		info2.put("Email", "Lilly@gmail.com");
		info2.put("phone", "703-666-5686");
		
		HashMap<String, String> info3 = new HashMap<>();
		info3.put("firstname", "Pao");
		info3.put("lastName", "Li");
		info3.put("MidleName", "S");
		info3.put("Email", "Pao@gmail.com");
		info3.put("phone", "703-666-5686");
		
		personInfos[0] = info;
		personInfos[1] = info2;
		personInfos[2] = info3;
		
		return personInfos;
	}
	
	
	@DataProvider(name = "personInfo")
	public Object[] dataSet3(){
		
		Object[] personInfos = new Object[3];
		
		ExcelReader er = new ExcelReader();
		
		personInfos[0] =  er.readTestData(1);
		personInfos[1] =  er.readTestData(2);
		personInfos[2] =  er.readTestData(3);

		
		return personInfos;
	}
	
	

}
