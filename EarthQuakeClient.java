package SearchingEarthquakeDataStarterProgram;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData)
        {
            if (qe.getMagnitude()>magMin)
            {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData)
        {
            if (qe.getLocation().distanceTo(from)<distMax)
            {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmal.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> stuff = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : stuff)
        {
            System.out.println(qe.toString());
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmal.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : answer)
        {
            System.out.println(qe.getLocation().distanceTo(city) + qe.getInfo());
        }
        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData)
        {
            if (qe.getDepth()>minDepth && qe.getDepth()<maxDepth)
            {
                answer.add(qe);
            }
        }
         return answer;
    }
    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + "quakes");
        ArrayList<QuakeEntry> answer = filterByDepth(list, -10000.0, -5000.0);
        for (QuakeEntry qe : answer)
        {
            System.out.println(qe.toString());
        }
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if (where.equalsIgnoreCase("start"))
        {
            for (QuakeEntry qe : quakeData)
            {
                if (qe.getInfo().indexOf(phrase)==0)
                {
                    answer.add(qe);
                }
            }
        }
        else if (where.equalsIgnoreCase("any"))
        {
            for (QuakeEntry qe : quakeData)
            {
                if (qe.getInfo().indexOf(phrase)!=-1)
                {
                    answer.add(qe);
                }
            }
        }
        else if (where.equalsIgnoreCase("end"))
        {
            for (QuakeEntry qe : quakeData)
            {
                if (qe.getInfo().indexOf(phrase)==qe.getInfo().length()-phrase.length())
                {
                    answer.add(qe);
                }
            }
        }
        return answer;
                
    }
   public void quakesByPhrase()
   {
       EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + "quakes");
        String where = "start";
        String phrase = "Explosion";
       ArrayList<QuakeEntry> answer = filterByPhrase(list, where, phrase);
       for (QuakeEntry qe : answer)
       {
           System.out.println(qe.getInfo());
        }
        System.out.println("Found " + answer.size() + " quakes that match " + phrase + " at " + where);
   }
    
}
