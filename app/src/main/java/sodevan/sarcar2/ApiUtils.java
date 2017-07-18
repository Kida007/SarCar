package sodevan.sarcar2;

import sodevan.sarcar2.Remote.RetrofitClient;
import sodevan.sarcar2.Service.NSservice;

/**
 * Created by kartiksharma on 12/04/17.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://api.geonames.org/";
    public static NSservice getNSservice() {
        return RetrofitClient.getClient(BASE_URL).create(NSservice.class);
    }
}
