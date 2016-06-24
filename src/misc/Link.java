package misc;
import java.util.ArrayList;

public class Link {
	private String name;
	
	private ArrayList<Link> nextLinks;
	
	public Link(String name) {
		nextLinks = new ArrayList<Link>();
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Link> getNextLinks() {
		return nextLinks;
	}
	
	public void addNextLink(Link l) {
		nextLinks.add(l);
	}
	
	public boolean next() {
		for (Link l : nextLinks) {
			l.next();
		}
		return true;
	}
	
}
