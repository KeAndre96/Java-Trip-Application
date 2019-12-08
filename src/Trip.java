/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONString;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONWriter;
/**
 *
 * @author johnn
 */
public class Trip {
    JSONObject tripJson = new JSONObject();
    JSONArray travelers = new JSONArray();
    Scanner sc = new Scanner(System.in);
    private String id;
    private String thankYouNote;

    public Trip(){}
    public void addPackages(String i)
    {   //This requires the use of getting the int value of package
        tripJson.put("packages", "Packages" + i);
    }
    public void addTravelers(Person traveler)
    {
        //travelers.
        tripJson.put("traveler",traveler.getName());
        // System.out.println("Added Traveler");
    }
    public void commitTravelers(){ //insert added travelers into tripJson
        tripJson.put("travelers", travelers);
    }
    public void setState(int i)
    {
        tripJson.put("state",Integer.toString(i));
    }
    public void addAgent(String nameOfAgent)
    {
        tripJson.put("agent", nameOfAgent);
        //state = 1;
        // System.out.println("Added agent");
    }
    public void addAgent(Person person){
        tripJson.put("agent", person.getName());

    }
    public void writeTrip()
    {
        try(FileWriter file = new FileWriter("trips.json"))
        {
            file.write(tripJson.toString());
            file.flush();
            //Files.write(Paths.get("tripsFile"), Trip.toJSONString().getBytes());
        }
        catch(Exception e)
        {
            System.out.println("FileLocation Not Found!");
        }
    }
}
