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
    JSONArray packages = new JSONArray();
    Scanner sc = new Scanner(System.in);
    private String id;
    private String thankYouNote;

    public Trip(){}
    public void addPackages(String fromLocation)
    {   //This requires the use of getting the int value of package
        packages.put(fromLocation);
    }
    public void commitPackages(){
        //commit packages list to tripJson
        tripJson.put("packages", packages);
    }
    public void addTravelers(Person traveler)
    {
        travelers.put(traveler.getName());

    }
    public void addPayment(String paymentType, Double amountPaid){
        tripJson.put("paymentType", paymentType);
        tripJson.put("amoutnPaid", amountPaid);
    }
    public void commitTravelers(){ //insert added travelers into tripJson
        tripJson.put("travelers", travelers);
    }
    public void setState(int i)
    {
        tripJson.put("state",Integer.toString(i));
    }
    public void addAgent(Person person){
        tripJson.put("agent", person.getName());

    }
    public void addNote(String note){
        tripJson.put("thankYouNote", note);
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
