package id.onestep.testmagangdot.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.onestep.testmagangdot.Adapters.PostAdapter;
import id.onestep.testmagangdot.Models.Post;
import id.onestep.testmagangdot.Networks.ApiClient;
import id.onestep.testmagangdot.Networks.ApiService;
import id.onestep.testmagangdot.Networks.UtilsApi;
import id.onestep.testmagangdot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiService mApiService;
    PostAdapter mPostAdapter;

    List<Post> postList = new ArrayList<>();

    @BindView(R.id.rvPost)
    RecyclerView rvPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mApiService = UtilsApi.getApiService();



        getPost();

    }

    private void getPost(){
       Call<List<Post>> call = mApiService.getPosts();
       call.enqueue(new Callback<List<Post>>() {
           @Override
           public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
               generateDataList(response.body());
           }

           @Override
           public void onFailure(Call<List<Post>> call, Throwable t) {

           }
       });
    }

    private void generateDataList(List<Post> list) {
        rvPost.setLayoutManager(new LinearLayoutManager(this));
        rvPost.setItemAnimator(new DefaultItemAnimator());
        rvPost.setHasFixedSize(true);

        mPostAdapter = new PostAdapter(this, list, new PostAdapter.PostsItemListener() {
            @Override
            public void onPostClick(Post post) {
                Intent i = new Intent(MainActivity.this,DetailPostActivity.class);
                i.putExtra("post",post);
                startActivity(i);
            }
        });

        rvPost.setAdapter(mPostAdapter);
    }
}
