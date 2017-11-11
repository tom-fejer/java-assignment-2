package championships.results;

public class SwimParticipant implements Participant {
	private String name, nation;

	public SwimParticipant(String name, String nation) {
		if (name != null && name != "" && nation != null && nation != "") {
			this.name = new String(name);
			this.nation = new String(nation);
		}
	}
	public String getName() {
		return new String(name);
	}
	public String getNation() {
		return new String(nation);
	}
}