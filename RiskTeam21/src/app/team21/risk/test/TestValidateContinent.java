/**
 * 
 */
package app.team21.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.mapmodule.MapEditor;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * @author Harsh Vahani & Yash Sheth
 *
 */
public class TestValidateContinent {
	private MapElements elements;
    private MapLoader loader;
    private String file_path="C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/World.map";    
    private MapEditor editor;    
    
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = loader.readMapFile(file_path);	
    	Continent c=new Continent("Continent 1");
    	List<Continent> new_list = elements.getContinentList();
    	new_list.add(c);
    	elements.setContinentList(new_list);
    	editor = new MapEditor();
    }
    
    @Test
    public void testValidMap() throws Exception {
        boolean result = editor.validateContinent(elements);
    	assertEquals(false, result);
    }
}
