package championships.results;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import championships.results.Participant;
import championships.results.ranking.*;

public interface Results {
	public void addResult(String event, Participant participant, int place);
	public void addResult(String event, String name, String nation, int place);
	public List<Participant> getResultsOf(String event);
	public Map<String,List<Participant>> getResultsOfAll();
	public Ranking<Medals> rankNationsByGoldFirst();
	public Ranking<Integer> rankNationsByTotalMedals();
	public void readFromFile(String filename) throws FileNotFoundException;
}