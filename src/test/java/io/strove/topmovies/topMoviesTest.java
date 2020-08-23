package io.strove.topmovies;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class topMoviesTest {
    @Test
    public void simpleTest() {
        List<Movie> movies = Arrays.asList(new Movie("Star Wars - The Phantom Menace", 4.2),
                new Movie("Star Wars - Attack of the Clones", 4.9), new Movie("Star Wars - Revenge of the Sith", 3.5),
                new Movie("Star Wars - A New Hope", 3.7), new Movie("Star Wars - The Empire Strikes Back", 4.6),
                new Movie("Star Wars - Return of the Jedi", 4.3));
        runTest(movies, true);
    }

    @Test
    public void bigTest() {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            if (i == 357) {
                movies.add(new Movie("best1", 4.6));
            } else if (i == 1264) {
                movies.add(new Movie("best2", 4.8));
            } else if (i == 65238) {
                movies.add(new Movie("best3", 4.5));
            } else if (i == 423165) {
                movies.add(new Movie("best4", 4.9));
            } else if (i == 928193) {
                movies.add(new Movie("best5", 4.7));
            } else {
                movies.add(new Movie(String.valueOf((char) ('a' + i % 26)), 4.3));
            }
        }

        runTest(movies, true);
    }

    @Test
    public void randomPerformanceTest() {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < 2000000; i++)
            movies.add(new Movie(String.valueOf((char) ('a' + i % 26)), getRandomRating()));

        runTest(movies, false);
    }

    private List<Movie> getBestMoviesCorrect(List<Movie> moviesList) {
        Movie[] movies = new Movie[6];
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

    private void runTest(List<Movie> movies, boolean checkTitle) {
        Main main = new Main();
        List<Movie> bestMovies = getBestMoviesCorrect(movies);
        List<Movie> candidateBestMovies = assertTimeoutPreemptively(Duration.ofSeconds(1),
                () -> main.getBestMovies(new ArrayList<>(movies)), "Test timeout");

        for (int i = 0; i < 5; i++) {
            if (checkTitle)
                assertTrue(bestMovies.get(i).getTitle().equals(candidateBestMovies.get(i).getTitle()));
            assertEquals(bestMovies.get(i).getRating(), candidateBestMovies.get(i).getRating());
        }
    }

    private double getRandomRating() {
        double minRating = 0.1;
        double maxRating = 5.0;
        Random r = new Random();

        return minRating + (maxRating - minRating) * r.nextDouble();
    }
}