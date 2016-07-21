package SearchingEarthquakeDataStarterProgram;


/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>(quakeData);
        // TO DO
        ArrayList<QuakeEntry> realAnswer = new ArrayList<QuakeEntry>();
        for (int j = 0; j<howMany; j++)
        {
            QuakeEntry minDist = answer.get(0);
            for (int i = 1; i< answer.size(); i++)
            {
                if (answer.get(i).getLocation().distanceTo(current)<minDist.getLocation().distanceTo(current))
                {
                    minDist = answer.get(i);
                }
            }
            realAnswer.add(minDist);
            answer.remove(minDist);
        }
        return realAnswer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
