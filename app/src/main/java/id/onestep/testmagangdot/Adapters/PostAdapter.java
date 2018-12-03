package id.onestep.testmagangdot.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.onestep.testmagangdot.Models.Post;
import id.onestep.testmagangdot.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private List<Post> list;
    private PostsItemListener postsItemListener;

    public PostAdapter(Context context, List<Post> list,PostsItemListener postsItemListener) {
        this.context = context;
        this.list = list;
        this.postsItemListener = postsItemListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_posts,viewGroup,false);

     PostAdapter.ViewHolder viewHolder = new PostAdapter.ViewHolder(view, this.postsItemListener);
     return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post item = list.get(i);

        String title = item.getTitle();
        String body = item.getBody();

        viewHolder.txtTitle.setText(title);
        viewHolder.txtBody.setText(body);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private Post getItem(int adapterPosition) {
        return list.get(adapterPosition);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtBody)
        TextView txtBody;

        PostsItemListener postsItemListener;
        public ViewHolder(@NonNull View v,PostsItemListener postsItemListener) {
            super(v);
            ButterKnife.bind(this,v);

            this.postsItemListener = postsItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Post post = getItem(getAdapterPosition());
            this.postsItemListener.onPostClick(post);

            notifyDataSetChanged();
        }
    }

    public interface PostsItemListener{
        void onPostClick(Post post);
    }
}
