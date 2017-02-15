package com.test.githubclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.test.githubclient.R;
import com.test.githubclient.interfaces.RecycleViewOnClickListener;
import com.test.githubclient.model.Repo;

import java.util.List;

/**
 * Created by jonat on 09/02/2017.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    private List<Repo> repositories;
    //private LayoutInflater layoutInflater;
    private RecycleViewOnClickListener mRecycleViewOnClickListener;

    public RepositoryAdapter(List<Repo> repositories, Context context) {
        this.repositories = repositories;
        //layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView description;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title1);
            description = (TextView) view.findViewById(R.id.title2);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecycleViewOnClickListener != null)
                mRecycleViewOnClickListener.onClickListener(v, getPosition());
        }
    }

    @Override
    public RepositoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_list_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RepositoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(repositories.get(position).getName());
        holder.description.setText(repositories.get(position).getDescription());

    }

    public void setmRecycleViewOnClickListener(RecycleViewOnClickListener mRecycleViewOnClickListener) {
        this.mRecycleViewOnClickListener = mRecycleViewOnClickListener;
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
