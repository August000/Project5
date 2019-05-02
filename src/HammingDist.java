import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HammingDist{
	
	private final int STRING_LENGHT = 4;
	ArrayList<String> stations;
	ArrayList<String> stationsList;
	String s1,s2;
	
	public HammingDist(String firstStation,String secondStation) throws IOException {
		if (firstStation.length() == secondStation.length()) {
			this.s1 = firstStation;
			this.s2 = secondStation;
		}
		else{
			System.out.println("Please insert Strings of equal lenght");
		}
		
		//Initializes all the station from the file in the parameter
		stations = getStations("Mesonet.txt");
		stationsList = new ArrayList<>();
	}
	
	public int calculateHammingDist(String firstStation, String secondStation){
		
		int hammingDist =0;
		
		for(int i = 0;i<4;i++) {
			if(firstStation.charAt(i)!= secondStation.charAt(i)) {
				hammingDist ++;
			}
		}
		
		return hammingDist;
	}
	
	public int[] calculateHammingDist(String stationName, int distance) {
		
		//Initializes the array to store the numbers of stations with a distance of 1,2,3,4
		int[] totalHammingDist = new int[5];
		
		int hammingDist;
		
		for(String currentStation: stations) {
			if(!currentStation.equals(stationName)) {
				hammingDist = 0;
				for(int i = 0;i<STRING_LENGHT;i++) {
					if(currentStation.charAt(i)!= stationName.charAt(i)) {
						hammingDist ++;
					}
				}
				if(hammingDist == distance) {
				    stationsList.add(currentStation);
				}
				switch(hammingDist){
					case 0: totalHammingDist[0]++;
						break;
					case 1: totalHammingDist[1]++;
						break;
					case 2: totalHammingDist[2]++;
						break;
					case 3: totalHammingDist[3]++;
						break;
					case 4: totalHammingDist[4]++;
                    break;
				}
			}
		}
		
		return totalHammingDist;
	}
	
	// getStations return a perfect sized array that return all the stations on a given File 
	public ArrayList<String>  getStations(String fileName) throws IOException {	
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		ArrayList<String>  stationsList = new ArrayList<String>();
		
		String stationLine;
		
		stationLine = br.readLine();
		
		int index = 0;
		while(stationLine!= null) {
		    stationsList.add(stationLine.substring(0, 4)); // Fixed substring range to read from file
			stationLine = br.readLine();
			index++;
		}
		
		br.close();
		
		return stationsList;
	} 
	public ArrayList<String> getStationsList () {
	    return this.stationsList;
	}
	
	public void addStation(String stID) {
	    stationsList.add(stID);
	}
	
	@Override
	public String toString() {
		
		int [] s1Dist= calculateHammingDist(s1,2);
		int [] s2Dist = calculateHammingDist(s2,2);
		
		return String.format("The Hamming Distance of %s and %s: %d.\n"
				+ "Out of 119, for %s, number of nodes are: %d, %d, %d, %d and\n"
				+ "for %s, number of nodes are: %d, %d, %d, %d respectively.", 
				s1,s2,calculateHammingDist(s1,s2), 
				s1,s1Dist[0],s1Dist[1],s1Dist[2],s1Dist[3], 
				s2,s2Dist[0],s2Dist[1],s2Dist[2],s2Dist[3]);
	}
}