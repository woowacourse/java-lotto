package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {
    private Map<Reward, Integer> map;

    @BeforeEach
    void setUp() {
        map = new LinkedHashMap<>();
        map.put(Reward.FIFTH, 0);
        map.put(Reward.FOURTH, 0);
        map.put(Reward.THIRD, 0);
        map.put(Reward.SECOND, 0);
        map.put(Reward.FIRST, 0);
    }

    @Test
    void name() {
        LottoResult result = new LottoResult(map);
        System.out.println(result.toString());
    }
}