/**
 * 
 */
package test.team21.mapmodule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * @author Yash Sheth
 *
 */
public class TestNoContinents {

	private MapElements elements;//gamemap
    private MapLoader loader;//mapmodel
    private String filePath;
	
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapElements.getInstance();
    	filePath = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/Testfiles/";	
    }
    
    @Test
    public void testNoContinents() throws Exception {
    	System.out.println("**************************************\n");
		System.out.println("TestNoContinents");
    	elements = loader.readMapFile(filePath + "no_continents.map");
        assertEquals(false, elements.isCorrectMap());
    }

}