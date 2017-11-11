package championships.results.ranking; 

public interface Medals extends Comparable<Medals> {
	public int 	   compareTo(Medals obj);
	public boolean equals(Object obj);
	public int 	   getBronzes();
	public int 	   getGolds();
	public int 	   getSilvers();
	public int 	   hashCode();
	public String  toString();
}