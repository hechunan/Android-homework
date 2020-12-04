package com.bytedance.network;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.network.api.testService;
import com.bytedance.network.model.SearchAdapter;
import com.bytedance.network.model.Video;
import com.bytedance.network.model.getInfo;
import com.bytedance.network.model.postInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api-sjtu-camp.bytedance.com/invoke/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v -> {
            try {
                uploadInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(view -> downloadInfo());
    }

    private void downloadInfo() {
        RecyclerView recyclerView=findViewById(R.id.tv);
        SearchAdapter searchAdapter = new SearchAdapter();
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        testService testService = retrofit.create(testService.class);

        testService.get().enqueue(new Callback<getInfo>() {
            @Override public void onResponse(final Call<getInfo> call, final Response<getInfo> response) {
                // 合法性校验
                if (!response.isSuccessful()) {
                    return;
                }

                final List<Video> videoList = response.body().getFeeds();

                if (videoList == null || videoList.isEmpty()) {
                    return;
                }

                List<String> list = new ArrayList<>();
                for (int i = 0; i < videoList.size(); i++) {
                    final Video repo = videoList.get(i);
                    list.add("学生编号：" + repo.getStudentId());
                    list.add("用户姓名：" + repo.getUserName());
                    list.add("图片URL：" + repo.getImageUrl());
                    list.add("视频URL：" + repo.getVideoUrl());
                    list.add("其他说明 ："+repo.getExtraValue());
                }
                searchAdapter.notifyItems(list);
            }

            @Override public void onFailure(final Call<getInfo> call, final Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void uploadInfo() throws IOException {
        // 通过 retrofit 对象的 create() 方法来实例化 java 接口的对象
        // 拿到该接口对象后直接调用相应 api 所对应的方法，并传入对应的参数，获取到 Call<T> 的实例化对象
        // 通过该对象即可向服务器发起网络请求
       testService testService = retrofit.create((testService.class));
//        Call<List<Repo>> call = service.getRepos("JakeWharton");
//        call.enqueue(new Callback<List<Repo>>() {//enqueue指的是异步请求方式
//            @Override public void onResponse(final Call<List<Repo>> call, final Response<List<Repo>> response) {
//                // 合法性校验
//                if (!response.isSuccessful()) {
//                    return;
//                }
//                final List<Repo> repoList = response.body();
//                if (repoList == null || repoList.isEmpty()) {
//                    return;
//                }
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < repoList.size(); i++) {
//                    final Repo repo = repoList.get(i);
//                    stringBuilder.append("仓库名：").append(repo.getName())
//                            .append("\n");
//                }
//                ((TextView) findViewById(R.id.tv)).setText(stringBuilder.toString());
//            }
//
//            @Override public void onFailure(final Call<List<Repo>> call, final Throwable t) {
//                t.printStackTrace();
//            }
//        });
      testService.post("爱与和平","蜻蜓队长","一锤一个",getMultipartFromAsset(),getMultipartFromAsset1()).enqueue(new Callback<postInfo>() {
        @Override
        public void onResponse(Call<postInfo> call, Response<postInfo> response) {
//            if (!response.isSuccessful()) {
//                return;
//            }
            if (response.isSuccessful()) {
                Log.d("TAG", "111");
            } else {
                Log.d("TAG", "!isSuccessful");
            }
        }



        @Override
        public void onFailure(Call<postInfo> call, Throwable t) {
t.printStackTrace();
        }
    });


//    private MultipartBody.Part getMultipartFromAsset1() {
//    }

//    private MultipartBody.Part getMultipartFromAsset() throws IOException {
//        AssetManager assetManager=getAssets();
//        InputStream inputStream=assetManager.open("pic.png");
//        final String partKey="image";
//        final String localFileName="pic.png";
//        RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/feom-data"),toByteArray(inputStream));
//        //MediaType.parse("multipart/feom-data"),toByteArray(inputStream)
//        return MultipartBody.Part.createFormData(partKey,localFileName,requestFile);
//    }

//    private static  byte[] toByteArray(InputStream inputStream) throws IOException {
//        ByteArrayOutputStream output=new ByteArrayOutputStream();
//        byte[] buffer=new byte[4096];
//        int n=0;
//        while(-1!=(n=inputStream.read(buffer))){
//            output.write(buffer,0,n);
//        }
//        return  output.toByteArray();
//    }

//    private void requestAllInfo() {
//        GitHubService service = retrofit.create(GitHubService.class);
//        Call<List<Repo>> repos = service.getRepos("JakeWharton");
//        repos.enqueue(new Callback<List<Repo>>() {
//            @Override public void onResponse(final Call<List<Repo>> call, final Response<List<Repo>> response) {
//                if (!response.isSuccessful()) {
//                    return;
//                }
//                final List<Repo> repoList = response.body();
//                if (repoList == null || repoList.isEmpty()) {
//                    return;
//                }
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < repoList.size(); i++) {
//                    final Repo repo = repoList.get(i);
//                    stringBuilder.append("仓库名：").append(repo.getName())
//                            .append(", 描述：").append(repo.getDescription())
//                            .append(", fork 数量：").append(repo.getForksCount())
//                            .append(", star 数量：").append(repo.getStarsCount())
//                            .append("\n");
//                }
//                ((TextView) findViewById(R.id.tv)).setText(stringBuilder.toString());
//            }
//
//            @Override public void onFailure(final Call<List<Repo>> call, final Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
    }

    private MultipartBody.Part getMultipartFromAsset1() throws IOException {
        AssetManager assetManager=getAssets();
        InputStream inputStream=assetManager.open("video.mp4");
        final String partKey="video";
        final String localFileName="video.mp4";
        RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/form-data"),toByteArray(inputStream));
        //MediaType.parse("multipart/feom-data"),toByteArray(inputStream)
        return MultipartBody.Part.createFormData(partKey,localFileName,requestFile);
    }

    private MultipartBody.Part getMultipartFromAsset() throws IOException {
        AssetManager assetManager=getAssets();
        InputStream inputStream=assetManager.open("pic.png");
        final String partKey="cover_image";
        final String localFileName="pic.png";
        RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/form-data"),toByteArray(inputStream));
        //MediaType.parse("multipart/feom-data"),toByteArray(inputStream)
        return MultipartBody.Part.createFormData(partKey,localFileName,requestFile);
    }

    private byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        byte[] buffer=new byte[4096];
        int n=0;
        while(-1!=(n=inputStream.read(buffer))){
            output.write(buffer,0,n);
        }
        return  output.toByteArray();
    }
}