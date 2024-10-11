package org.anil.model;

import java.util.List;

public record Movie(String showId,
                    String type,
                    String title,
                    List<String> directors,
                    List<String> actors,
                    String country,
                    Integer year){}
