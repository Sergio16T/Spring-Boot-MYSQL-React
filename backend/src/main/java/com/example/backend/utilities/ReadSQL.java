package com.example.backend.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.stream.Collectors;

public class ReadSQL {
    public String SQL;

    public ReadSQL(String path) {
        // Java -Try With Resources- syntax
        try (InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines()
              .collect(Collectors.joining(System.lineSeparator()));
              this.SQL = contents;
        } catch (Exception e) {
            System.out.println("ReadSQL exception thrown: " + e);
        }
    }

    public String getQuery() {
        return this.SQL;
    }
}

// The try-with-resources statement is a try statement that declares one or more resources.
// A resource is an object that must be closed after the program is finished with it.
// The try-with-resources statement ensures that each resource is closed at the end of the statement

// Without try-with-resources syntax
// InputStream inputStream = getClass().getResourceAsStream(path);
// BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
// String contents = reader.lines()
//     .collect(Collectors.joining(System.lineSeparator()));
//  this.SQL = contents;