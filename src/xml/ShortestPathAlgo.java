package xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ShortestPathAlgo {
	HashMap pairs = new HashMap<String, String>();
	Set<String> seen = new HashSet<String>();
	ArrayList<String> lowPath = new ArrayList(); 
	
	public static void findBestPath(start, end){
		mapPairs(start);
	}
	
	public void mapPairs(init){
		seen.add(init);
	}
	public static void main(String[] args) {
		
	}

}
