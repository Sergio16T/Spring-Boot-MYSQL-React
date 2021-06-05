package com.example.backend.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.stream.Collectors;

public class ReadSQL {
    public String SQL;

    public ReadSQL(String path) {
        InputStream inputStream = getClass().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String contents = reader.lines()
            .collect(Collectors.joining(System.lineSeparator()));

        this.SQL = contents;
    }

    public String getQuery() {
        return this.SQL;
    }
}
