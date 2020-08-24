package io.strove.topmovies;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    // TODO implement this method
    public List<Movie> getBestMovies(List<Movie> moviesList) {
        /*Collections.sort(moviesList, new Comparator<Movie>() {
            @Override
            public int compare(Movie movie, Movie movie1) {
                return movie.getRating() < movie1.getRating() ? 1 : -1;
            }
        });
        return moviesList.subList(0, 5);*/
        Movie movies[] = new Movie[6];
        for (int i = 0; i < moviesList.size(); i++) {
            int pos = Math.min(i, 5);
            movies[pos] = moviesList.get(i);
            for (int j = pos; j > 0; j--) {
                if (movies[j].getRating() > movies[j - 1].getRating()) {
                    Movie tmpMovie = movies[j - 1];
                    movies[j - 1] = movies[j];
                    movies[j] = tmpMovie;
                } else {
                    break;
                }
            }
        }
        return Arrays.asList(movies).subList(0, 5);
    }
}