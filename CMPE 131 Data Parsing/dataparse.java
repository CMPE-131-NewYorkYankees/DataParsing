import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class dataparse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String answer = in.next();

        if(answer.equals("-c"))
        {
            String inputFile = "data.txt";
            String outputFile = "data.csv";

            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                FileWriter writer = new FileWriter(outputFile);

                String line = reader.readLine();
                while (line != null) {
                    // Split each line into fields using a delimiter
                    String[] fields = line.split("\t");

                    // Write the fields to the CSV file
                    for (int i = 0; i < fields.length; i++) {
                        writer.append(fields[i]);
                        if (i != fields.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");

                    line = reader.readLine();
                }

                reader.close();
                writer.flush();
                writer.close();

                System.out.println("Conversion complete.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(answer.equals("-j")){
            String inputFile = "data.txt";
            String outputFile = "data.json";

            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                FileWriter writer = new FileWriter(outputFile);

                JSONObject json = new JSONObject();
                JSONArray array = new JSONArray();
                String line = reader.readLine();
                while (line != null) {
                    // Split each line into fields using a delimiter
                    String[] fields = line.split("\t");

                    // Create a JSON object for each line and add it to the array
                    JSONObject obj = new JSONObject();
                    for (int i = 0; i < fields.length; i++) {
                        obj.put("field" + (i+1), fields[i]);
                    }
                    array.put(obj);

                    line = reader.readLine();
                }

                json.put("data", array);
                writer.write(json.toString());

                reader.close();
                writer.flush();
                writer.close();

                System.out.println("Conversion complete.");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }   
        if(answer.equals("-x"))
        {
            String inputFile = "data.txt";
        String outputFile = "data.xml";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            FileWriter writer = new FileWriter(outputFile);

            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlWriter = factory.createXMLStreamWriter(writer);

            xmlWriter.writeStartDocument();
            xmlWriter.writeStartElement("data");

            String line = reader.readLine();
            while (line != null) {
                // Split each line into fields using a delimiter
                String[] fields = line.split("\t");

                // Write the fields to the XML file
                xmlWriter.writeStartElement("record");
                for (int i = 0; i < fields.length; i++) {
                    xmlWriter.writeStartElement("field" + (i+1));
                    xmlWriter.writeCharacters(fields[i]);
                    xmlWriter.writeEndElement();
                }
                xmlWriter.writeEndElement();

                line = reader.readLine();
            }

            xmlWriter.writeEndElement();
            xmlWriter.writeEndDocument();

            reader.close();
            xmlWriter.close();

            System.out.println("Conversion complete.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
    

    }
}
