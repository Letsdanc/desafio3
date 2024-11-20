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
            // URL de la API con tu API Key y las monedas solicitadas
            String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + desdeMoneda;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Extraer la tasa de cambio del JSON
            String responseString = response.toString();

            // Buscar la tasa de cambio para la moneda de destino en la respuesta
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
