/**
 * 
 */
package app.team21.risk.test.maps;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.test.utils.TestUtils;

/**
 * @author Mahy Salama and Yash Sheth
 *
 */
public class TestControlValue{
	private MapElements elements;//gamemap
    private MapLoader loader;//mapmodel
    private String filePath;
    
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapElements.getInstance();
    	filePath = TestUtils.FILE_PATH + "/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/Testfiles/";	
    }
    
    @Test
    public void testControlValue() throws Exception {
       elements = loader.readMapFile(filePath + "test_control_values.map");
        assertEquals(false, elements.isCorrectMap());
    }
}