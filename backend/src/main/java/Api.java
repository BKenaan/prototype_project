
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Api {

    private static final String BASE_URL = "https://world.openfoodfacts.org/api/v2";

    public static String getProducts() {
        try {
            HttpResponse<String> response = Unirest.get(BASE_URL + "/search")
                    .header("accept", "application/json")
                    .queryString("fields", "code,product_name")
                    .queryString("page_size", "50")
                    .asString();

            if (response.getStatus() == 200) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to fetch products: " + response.getStatusText());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching products: " + e.getMessage(), e);
        }
    }
}
