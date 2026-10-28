package com.khushbu.automation.data;

import java.util.List;

public interface TestDataSource<T> {
    List<T> load();
}