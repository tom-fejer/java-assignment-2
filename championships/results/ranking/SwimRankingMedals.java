package championships.results.ranking;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import championships.results.Participant;

public class SwimRankingMedals implements Ranking<Medals> {
	private HashMap<String, ArrayList<Participant>> results;

	public SwimRankingMedals(HashMap<String, ArrayList<Participant>> results) {
		this.results = results;
	}

	public Set<String> getNations() {
		HashSet<String> nations = new HashSet<String>();
		for (Map.Entry<String, ArrayList<Participant>> entry : this.results.entrySet()) {
			for (Participant p : entry.getValue()) {
				nations.add(p.getNation());
			}
		}
		return nations;
	}

	public Medals getPointsOf(String nation) {
		Medals points;
		int[] medals = new int[3];
		for (Map.Entry<String, ArrayList<Participant>> entry : this.results.entrySet()) {
			for (int i = 0; i < 3; ++i) {
				if (entry.getValue().size() > i) {
					if (entry.getValue().get(i) != null) {
						if (entry.getValue().get(i).getNation().equals(nation)) {
							medals[i]++;
						}
					}
				}
			}
		}
		points = new SwimMedals(medals[0], medals[1], medals[2]);
		return points;
	}

	public Map<String, Medals> getPointsOfAll() {
		Map<String, Medals> points = new HashMap<String, Medals>();
		for (ArrayList<Participant> participants : this.results.values()) {
			for (Participant p : participants) {
				if (p != null) {
					if (!points.containsKey(p.getNation())) {
						points.put(p.getNation(), getPointsOf(p.getNation()));
					}
				}
			}
		}
		return points;
	}

	public List<String> getRanking() {
		List<String> rankList = new ArrayList<String>();
		List<Map.Entry<String, Medals>> list = new ArrayList<>(getPointsOfAll().entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Medals>>() {
			@Override
			public int compare(Map.Entry<String, Medals> e1, Map.Entry<String, Medals> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		for (Map.Entry<String, Medals> entry : list) {
			rankList.add(entry.getKey());
		}
		Collections.reverse(rankList);
		return rankList;
	}

	public List<String> getTop3() {
		List<String> topThree = new ArrayList<String>();
		for (int i = 0; i < 3; ++i) {
			topThree.set(i, getRanking().get(i));
		}
		return topThree;
	}

	public void printRankingToFile(String filename) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileWriter(filename));
			List<String> ranking = getRanking();
			for (int i = 0; i < ranking.size(); ++i) {
				output.println(ranking.get(i) + ": " + getPointsOf(ranking.get(i)));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			output.close();
		}
	}
}