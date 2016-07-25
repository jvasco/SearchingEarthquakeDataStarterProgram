package SearchingEarthquakeDataStarterProgram;
import java.util.*;


/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes()
    {
       EarthQuakeParser parse = new EarthQuakeParser(); 
       String source = "nov20quakedatasmall.atom";
       ArrayList<QuakeEntry> list = parse.read(source);
       /*for (QuakeEntry qe : list)
       {
           System.out.println(qe.getInfo());
        }
        */
        System.out.println(list.size() + " earthquakes found");
        //indexOfLargest(list);
        ArrayList<QuakeEntry> stuff = getLargest(list, 5);
        for (QuakeEntry qe : stuff)
        {
            System.out.println(qe.getInfo());
        }
    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        QuakeEntry largest = data.get(0);
        int index = 0;
        int largeIndex = 0;
        for (QuakeEntry qe : data)
        {
           
            if (qe.getMagnitude()>largest.getMagnitude())
            {
                largest = qe;
                largeIndex = index;
            }
             index++;
        }
        //System.out.println("Largest quake has index of " + largeIndex + " and has a magnitude of " + data.get(largeIndex).getMagnitude());
        return largeIndex;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        if (quakeData.size()<howMany){
            howMany = quakeData.size();
        }
        ArrayList<QuakeEntry> copy = quakeData;
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i = 0; i<howMany; i++)
        {
            answer.add(copy.get(indexOfLargest(copy)));
            copy.remove(copy.get(indexOfLargest(copy)));
        }
        return answer;
    }
}
