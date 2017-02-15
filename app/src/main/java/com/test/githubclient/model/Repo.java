package com.test.githubclient.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jonat on 14/02/2017.
 */

public class Repo implements Serializable{
    @SerializedName("id")
    private int idRep;

    private String name;

    @SerializedName("description")
    private String description;


    public Repo() {}


    public int getId()       { return idRep; }
    public String getName()     { return name; }
    public String getDescription() { return description; }
}
