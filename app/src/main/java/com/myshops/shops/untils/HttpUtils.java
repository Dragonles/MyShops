package com.myshops.shops.untils;

import android.content.Context;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.common.util.MD5;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by Vonte on 2015/12/16.
 */
public class HttpUtils {
    public static void httputilsPost(String shorurl,HashMap<String,String> map,final Callback.CommonCallback<String> callback){
        //保存url
        String url = Config.url+shorurl;
        if ("".equals(map)){
            map.put("sign","sunsai123");
        }
        else {
            //map集合排序
            Collection<String> collection = map.keySet();
            Iterator<String> iterator = collection.iterator();
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()){
                list.add(iterator.next());
            }
            Collections.sort(list);
            //http请求码
            String qingqiu = "";
            for(int i = 0;i<map.size();i++){
                qingqiu+=list.get(i)+map.get(list.get(i));
            }
            qingqiu+=Config.siyao;
            //发送http请求
            RequestParams params = new RequestParams(url);
            for(int j=0;j<map.size();j++){
                params.addQueryStringParameter(list.get(j),map.get(list.get(j)));
            }
            map.put("sign", MD5.md5(qingqiu));
            params.addQueryStringParameter("sign",map.get("sign"));
            //返回JSON数据
            int currentapiVersion=android.os.Build.VERSION.SDK_INT;
            Log.i("currentapiVersion",currentapiVersion+"");
            if(currentapiVersion<=20){
                try {
                    SSLContext sslcontext = SSLContext.getInstance("TLSv1");
                    sslcontext.init(null,null, null);
                    SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());
                    params.setSslSocketFactory(NoSSLv3Factory);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            x.http().post(params,callback);
        }



    }
    public static void httputilsGet(String shorurl,HashMap<String,String> map,final Callback.CommonCallback<String> callback){
        //保存url
        String url = Config.url+shorurl;
        //map集合排序
        Collection<String> collection = map.keySet();
        Iterator<String> iterator = collection.iterator();
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        Collections.sort(list);
        //http请求码
        String qingqiu = "";
        for(int i = 0;i<map.size();i++){
            qingqiu+=list.get(i)+map.get(list.get(i));
        }
        qingqiu+=Config.siyao;
        //发送http请求
        RequestParams params = new RequestParams(url);
        for(int j=0;j<map.size();j++){
            params.addQueryStringParameter(list.get(j),map.get(list.get(j)));
        }
        map.put("sign",MD5.md5(qingqiu));
        params.addQueryStringParameter("sign",map.get("sign"));
        //返回JSON数据
        x.http().get(params,callback);

    }

    public static void httpPostImage(String path,String token,String url,Callback.CommonCallback<String> callback){

        RequestParams params = new RequestParams(Config.url+url);
        params.addQueryStringParameter("token",token);
        params.setMethod(HttpMethod.POST);
        params.setMultipart(true);
        params.addBodyParameter("image",new File(path));
        x.http().post(params,callback);




    }

}
