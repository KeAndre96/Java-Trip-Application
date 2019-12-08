import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.json.simple.parser.JSONParser;

public class JSONReader extends Reader{
    public JSONReader(String filename)
    {
        super(filename);
    }
    public void readJSON() throws Exception
    {
        System.out.println("made it here!");
        FileReader reader = new FileReader(this.filename);
        JSONParser jp = new JSONParser();
        jp.parse(reader);
        //JSONObject jo = JSONObject();
    }
}
