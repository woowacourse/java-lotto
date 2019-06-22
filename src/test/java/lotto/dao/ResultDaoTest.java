package lotto.dao;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ResultDaoTest {
    private ResultDao resultDao = ResultDao.getInstance();

    @Test
    void addResult() {
        List<String> results = new ArrayList<>();
        results.add("FIRST");
        results.add("SECOND");
        resultDao.addResult(results, 7);
    }

    @Test
    void offerResults() {
        System.out.println(resultDao.offerResults(7));
    }
}