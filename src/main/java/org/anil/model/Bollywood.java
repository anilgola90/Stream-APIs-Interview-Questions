package org.anil.model;

import java.util.List;

public record Bollywood(

        String id,
        String title,
        int releaseYear,
        List<String> genre,
        List<String> actors,
        List<String> directors


) {
}
