package championships.results.ranking;
import java.lang.StringBuilder;

public class SwimMedals implements Medals {
	private int numOfGolds, numOfSilvers, numOfBronzes;

	public SwimMedals(int gold, int silver, int bronze) {
		this.numOfGolds   = gold;
		this.numOfSilvers = silver;
		this.numOfBronzes = bronze;
	}

	public int compareTo(Medals obj) {
		int compare = 0;
		// 1. check Golds
		if (this.getGolds() > obj.getGolds()) {
			compare = 1;
		} else if (this.getGolds() < obj.getGolds()) {
			compare = -1;
		} else if (this.getGolds() == obj.getGolds()) {
			// 2. check Silvers
			if (this.getSilvers() > obj.getSilvers()) {
				compare = 1;
			} else if (this.getSilvers() < obj.getSilvers()) {
				compare = -1;
			} else if (this.getSilvers() == obj.getSilvers()) {
				// 3. check Bronzes
				if (this.getBronzes() > obj.getBronzes()) {
					compare = 1;
				} else if (this.getBronzes() < obj.getBronzes()) {
					compare = -1;
				} else if (this.getBronzes() == obj.getBronzes()) {
					compare = 0;
				}
			}
		}
		return compare;
	}

	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof Medals) {
			Medals medal = (Medals) obj;
			res = this != null && medal != null
				&& this.getClass() == medal.getClass()
				&& this.getGolds() == medal.getGolds()
				&& this.getSilvers() == medal.getSilvers()
				&& this.getBronzes() == medal.getBronzes();
		}
		return res;
	}

	public int getGolds() {
		return this.numOfGolds;
	}

	public int getSilvers() {
		return this.numOfSilvers;
	}

	public int getBronzes() {
		return this.numOfBronzes;
	}

	public int hashCode() {
		return numOfGolds*5 + numOfSilvers*6 + numOfBronzes*7;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(this.getGolds());
		sb.append(", ");
		sb.append(this.getSilvers());
		sb.append(", ");
		sb.append(this.getBronzes());
		sb.append(">");
		return sb.toString();
	}	
}