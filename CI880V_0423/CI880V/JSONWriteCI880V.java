package CI880V;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONWriteCI880V {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("orarendCI880V.json");
             FileWriter writer = new FileWriter("output.txt")) {

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray orarend = (JSONArray) jsonObject.get("ora");

            String cim = "CI880V Órarend 2026 tavasz\n\n";

            System.out.print(cim);
            writer.write(cim);

            for (int i = 0; i < orarend.size(); i++) {

                JSONObject ora = (JSONObject) orarend.get(i);
                JSONObject time = (JSONObject) ora.get("idopont");

                String blokk =
                        "Tárgy: " + ora.get("targy") + "\n" +
                                "Időpont: " + time.get("nap") + " " + time.get("tol") + ":00 - " + time.get("ig") + ":00\n" +
                                "Helyszín: " + ora.get("helyszin") + "\n" +
                                "Oktató: " + ora.get("oktato") + "\n" +
                                "Szak: " + ora.get("szak") + "\n\n";

                System.out.print(blokk);

                writer.write(blokk);
            }

            System.out.println("Az output.txt fájl sikeresen létrehozva!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
