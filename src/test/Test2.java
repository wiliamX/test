package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;
import org.uncommons.reportng.*;

public class Test2 {

	public static void main(String[] args) throws Exception{
		TestNG testNg = new TestNG();
		testNg.setUseDefaultListeners(false);
		HTMLReporter report1 = new HTMLReporter();
		JUnitXMLReporter report2 = new JUnitXMLReporter();
		testNg.addListener(report1);
		testNg.addListener(report2);
		System.setProperty("org.uncommons.reportng.title", "FIRST TEST REPORT");
		List<String> suites = new ArrayList<String>();
		suites.add(System.getProperty("user.dir")+"\\src\\testng.xml");
		testNg.setTestSuites(suites);
		File file = new File(System.getProperty("user.dir")+"\\TestResult");
		if(!(file.exists())){
			file.mkdirs();
		}
		testNg.setOutputDirectory(file.getAbsolutePath());
		testNg.run();
	}
	
}