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
		//start is the beginning
		mapPairs(start);
		ArrayList<String> pathList = new ArrayList();
		pathList.add(start);
		findPaths(start,end,pathList);
	}
	
	//facName is declared in xmlreader.java.... I am thinking
	//that I need to put this into an interface
	public void mapPairs(facName){
		seen.add(facName);
		//should be linkCity is also declared in xmlreader.java 
		//use a loop to get all the neighbors
		for(String linkCity:facName){
			getDistance(linkCity,facName);
			pairs.add(facName, linkCity, distance);
			if(!seen.contains(linkCity)){
				mapPairs(linkCity);
				}	
			return;
			}
		return;
		}
	
	
	private static void findPath(start, end, pathList){
		if(start.equals(end)){
			if(lowPath.empty()){
				lowPath = pathList;
				return;
			}
			else{
				if(sum(pathList.getDistance()) < sum(lowPath.getDistance())){
					lowPath = pathList;
				}
				else{
					return;
				}
			}
		}
		else{
			Set<String> fromHere = new HashSet<String>();
			for(String pairing : pairs){
				if(pairs[0].equals(start)){
					fromHere.add(start);
				}
			}
			for(String pairing : fromHere){
				if(!pathList.contains(pairs.next())){
					ArrayList<String> newPath = pathList.clone();
					newPath.add(pairs.next());
					findPaths(pairs.next(), end, newPath);
				}
				return;
			}
			
		}
	}
	
	public static void getDistance(linkCity, facName){
		int distance = linkCity - facName;
		return distance;
	}
	public static void main(String[] args) {
		
	}

}
