import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightSchedule {
	public static void main(String[] args) {

		ArrayList<Flight> flights = new ArrayList<>();
		ArrayList<Airport> airports = new ArrayList<>();

		InputStream ins = null;
		Reader r = null;
		BufferedReader br = null;
		try {
			String s;
			ins = new FileInputStream("FlightList.txt");
			r = new InputStreamReader(ins, "UTF-8");
			br = new BufferedReader(r);
			while ((s = br.readLine()) != null) {
				String[] tokens = s.split("\\|");
				if (tokens.length != 4) {
					continue;
				}
				for (int i = 0; i < tokens.length; ++i) {
					tokens[i] = tokens[i].trim();
				}
				String[] name_date = tokens[0].split(" ");
				Flight f = new Flight(name_date[0], name_date[1]);
				for (int i = 1; i < tokens.length; ++i) {
					boolean isFound = false;
					Airport a = null;
					for (int j = 0; j < airports.size(); ++j) {
						a = airports.get(j);
						if (a.getName().equals(tokens[i])) {
							isFound = true;
							break;
						}
					}
					if (!isFound) {
						a = new Airport(tokens[i]);
						a.addFlight(f);
						f.addAirport(a);
						airports.add(a);
					} else {
						a.addFlight(f);
						f.addAirport(a);
						airports.add(a);
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			if (r != null) {
				try {
					r.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the name of an airport: ");
			String input = sc.next().trim();
			if (input.equals("exit")) {
				break;
			}
			boolean isValid = false;
			for (int i = 0; i < airports.size(); ++i) {
				Airport a = airports.get(i);
				if (a.getName().equals(input)) {
					System.out.println(a.toString());
					isValid = true;
					break;
				}
			}
			if (!isValid) {
				System.out.println("This is not a valid airport");
			}
		}
	}
}