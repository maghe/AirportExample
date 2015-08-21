package nl.lunatech.assignment.extension.elements;

public class Country {

	private int id;
	private String code;
	private String name;
	private String continet;
	private String wikipedia_link;
	private String keywords;

	public Country(String id, String code, String name, String continent, String wikipedia_link, String keywords) {
		this(id, code, name, continent, wikipedia_link);
		this.keywords = keywords;
	}

	public Country(String id, String code, String name, String continent, String wikipedia_link) {
		this.id = Integer.parseInt(id);
		this.code = code;
		this.name = name;
		this.continet = continent;
		this.wikipedia_link = wikipedia_link;
	}
	
	public String toString() {
		return id + "," + code + "," + name + "," + continet + "," + wikipedia_link + "," + keywords;
	}
}