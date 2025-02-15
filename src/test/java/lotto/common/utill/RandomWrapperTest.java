package lotto.common.utill;

import static lotto.common.constant.BusinessRule.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class RandomWrapperTest {
    private static final long TEST_SEED = 123L;

    @BeforeEach
    void setUp() throws Exception {
        Field randomField = RandomWrapper.class.getDeclaredField("random");
        randomField.setAccessible(true);
        Random random = (Random)randomField.get(null);
        random.setSeed(TEST_SEED);
    }

    @Test
    @DisplayName("Random 클래스의 무작위 생성은 시드값에 의해 결정된다.")
    void learnRandomIntListWithSeed() {
        var min = LOTTO_MINIMUM;
        var max = LOTTO_MAXIMUM;
        var size = LOTTO_SIZE;

        List<Integer> result1 = RandomWrapper.getRandomIntList(min, max, size);

        try {
            setUp();
        } catch (Exception e) {
            fail("시드 리셋 실패");
        }

        List<Integer> result2 = RandomWrapper.getRandomIntList(min, max, size);

        assertEquals(result1, result2);
        assertEquals(size, result1.size());

        for (int num : result1) {
            assertTrue(num >= min && num < max);
        }

        assertEquals(size, new HashSet<>(result1).size());
    }

    @RepeatedTest(100)
    void testRandomIntListProperties() {
        var min = LOTTO_MINIMUM;
        var max = LOTTO_MAXIMUM;
        var size = LOTTO_SIZE;

        List<Integer> result = RandomWrapper.getRandomIntList(min, max, size);

        assertEquals(size, result.size());
        for (int num : result) {
            assertTrue(num >= min && num < max,
                "생성된 숫자가 범위를 벗어났다 : " + num);
        }

        assertEquals(size, new HashSet<>(result).size(),
            "리스트가 중복 결과를 포함한다.");
    }
}
