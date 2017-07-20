package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

    @Rule
    public DynamicPort dynamicPort = new DynamicPort("http.port");
    
	@Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
        System.out.println("Dynamic Port in Test : " + dynamicPort.getNumber() + "\n\n");
		runFlowAndExpect("mavenFlow", "Hello Maven");
    }
    
	 @Test
	 public void retrieveFlightsAddsAppropriateHeader() throws Exception {
		 System.out.println("Dynamic Port in Test 2 : " + dynamicPort.getNumber() + "\n\n");
		 MuleEvent event = runFlow("retrieveFlights");
		 String contentType = event.getMessage().getOutboundProperty("Content_Type");
		 assertEquals("application/json", contentType);
	 }

    @Override
    protected String [] getConfigFiles() {
        return new String[]{"maven-project.xml","global.xml"};
    }

}
