/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONString;
import org.json.JSONWriter;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author johnn
 */
public class trip {
    JSONObject Trip = new JSONObject();
    static JSONObject Travelers = new JSONObject();
    public int state;
    JSONArray jArray;
    Scanner sc = new Scanner(System.in);
    public trip()
    {
        state = 1;
    }
    public void addPackages(String packageName)
    {   //This requires the use of getting the int value of package

        Trip.put("packages", "Packages " + packageName);
        //System.out.println("Added Package.");
    }
    public void state(int i)
    {
        Trip.put("state",Integer.toString(i));
    }
    public void addAgent(String nameOfAgent)
    {
        Trip.put("agent", nameOfAgent);
        state = 1;
       // System.out.println("Added agent");
    }
    public void addTravelers(String name)
    {
        Travelers.put("traveler",name);
     // System.out.println("Added Traveler");
    }
    public void addedTravelers(JSONArray j)
    {
       // jArray = new JSONArray();
        //jArray.put(Travelers);
        Trip.put("travelers",j);
    }
    public void writeTrip()
    {
        try(FileWriter file = new FileWriter("trips.json"))
        {
            file.write(Trip.toString());
            file.flush();
        //Files.write(Paths.get("tripsFile"), Trip.toJSONString().getBytes());
        }
        catch(Exception e)
        {
            System.out.println("FileLocation Not Found!");
        }
    }
}