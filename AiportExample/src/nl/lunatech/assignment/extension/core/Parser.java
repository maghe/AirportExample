package nl.lunatech.assignment.extension.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import nl.lunatech.assignment.extension.elements.Airport;
import nl.lunatech.assignment.extension.elements.Runway;

public class Parser {

	private String runwayUrl = "resources/runwaysRefined.csv";
	private String airportUrl = "resources/airportsRefined.csv";
	private String countryUrl = "resources/countriesRefined.csv";

	private Map<String, Set<Runway>> runways;
	private Map<String, List<Airport>> airports;
	private Map<String, String> countries;

	private Comparator treeComparator = new Comparator<Runway>() {

		@Override
		public int compare(Runway runway01, Runway runway02) {
			Integer id01 = runway01.getId();
			Integer id02 = runway02.getId();

			return id01.compareTo(id02);

		}
	};

	private final String cvsSplitBy = ",";

	public Parser() {
		runways = new HashMap<String, Set<Runway>>();
		airports = new HashMap<String, List<Airport>>();
		countries = new HashMap<String, String>();

	}

	public void parse() {

		parseRunways(runwayUrl);

		parseAirports(airportUrl);

		parseCountries(countryUrl);

	}

	private void parseCountries(String countriesUrl) {

		String csvFile = countriesUrl;
		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();// to skip the first line
			while ((line = br.readLine()) != null) {

				line = inputRefinement(line);
				String[] contriesArray = line.split(cvsSplitBy);

				if (!countries.containsKey(contriesArray[2])) {
					countries.put(contriesArray[2], contriesArray[1]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void parseAirports(String airportsUrl) {
		String csvFile = airportsUrl;
		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();// to skip the first line
			while ((line = br.readLine()) != null) {

				line = inputRefinement(line);
				String[] airportsArray = line.split(cvsSplitBy);

				Airport airport = new Airport(airportsArray[0], airportsArray[1], airportsArray[2], airportsArray[3],
						airportsArray[8]);

				if (this.runways.containsKey(airport.getIdent())) {
					airport.setRunways(this.runways.get(airport.getIdent()));
				} else {
					airport.setRunways(new TreeSet<Runway>());
				}

				if (!airports.containsKey(airport.getIsoCountry())) {
					List<Airport> list = new ArrayList<Airport>();
					list.add(airport);
					airports.put(airport.getIsoCountry(), list);
				} else {
					airports.get(airport.getIsoCountry()).add(airport);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void parseRunways(String runwaysUrl) {

		String csvFile = runwaysUrl;
		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();// to skip the first line
			while ((line = br.readLine()) != null) {

				line = inputRefinement(line);

				// use comma as separator
				String[] runwayArray = line.split(cvsSplitBy);

				Runway runway = new Runway(runwayArray[0], runwayArray[1], runwayArray[2], runwayArray[3],
						runwayArray[4], runwayArray[5]);

				if (!runways.containsKey(runway.getAirport_ident())) {
					Set<Runway> set = new TreeSet<Runway>(treeComparator);
					set.add(runway);
					runways.put(runway.getAirport_ident(), set);
				} else {
					runways.get(runway.getAirport_ident()).add(runway);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String inputRefinement(String line) {
		return line.toLowerCase().replace("\"", "");
	}

	public Map<String, Set<Runway>> getRunwaysMap() {
		return runways;
	}

	public void setRunwaysMap(Map<String, Set<Runway>> runwaysMap) {
		this.runways = runwaysMap;
	}

	public Map<String, List<Airport>> getAirportsMap() {
		return airports;
	}

	public void setAirportsMap(Map<String, List<Airport>> airportsMap) {
		this.airports = airportsMap;
	}

	public Map<String, String> getCountriesMap() {
		return countries;
	}

	public void setCountriesMap(Map<String, String> countriesMap) {
		this.countries = countriesMap;
	}

}
