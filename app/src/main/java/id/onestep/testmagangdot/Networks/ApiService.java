package id.onestep.testmagangdot.Networks;

import java.util.List;

import id.onestep.testmagangdot.Models.Comment;
import id.onestep.testmagangdot.Models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/posts")
    Call <List<Post>> getPosts();

    @GET("/posts/{id_post}/comments")
    Call <List<Comment>> getComment(@Path("id_post") int id_post);
}
