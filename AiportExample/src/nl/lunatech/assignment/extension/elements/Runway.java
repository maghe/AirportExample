package nl.lunatech.assignment.extension.elements;

public class Runway {

	private int id;
	private String airport_ref;
	private String airport_ident;
	private String lenght_ft;
	private String width_ft;
	private String surface;

	public Runway(String id, String airport_ref, String airport_ident, String lenght_ft, String width_ft,
			String surface) {
		this.id = Integer.parseInt(id);
		this.airport_ref = airport_ref;
		this.airport_ident = airport_ident;
		this.lenght_ft = lenght_ft;
		this.width_ft = width_ft;
		this.surface = surface;
	}

	public String toString() {
		return id + "," + airport_ref + "," + airport_ident + "," + lenght_ft + "," + width_ft + "," + surface;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAirport_ref() {
		return airport_ref;
	}

	public void setAirport_ref(String airport_ref) {
		this.airport_ref = airport_ref;
	}

	public String getAirport_ident() {
		return airport_ident;
	}

	public void setAirport_ident(String airport_ident) {
		this.airport_ident = airport_ident;
	}

	public String getLenght_ft() {
		return lenght_ft;
	}

	public void setLenght_ft(String lenght_ft) {
		this.lenght_ft = lenght_ft;
	}

	public String getWidth_ft() {
		return width_ft;
	}

	public void setWidth_ft(String width_ft) {
		this.width_ft = width_ft;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}
}
