import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorAPI {
    private String apiKey;

    public ConversorAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasa(String desdeMoneda, String aMoneda) {
        double tasa = 0.0;
        try {
            String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + desdeMoneda;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String responseString = response.toString();

       
            int index = responseString.indexOf("\"" + aMoneda + "\":");
            if (index != -1) {
                String rateStr = responseString.substring(index + aMoneda.length() + 3, responseString.indexOf(",", index));
                tasa = Double.parseDouble(rateStr);
            }

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }
        return tasa;
    }
}
