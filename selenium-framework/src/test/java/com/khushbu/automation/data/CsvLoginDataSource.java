package com.khushbu.automation.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvLoginDataSource implements TestDataSource<LoginData> {
    @Override public List<LoginData> load() {
        try (var in = getClass().getClassLoader().getResourceAsStream("testdata/login-users.csv")) {
            if (in == null) throw new IllegalStateException("CSV test data file not found");
            Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(reader);
            List<LoginData> data = new ArrayList<>();
            for (CSVRecord row : records)
                data.add(new LoginData(row.get("username"), row.get("password"), row.get("expectedOutcome")));
            return data;
        } catch (Exception e) { throw new IllegalStateException("Unable to read CSV login data", e); }
    }
}