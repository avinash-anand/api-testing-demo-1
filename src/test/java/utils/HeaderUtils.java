package utils;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class HeaderUtils {

    public static Invocation.Builder addHeaders(Invocation.Builder req, HttpMethodType httpMethodType) {
        MultivaluedMap<String, Object> map1 = new MultivaluedHashMap<String, Object>();
        String environment = System.getProperty("environment", "dev");
//        String[] headerArray = PropertyFileReader.getProperty(environment + ".headers").replaceAll("\\s+", "").split(",");
        String[] headerArray = PropertyFileReader.getProperty(environment + ".headers").split(",");
        for (String header : headerArray) {
//            map1.putSingle(header, PropertyFileReader.getProperty(environment + ".header." + httpMethodType + "." + header));
            map1.putSingle(header.trim(), PropertyFileReader.getProperty(environment + ".header." + httpMethodType + "." + header.trim()));
        }
        return req.headers(map1);
    }

}
