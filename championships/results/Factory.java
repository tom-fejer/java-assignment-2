package championships.results;

public final class Factory extends Object {
	public static Results createResults() {
		return new SwimResults();
	}
}