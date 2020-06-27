package com.example.transactionapp.structure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

class MovieCollection {

    List<Movie> movies;

    // public constructor is necessary for collections
    public MovieCollection() {
        movies = new ArrayList<Movie>();
    }

    public static MovieCollection parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        MovieCollection boxOfficeMovieResponse = gson.fromJson(response, MovieCollection.class);
        return boxOfficeMovieResponse;
    }
}