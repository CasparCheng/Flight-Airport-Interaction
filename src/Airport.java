
import java.util.ArrayList;
import java.util.HashSet;

public class Airport {
	private String name;
	private ArrayList<Flight> flights;

	public Airport(String name) {
		this.name = name;
		flights = new ArrayList<>();
	}

	public boolean wasVisitedBy(Flight f) {
		return flights.contains(f);
	}

	public boolean onSameFlight(Airport a) {
		if (a == null) {
			return false;
		}
		if (flights.size() != a.flights.size() || flights.size() == 0) {
			return false;
		}
		final HashSet<Flight> fs1 = new HashSet<>(flights);
		final HashSet<Flight> fs2 = new HashSet<>(a.flights);

		return fs1.equals(fs2);
	}

	public void addFlight(Flight f) {
		if (!flights.contains(f)) {
			flights.add(f);
			f.addAirport(this);
		}
	}

	public boolean equals(Airport a) {
		if (a == null) {
			return false;
		}
		if (!name.equals(a.name)) {
			return false;
		}
		if (flights.size() == 0 && a.flights.size() == 0) {
			return true;
		}

		return onSameFlight(a);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder(name);
		ret.append(" (");
		if (flights.size() > 0) {
			for (int i = 0; i < flights.size() - 1; ++i) {
				ret.append(flights.get(i).getName());
				ret.append(", ");
			}
			ret.append(flights.get(flights.size() - 1).getName());
		}
		ret.append(")");

		return ret.toString();
	}

}
