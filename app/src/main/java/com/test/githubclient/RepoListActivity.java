package com.test.githubclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.githubclient.adapter.RepositoryAdapter;
import com.test.githubclient.interfaces.RecycleViewOnClickListenerHack;
import com.test.githubclient.model.Repository;

import java.util.ArrayList;

public class RepoListActivity extends AppCompatActivity implements RecycleViewOnClickListenerHack {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.repo_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        loadListOfRepositorys();

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadListOfRepositorys() {
        ArrayList<Repository> testList = new ArrayList<>();
        Repository repository1 = new Repository();
        repository1.setTitle1("Repositorio1");
        repository1.setTitle2("Subtitulo1");

        Repository repository2 = new Repository();
        repository2.setTitle1("Repositorio2");
        repository2.setTitle2("Subtitulo2");

        testList.add(repository1);
        testList.add(repository2);

        RepositoryAdapter repoAdapter = new RepositoryAdapter(testList, this);
        repoAdapter.setmRecycleViewOnClickListenerHack(this);
        LinearLayoutManager lln = new LinearLayoutManager(this);
        lln.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lln);
        recyclerView.setAdapter(repoAdapter);
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(this, "Posicao: " + position,  Toast.LENGTH_SHORT).show();
    }
}
