package app.team21.risk.mapmodule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;


/**
 * Last Updated on: 18-10-2018, Thursday
 * This class file allows user to create and edit maps and save according to the file format of the map.
 * 
 * @author Yash Sheth
 * @version 1.0.0
 */
public class MapEditor {
	/**
     * This method will add country in existing Map
     *
     * @param country     the country that you want to add
     * @param map_elements     current map details
     * @param neighbour_list  List of neighbour_country
     */
    public void addCountry(Country country, MapElements map_elements, List<Country> neighbour_list) {
        if (country != null && !neighbour_list.isEmpty()) {
        	map_elements.getCountryNeighboursMap().put(country, neighbour_list); // country added to existing game details map
            // Updating neighbour list
            for (Country neighbour : neighbour_list) {
                addNeighbour(neighbour.getCountryName(), map_elements, country);
            }
        }
    }

    /**
     * This method will add neighbour to Country
     *
     * @param country_name country name where neighbours to be added
     * @param map_elements current map details
     * @param neighbour_country neighbour country to be added.
     */
    public void addNeighbour(String country_name, MapElements map_elements, Country neighbour_country) {
        if (!country_name.isEmpty() && country_name!=null && !country_name.equals("")) {
            Country country = new Country(country_name);
            if (map_elements.getCountryNeighboursMap().containsKey(country)) {
            	map_elements.getCountryNeighboursMap().get(country).add(neighbour_country);
            }
        }
    }
    
	
    /**
     * @param continent
     * @param map_elements
     */
    public void addContinent(Continent continent, MapElements map_elements) {
        if (continent != null && continent.getControlValue()>=0) {
        	map_elements.getContinentList().add(continent);
        	map_elements.getContinentCountryMap().put(continent, continent.getMemberCountriesList());        	
        }
    }
    
    /**
     * @param continent
     */
    public boolean validateContinent(MapElements map_element){
    	boolean result=true;
    	for(Continent c:map_element.getContinentList())
    		if(!(c.getMemberCountriesList().size()>0)){
    			result=false;
    			break;
    		}
    		else
    			continue;
    	return result;
    }
    
    /**
     * @param continent
     */
    public boolean validateCountry(MapElements map_element){
    	boolean result=true;
    	for(Country c:map_element.getCountries())
    		if(!(c.getNeighbourNodes().size()>0)){
    			result=false;
    			break;
    		}
    		else
    			continue;
    	return result;
    }
    
    /**
     * This method will create .map file based on input provided from user
     *
     * @param map_elements Details provided from the user
     * @param file_name Map file name that user wants to give
     */
    
    public void writeMap(MapElements map_elements, String file_name) {
        StringBuilder maps = new StringBuilder("[Map]\n");
        for (Map.Entry<String, String> entry : map_elements.getMapDetail().entrySet()) {
            maps.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
        }
        maps.append("\n\n");

        StringBuilder continents = new StringBuilder("[Continents]\n");
        System.out.println("Print Status: this is the size of continents:" + map_elements.getContinentList().size());
        for (Continent continent : map_elements.getContinentList()) {
            continents.append(continent.continent_name).append("=").append(continent.control_value).append("\n");
        }
        continents.append("\n");

        StringBuilder territories = new StringBuilder("[Territories]\n");

        // loop of graphMap
        Iterator<Map.Entry<Country, List<Country>>> it = map_elements.getCountryNeighboursMap().entrySet().iterator();
        int start_pixel = 45;
        int end_pixel = 60;
        while (it.hasNext()) {
            Map.Entry<Country, List<Country>> pair = it.next();
            // get country object
            Country key_country = pair.getKey();
            // get list of the neighbours
            List<Country> nei_country_list = pair.getValue();

            // index of the country from the all countries of all continents
            // list
            start_pixel += 10;
            end_pixel += 10;
            // get values of each country object
            territories.append(key_country.country_name).append(",").append(start_pixel).append(",").append(end_pixel).append(",").append(key_country.getBelongsToContinent());

            // get the index value of the neighbour
            for (Country c : nei_country_list) {
                territories.append(",").append(c.country_name);
            }
            territories.append("\n");
        }

        String result = maps + continents.toString() + territories;

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file_name)))) {
            out.print(result);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
