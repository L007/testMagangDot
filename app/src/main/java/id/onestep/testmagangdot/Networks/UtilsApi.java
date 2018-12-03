package id.onestep.testmagangdot.Networks;

public class UtilsApi {
    private static final String BASE_URL_API="http://jsonplaceholder.typicode.com";
    public static ApiService getApiService(){
        return ApiClient.getClient(BASE_URL_API).create(ApiService.class);
    }
}
