package id.onestep.testmagangdot.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.onestep.testmagangdot.Models.Comment;

import id.onestep.testmagangdot.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<Comment> list;


    public CommentAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_comments, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Comment item = list.get(i);

        String name = item.getName();
        String email = item.getEmail();
        String body = item.getBody();
        //String inisialName = String.valueOf(item.getEmail().charAt(0)).trim().toUpperCase();

        viewHolder.txtName.setText(name);
        viewHolder.txtEmail.setText(email);
        viewHolder.txtBody.setText(body);

        //viewHolder.imgIcon.setText(inisialName);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private Comment getItem(int adapterPosition) {
        return list.get(adapterPosition);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtEmail)
        TextView txtEmail;

        @BindView(R.id.txtBody)
        TextView txtBody;

      /*  @BindView(R.id.iconName)
        Button imgIcon;*/


        public ViewHolder(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);


        }
    }
}
