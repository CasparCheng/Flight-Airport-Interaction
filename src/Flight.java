
import java.util.ArrayList;

public class Flight {
	private String name;
	private ArrayList<Airport> airports;
	private String date;

	public Flight(String name, String date) {
		this.name = name;
		this.date = date;
		airports = new ArrayList<>();
	}

	public void addAirport(Airport a) {
		if (!airports.contains(a)) {
			airports.add(a);
			a.addFlight(this);
		}
	}

	public boolean equals(Flight f) {
		if (f == null) {
			return false;
		}

		return name.equals(f.name) && date.equals(f.date);
	}

	public ArrayList<Airport> getAirports() {
		return airports;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder(name + ", " + date);
		for (int i = 0; i < airports.size(); ++i) {
			ret.append(System.lineSeparator());
			ret.append(airports.get(i).getName());
		}

		return ret.toString();
	}

}
