package nl.lunatech.assignment.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Airport {

	private int id;
	private String ident;
	private String type;
	private String name;
	private String isoCountry;
	private Set<Runway> runways;

	public Airport(String id, String ident, String type, String name, String iso_country) {
		this.id = Integer.parseInt(id);
		this.ident = ident;
		this.type = type;
		this.name = name;
		this.isoCountry = iso_country;

		this.runways = new TreeSet<Runway>();
	}

	public String toString() {
		return this.id +","+ this.isoCountry + "," + this.name + "," + this.id + "," + this.ident + "," + this.type;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsoCountry() {
		return isoCountry;
	}

	public void setIsoCountry(String isoCountry) {
		this.isoCountry = isoCountry;
	}

	public Set<Runway> getRunways() {
		return runways;
	}

	public void setRunways(Set<Runway> runways) {
		this.runways = runways;
	}

}
