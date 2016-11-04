package com.example.administrator.entic;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/31.
 */

public class News extends
        AsyncTask<String,String,String>
{
HttpURLConnection mUrlConnection;
    InputStream mInputStream;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            //获取URL的对象
            URL url = new URL(params[0]);
            Log.e("第二个", "----" + url);
            //打开链接
            mUrlConnection = (HttpURLConnection) url.openConnection();
            Log.e("第二个", "----" + mUrlConnection);
            //获取响应码
            int responseCode = mUrlConnection.getResponseCode();
            Log.e("响应码", "----" + responseCode);
            if (responseCode == 200) {
                //获取一个输入流，读取数据
                mInputStream = mUrlConnection.getInputStream();
                byte[] bytes = new byte[1024];
                int length;
                //字符数缓冲区，用于读取流中的内容
                StringBuffer buffer = new StringBuffer();
                while (((length = mInputStream.read(bytes)) != -1)) {
                    buffer.append(new String(bytes, 0, length));
                    Log.e("新闻列表", "---" + buffer.toString());

                }
                return buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //断开连接
            if (mUrlConnection != null) {
                mUrlConnection.disconnect();
            }
            //关流
            if (mInputStream != null) {
                try {
                    mInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
//        String path=params[0];
//        OkHttpClient client = new OkHttpClient();
//        Request build = new Request.Builder().url(path).build();
//
//        Call call = client.newCall(build);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
               // ResponseBody body = response.body();
//                final String string=body.string();
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
//                        Gson gson=new Gson();
//                        Type type=new TypeToken<MainInfo>(){}.getType();
//                        MainInfo mainInfo = gson.fromJson(string, type);
//
//                ArrayList<NewsInfo> mData=mainInfo.getData();
//                        for (NewsInfo newsInfo : mData) {
//                            Log.e("fffffff",""+mData);
//                        }
//                    }
//                });
////            }
////        });
//        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (null!=s&null!=mOnLoadLister) {
mOnLoadLister.lister(s);
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
    OnLoadLister mOnLoadLister;
    public void setOnLoadLister(OnLoadLister onLoadLister){
        this.mOnLoadLister=onLoadLister;
    }
}
