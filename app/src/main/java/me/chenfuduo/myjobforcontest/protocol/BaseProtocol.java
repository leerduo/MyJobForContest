package me.chenfuduo.myjobforcontest.protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import me.chenfuduo.myjobforcontest.http.HttpHelper;
import me.chenfuduo.myjobforcontest.utils.FileUtils;
import me.chenfuduo.myjobforcontest.utils.IOUtils;
import me.chenfuduo.myjobforcontest.utils.LogUtils;
import me.chenfuduo.myjobforcontest.utils.StringUtils;

/**
 * Created by chenfuduo on 2015/10/9.
 */
public abstract class BaseProtocol<T> {

    //科大   "http://mobile.haitou.cc/client3/info?ver=1.0&school=47&part=hf&source=xjh&page=1"

    //工大   "http://mobile.haitou.cc/client3/info?ver=1.0&school=49&part=hf&source=xjh&page=1"

    //安大   "http://mobile.haitou.cc/client3/info?ver=1.0&school=48&part=hf&source=xjh&page=1"

    /** 加载协议 */
    public T load(int index,int page) {
        String json;
        // 1.从本地缓存读取数据，查看缓存时间
        json = loadFromLocal(index,page);
        // 2.如果缓存时间过期，从网络加载
        if (StringUtils.isEmpty(json)) {
            json = loadFromNet(index,page);
            if (json == null) {
                // 网络出错
                return null;
            } else {
                // 3.把数据保存到本地保存到本地
                saveToLocal(json, index,page);
            }
        }
        return parserJson(json);
    }

    /** 从本地加载协议 */
    protected String loadFromLocal(int index,int page) {
        String path = FileUtils.getCacheDir();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path  + "_" + index + "_" + page));
            String line = reader.readLine();// 第一行是时间
            Long time = Long.valueOf(line);
            if (time > System.currentTimeMillis()) {//如果时间未过期
                StringBuilder sb = new StringBuilder();
                String result;
                while ((result = reader.readLine()) != null) {
                    sb.append(result);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        } finally {
            IOUtils.close(reader);
        }
        return null;
    }

    /** 从网络加载协议 */
    protected String loadFromNet(int index,int page) {
        String result = null;

        //科大   "http://mobile.haitou.cc/client3/info?ver=1.0&school=47&part=hf&source=xjh&page=1"

        //工大   "http://mobile.haitou.cc/client3/info?ver=1.0&school=49&part=hf&source=xjh&page=1"

        //安大   "http://mobile.haitou.cc/client3/info?ver=1.0&school=48&part=hf&source=xjh&page=1"

        HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL + "&school=" + index + "&part=hf&source=xjh&page=" + page);
        if (httpResult != null) {
            result = httpResult.getString();
            httpResult.close();
        }
        return result;
    }

    /** 保存到本地 */
    protected void saveToLocal(String str, int index,int page) {
        String path = FileUtils.getCacheDir();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path  + "_" + index + "_" + page));
            long time = System.currentTimeMillis() + 1000 * 60;//先计算出过期时间，写入第一行
            writer.write(time + "\r\n");
            writer.write(str.toCharArray());
            writer.flush();
        } catch (Exception e) {
            LogUtils.e(e);
        } finally {
            IOUtils.close(writer);
        }
    }



    /**
     * 解析json
     * @param json
     * @return
     */
    protected abstract T parserJson(String json);



}
