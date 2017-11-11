package championships.results;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import championships.results.ranking.*;

public class SwimResults implements Results {
	// index of participant == place
	private HashMap<String, ArrayList<Participant>> results = new HashMap<String, ArrayList<Participant>>();

	public void addResult(String event, Participant participant, int place) throws IllegalArgumentException {
		if (event == null || event == "" || participant == null || place <= 0) {
			throw new IllegalArgumentException();
		} else if (this.results.containsKey(event)) {
			this.results.get(event).ensureCapacity(place); // for performance
			while (this.results.get(event).size() < place) { this.results.get(event).add(null); } // resize to place
			if (this.results.get(event).get(place-1) != null) {
				throw new IllegalArgumentException();
			} else { 
				this.results.get(event).set(place-1, participant);
			}
		} else {
			ArrayList<Participant> participants = new ArrayList<Participant>();
			this.results.put(event, participants); // empty placeholder
			this.results.get(event).ensureCapacity(place); // for performance
			while (this.results.get(event).size() < place) { this.results.get(event).add(null); } // resize to place
			this.results.get(event).set(place-1, participant);
		}
	}

	public void addResult(String event, String name, String nation, int place) throws IllegalArgumentException {
		if (name == null || name == "" || nation == null || nation == "") {
			throw new IllegalArgumentException();
		} else {
			Participant participant = new SwimParticipant(name, nation);
			addResult(event, participant, place);
		}
	}

	public List<Participant> getResultsOf(String event) {
		if (this.results.containsKey(event)) {
			return new ArrayList<Participant>(this.results.get(event));
		} else return new ArrayList<Participant>();
	}

	public Map<String, List<Participant>> getResultsOfAll() {
		return new HashMap<String, List<Participant>>(this.results);
	}

	public Ranking<Medals> rankNationsByGoldFirst() {
		return new SwimRankingMedals(this.results);
	}

	public Ranking<Integer> rankNationsByTotalMedals() {
		return new SwimRankingInteger(this.results);
	}

	public void readFromFile(String filename) throws FileNotFoundException {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(";");
				if (line.length == 4) {
					if (!line[0].equals("") && !line[1].equals("") &&
							!line[2].equals("") && isNumber(line[3])) {
						try {
							addResult(line[0], line[1], line[2], Integer.parseInt(line[3]));
						} catch (IllegalArgumentException e) {}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	// checks if given string is a positive number
	public static boolean isNumber(String str) {
		Integer i = 0;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return (i > 0);
	}
}