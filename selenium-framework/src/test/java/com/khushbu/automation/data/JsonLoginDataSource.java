package com.khushbu.automation.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class JsonLoginDataSource implements TestDataSource<LoginData> {
    @Override public List<LoginData> load() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("testdata/login-users.json")) {
            if (in == null) throw new IllegalStateException("JSON test data file not found");
            return new ObjectMapper().readValue(in, new TypeReference<List<LoginData>>() {});
        } catch (Exception e) { throw new IllegalStateException("Unable to read JSON login data", e); }
    }
}