package lotto.dao;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ResultTest {
    private Result result = Result.getInstance();

    @Test
    void addResult() {
        List<String> results = new ArrayList<>();
        results.add("FIRST");
        results.add("SECOND");
        result.addResult(results, 7);
    }

    @Test
    void offerResults() {
        System.out.println(result.offerResults(7));
    }
}