package com.test.githubclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.githubclient.adapter.RepositoryAdapter;
import com.test.githubclient.interfaces.GitHubService;
import com.test.githubclient.interfaces.RecycleViewOnClickListener;
import com.test.githubclient.model.Repo;
import com.test.githubclient.model.RepoDeserializer;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RepoListActivity extends AppCompatActivity implements RecycleViewOnClickListener {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.repo_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        loadListOfRepositorys();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadListOfRepositorys() {

        Gson gson = new GsonBuilder().registerTypeAdapter(Repo.class, new RepoDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<List<Repo>> repositories = gitHubService.listRepos("JonatasAlbano");
        repositories.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Response<List<Repo>> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    List<Repo> repos = response.body();
                    RepositoryAdapter repoAdapter = new RepositoryAdapter(repos, RepoListActivity.this);
                    repoAdapter.setmRecycleViewOnClickListener(RepoListActivity.this);
                    LinearLayoutManager lln = new LinearLayoutManager(RepoListActivity.this);
                    lln.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(lln);
                    recyclerView.setAdapter(repoAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(this, "Posicao: " + position,  Toast.LENGTH_SHORT).show();
    }
}
