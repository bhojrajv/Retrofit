package com.example.retrofit.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.Model.User;
import com.example.retrofit.R;

import java.util.List;

class RecAddapter extends RecyclerView.Adapter<RecAddapter.Viewholder> {
    private Context context;
    private List<User>users;
    public RecAddapter(FragmentActivity activity, List<User> userlist) {
        this.context=activity;
        this.users=userlist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view=layoutInflater.inflate(R.layout.user_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
           holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        holder.school.setText(users.get(position).getSchool());
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name,email,school;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameuser);
            email=itemView.findViewById(R.id.emailuser);
            school=itemView.findViewById(R.id.schooluser);
        }
    }
}
