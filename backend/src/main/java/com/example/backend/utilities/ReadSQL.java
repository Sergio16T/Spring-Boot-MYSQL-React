package com.example.backend.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.stream.Collectors;

import com.example.backend.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadSQL {
    public String SQL;
    public String path;

    private static final Logger logger= LoggerFactory.getLogger(ReadSQL.class);

    public ReadSQL(String path) {
        Integer beginIndex = path.indexOf("/", 1);
        this.path = beginIndex > 0 ? path.substring(beginIndex) : path.substring(0);

        // Java -Try With Resources- syntax
        try (InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
                this.SQL = contents;
        } catch (IOException e) {
            logger.error("ReadSQL IOException: ", e);
        } catch (NullPointerException e) {
            logger.error("ReadSQL NullPointerException: ", e);
        }
    }

    public String getQuery() throws ResourceNotFoundException {
        if (this.SQL == null) {
            throw new ResourceNotFoundException("\n\t at com.example.backend.utilities.ReadSQL.getQuery(ReadSQL.java:40) ~[classes/:na] \n\t this.SQL is null for resource: " + this.path);
        }
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