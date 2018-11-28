package gov.js.admin.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gov.js.tools.CommonUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WcfAPI {
    public static WcfResult GroupByCountyTotal(int regionId) {
        try {
            String json = IOUtils.toString(new URL("http://61.159.180.166:8036/Service2.svc/GroupByCountyTotal?regionId="+ regionId), "UTF-8");
            Gson gson = CommonUtils.createGson();
            java.lang.reflect.Type type = new TypeToken<WcfResult>(){}.getType();
            return gson.fromJson(json, type);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}