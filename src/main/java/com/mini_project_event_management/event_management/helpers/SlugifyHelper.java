package com.mini_project_event_management.event_management.helpers;

import com.github.slugify.Slugify;

public class SlugifyHelper {
    private static final Slugify slugify;

    static {
        slugify = Slugify.builder()
                .lowerCase(false)
                .build();
    }

    public static String slugify(String input) {
        return slugify.slugify(input);
    }
}
