package io.strove.topmovies;

import java.util.Arrays;
import java.util.List;

public class Main {
    // TODO implement this method
    public static List<Movie> getBestMovies(List<Movie> moviesList) {
        return null;
    }

    // Run this code
    public static void main (String[] args) {
        List<Movie> movies = Arrays.asList(new Movie("Star Wars - The Phantom Menace", 4.2),
                new Movie("Star Wars - Attack of the Clones", 4.9), new Movie("Star Wars - Revenge of the Sith", 3.5),
                new Movie("Star Wars - A New Hope", 3.7), new Movie("Star Wars - The Empire Strikes Back", 4.6),
                new Movie("Star Wars - Return of the Jedi", 4.3));
        List<Movie> bestMovies = getBestMovies(movies);
        for (Movie movie : bestMovies)
            System.out.println(movie.getRating() + " " + movie.getTitle());
    }
}