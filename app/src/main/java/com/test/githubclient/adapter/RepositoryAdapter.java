package com.test.githubclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.test.githubclient.R;
import com.test.githubclient.interfaces.RecycleViewOnClickListenerHack;
import com.test.githubclient.model.Repository;

import java.util.List;

/**
 * Created by jonat on 09/02/2017.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    private List<Repository> repositories;
    private LayoutInflater layoutInflater;
    private RecycleViewOnClickListenerHack mRecycleViewOnClickListenerHack;

    public RepositoryAdapter(List<Repository> repositories, Context context) {
        this.repositories = repositories;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title1;
        public TextView title2;

        public ViewHolder(View view) {
            super(view);
            title1 = (TextView) view.findViewById(R.id.title1);
            title2 = (TextView) view.findViewById(R.id.title2);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecycleViewOnClickListenerHack != null)
                mRecycleViewOnClickListenerHack.onClickListener(v, getPosition());
        }
    }

    @Override
    public RepositoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.repository_list_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RepositoryAdapter.ViewHolder holder, int position) {
        holder.title1.setText(repositories.get(position).getTitle1());
        holder.title2.setText(repositories.get(position).getTitle2());

    }

    public void setmRecycleViewOnClickListenerHack(RecycleViewOnClickListenerHack mRecycleViewOnClickListenerHack) {
        this.mRecycleViewOnClickListenerHack = mRecycleViewOnClickListenerHack;
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
