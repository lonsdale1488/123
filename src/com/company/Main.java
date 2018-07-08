package com.company;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.security.cert.X509Certificate;

public class Main {

    public static void main(String[] args) {

        disableSSL();
try {String response = getexchang();
 //   System.out.print(response);
    String fin = " ";
    if (response == null)
    {return;}



        writeFile(response);
readFile();

} catch (Exception e) {
    e.printStackTrace();
}

    }
    private static String getexchang() throws Exception {
        String url = "https://exchangeratesapi.io/api/latest";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void disableSSL() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String readFile()  {
        try {
            FileReader reader = new FileReader("Text.txt");
            int c;
            while ((c = reader.read()) != -1) {
               // System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

   private static void writeFile(String  texttowrite)
    {
        try(FileWriter writer = new FileWriter("Text.txt")) {


            int a =0;
    char[] look = texttowrite.toCharArray();
    for (int j = 0; j < look.length; j++) {


        if (look[j] == '{') {
            if (a>0)
        {writer.write("\n");
            for (int g = 0; g < a; g++)
            {
                writer.write("\t");
            }
        }
            a=a+1;
            writer.write(look[j]);
            writer.write("\n");
            for (int g = 0; g < a; g++)
            {
                writer.write("\t");
            }



        }
       else if (look[j] == ',') {
            writer.write(look[j]);
            writer.write("\n");
            for (int g = 0; g < a; g++)
            {
                writer.write("\t");
            }
        }
        else  if (look[j] == '}')
        {
            writer.write("\n");
            a=a-1;
            for (int g = 0; g < a; g++)
            {
                writer.write("\t");
            }
            writer.write(look[j]);

        }
       else { writer.write(look[j]);}
    }




        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
