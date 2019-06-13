package lotto.domain.utils;

import lotto.domain.model.Number;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ShuffledNumberGeneratorTest {

    List<Number> shuffledNumber = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        shuffledNumber = ShuffledNumberGenerator.getShuffledNumbers();
    }

    @Test
    public void 랜덤_넘버_생성_확인_중복_사이즈() {
        assertEquals(new HashSet<>(shuffledNumber).size(), 6);
    }
}
