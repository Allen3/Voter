import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Allen on 2017/6/19.
 */
public class Core {
    private final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; 1503-M02 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.2 TBS/036558 Safari/537.36 MicroMessenger/6.3.25.861 NetType/WIFI Language/zh_CN";

    public static void main(String[] args) throws Exception {


        Core http;
        int cnt = 0;

        while (true) {
            try {
                http = new Core();
                http.sendGet();
                //System.out.println("Request sent: " + cnt);
                cnt++;
            } catch (Exception e) {
                continue;
            } finally {
                Thread.sleep(1000);
            }
        }

    }

    // HTTP GET request

    private void sendGet() throws Exception {

        String url = "http://shx.peopledigital.com.cn/?app_ajax=1&app_act=vote67&id=101&itemid=1";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestProperty("Referer", "http://shx.peopledigital.com.cn/?app_act=detail&id=101&from=singlemessage&isappinstalled=0");
        //con.setRequestProperty("Cookie", "kj_s_id=NGNhYmZkOQNjAuMjU1Ljc0LjE0MDE0OTk0OTkwNzg3NjYMWY1ZGQ1NTA3Njc0MTUyMB");
        con.setRequestProperty("Host", "shx.peopledigital.com.cn");
        con.setRequestProperty("If-Modified-Since", "0");
        con.setRequestProperty("Connection", "keep-alive");

        con.setConnectTimeout(1000);
        con.setReadTimeout(1000);

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}
