package id.onestep.testmagangdot.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.onestep.testmagangdot.Adapters.CommentAdapter;
import id.onestep.testmagangdot.Models.Comment;
import id.onestep.testmagangdot.Models.Post;
import id.onestep.testmagangdot.Networks.ApiService;
import id.onestep.testmagangdot.Networks.UtilsApi;
import id.onestep.testmagangdot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPostActivity extends AppCompatActivity {
    ApiService mApiService;
    CommentAdapter mCommentAdapter;

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtBody)
    TextView txtBody;
    @BindView(R.id.rvComment)
    RecyclerView rvComment;

    Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        ButterKnife.bind(this);

        post = (Post) getIntent().getSerializableExtra("post");
        mApiService = UtilsApi.getApiService();

        txtTitle.setText(post.getTitle());
        txtBody.setText(post.getBody());
        Toast.makeText(this, "post id = "+String.valueOf(post.getId()), Toast.LENGTH_SHORT).show();

        getComment(post.getId());

    }

    private void getComment(int id_post){
        Call<List<Comment>> call = mApiService.getComment(id_post);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }

    private void generateDataList(List<Comment> list) {
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setItemAnimator(new DefaultItemAnimator());
        rvComment.setHasFixedSize(true);

        mCommentAdapter = new CommentAdapter(this, list);

        rvComment.setAdapter(mCommentAdapter);
    }

}
