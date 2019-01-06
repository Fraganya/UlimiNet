package ht.me.fraganya.uliminet;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestHandler {

    public String sendPostRequest(String requestURL, HashMap<String,String> data)
    {
        URL url;
        StringBuilder responseString = new StringBuilder();

        try{
            url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream outStream  = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream,"UTF-8"));

            writer.write(getPostDataString(data));
            writer.flush();
            writer.close();
            outStream.close();

            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                responseString= new StringBuilder();
                String response;

                while((response = reader.readLine()) != null){
                    responseString.append(response);
                }
            }
        }
        catch(Exception e){
            Log.d("NETERROR",e.toString());
            Log.d("NETERROR","GETTING CON ERROR");
            e.printStackTrace();
        }

        Log.d("DBG",responseString.toString());
        return responseString.toString();

    }

    //@brief converts the key,value pair to a query string
    private String getPostDataString (HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder requestQuery = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String,String> entry : params.entrySet())
        {
            if(first){
                first = false;
            }
            else{
                requestQuery.append("&");
            }

            requestQuery.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            requestQuery.append("=");
            requestQuery.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }

        Log.d("DBG",requestQuery.toString());
        return requestQuery.toString();
    }
}
