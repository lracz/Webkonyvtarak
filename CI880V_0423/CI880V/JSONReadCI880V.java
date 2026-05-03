package CI880V; //

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadCI880V {
    public static void main(String[] args) {

        try (FileReader reader = new FileReader("orarendCI880V.json")) {

            JSONParser jsonParser = new JSONParser();
            JSONObject root = (JSONObject) jsonParser.parse(reader);

            JSONArray orarend = (JSONArray) root.get("ora");


            System.out.println("CI880V Órarend 2026 tavasz\n");

            for (int i = 0; i < orarend.size(); i++) {

                JSONObject ora = (JSONObject) orarend.get(i);


                JSONObject time = (JSONObject) ora.get("idopont");


                System.out.println("tárgy: " + ora.get("targy"));
                System.out.println("időpont: " + time.get("nap") + " " + time.get("tol") + "-" + time.get("ig"));
                System.out.println("helyszín: " + ora.get("helyszin"));
                System.out.println("oktató: " + ora.get("oktato"));
                System.out.println("szak: " + ora.get("szak") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}