package lotto.domain.generator;

import lotto.domain.*;

import java.util.HashMap;
import java.util.Map;

public class ResultGenerator {
    public static Result generateResult(BoughtLottos boughtLottos, WinningNumber winningNumber) {
        Map<Prize, Integer> results = new HashMap<>();
        for (Lotto lotto : boughtLottos.getLottos()) {
            Prize prize = winningNumber.prize(lotto);
            putResult(results, prize);
        }
        return new Result(results);
    }

    private static void putResult(final Map<Prize, Integer> results, final Prize prize) {
        Integer count = results.get(prize);
        if (count != null) {
            results.put(prize, count + 1);
        }
        results.putIfAbsent(prize, 1);
    }
}
