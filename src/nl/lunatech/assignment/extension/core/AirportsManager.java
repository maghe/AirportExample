package nl.lunatech.assignment.extension.core;

import java.util.List;
import java.util.Map;

import nl.lunatech.assignment.extension.elements.Airport;
import nl.lunatech.assignment.extension.elements.Country;
import nl.lunatech.assignment.extension.gui.AirportSearcherGUI;

public class AirportsManager {
	private Parser parser;
	private AirportSearcherGUI airportSearcherGUI;

	public AirportsManager() {

		parser = new Parser();
		parser.parse();
		
		//MAGHE - to remove
		Map<String, Country> countries = parser.getCountries();
		for (Map.Entry<String, Country> entry : countries.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue().toString());
		}
		
		

		airportSearcherGUI = new AirportSearcherGUI(this);

	}

	public List<Airport> getAirportsDataFromCountryCode(String countryCode) {

		String countryCodeLowerCase = countryCode.toLowerCase();

		Map<String, List<Airport>> airportsMap = parser.getAirportsMap();
		if (airportsMap.containsKey(countryCodeLowerCase)) {
			return airportsMap.get(countryCodeLowerCase);
		}
		return null;

	}

	public List<Airport> getAirportsDataFromCountryName(String countryName) {

		String countryNameLowerCase = countryName.toLowerCase();

		if (parser.getCountries().containsKey(countryNameLowerCase)) {

			//return getAirportsDataFromCountryCode(parser.getCountries().get(countryNameLowerCase));
		}
		return null;
	}
}
