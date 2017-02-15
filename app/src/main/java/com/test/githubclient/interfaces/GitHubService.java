package com.test.githubclient.interfaces;

import com.test.githubclient.model.Repo;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by jonat on 14/02/2017.
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
