package championships;
import java.io.FileNotFoundException;
import championships.results.*;
import championships.results.ranking.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			test();
			Results results = Factory.createResults();
			results.readFromFile(args[0]);
			results.rankNationsByGoldFirst().printRankingToFile("goldFirstOutput.txt");
			results.rankNationsByTotalMedals().printRankingToFile("totalMedalsOutput.txt");			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	public static void test() {
		Results results = Factory.createResults();
		Ranking <Medals> ranking = results.rankNationsByGoldFirst();
		//Ranking <Integer> ranking = results.rankNationsByTotalMedals();

		System.out.println(ranking.getRanking()); // []

		results.addResult("gyors 50m", "x", "Magyarország", 1);

		System.out.println(ranking.getRanking()); // [Magyarország]

		results.addResult("gyors 50m", "y", "USA", 2);
		results.addResult("gyors 50m", "z", "USA", 3);
		results.addResult("gyors 100m", "y", "Kína", 1);
		results.addResult("gyors 100m", "z", "Kína", 3);

		System.out.println(ranking.getPointsOf("Kína")); //  <1, 0, 1>
		System.out.println(ranking.getPointsOf("Magyarország")); //  <1, 0, 0>
		System.out.println(ranking.getPointsOf("USA")); //  <0, 1, 1>
		System.out.println(ranking.getRanking()); // [Kína, Magyarország, USA]
	}
}