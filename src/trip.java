/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;
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
public class trip {
    JSONObject Trip = new JSONObject();
    JSONArray Travelers = new JSONArray();
    Scanner sc = new Scanner(System.in);
    public trip()
    {
        
    }
    public void addPackages(int i)
    {   //This requires the use of getting the int value of package
        Trip.put("packages", "Packages" + Integer.toString(i));
    }
    public void addTravelers(String name)
    {
      Trip.put("travelers",name);
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
