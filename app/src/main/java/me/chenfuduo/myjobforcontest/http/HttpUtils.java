package me.chenfuduo.myjobforcontest.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import me.chenfuduo.myjobforcontest.bean.ChatMessage;
import me.chenfuduo.myjobforcontest.bean.Result;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class HttpUtils {
    /**
     * 发起网络请求
     * @param address
     * @param listener
     */
    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if (listener != null){
                        listener.onSuccess(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null){
                        listener.onError(e);
                    }
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }


    private static final String URLSTR = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "28404da63d36c39b90880a484c37aaff";
    private static InputStream is = null;
    private static ByteArrayOutputStream baos = null;


    public static ChatMessage sendMessage(String msg){

        ChatMessage chatMessage = new ChatMessage();

        String jsonRes = doGet(msg);

        Gson gson = new Gson();

        Result result = null;

        try {
            result = gson.fromJson(jsonRes, Result.class);
            chatMessage.setMsg(result.getText());
        } catch (JsonSyntaxException e) {
            chatMessage.setMsg("服务器繁忙，请稍后重试");
        }

        chatMessage.setDate(new Date());

        chatMessage.setType(ChatMessage.Type.INCOMING);

        return chatMessage;
    }





    public static String doGet(String msg) {

        String result = "";

        String url = setParams(msg);

        try {
            URL urlStr = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) urlStr
                    .openConnection();

            conn.setReadTimeout(5000);

            conn.setConnectTimeout(5000);

            conn.setRequestMethod("GET");

            is = conn.getInputStream();

            int len = -1;

            byte[] buf = new byte[128];

            baos = new ByteArrayOutputStream();

            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();
            result = new String(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

    private static String setParams(String msg) {

        String url = null;
        try {
            url = URLSTR + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }


}
