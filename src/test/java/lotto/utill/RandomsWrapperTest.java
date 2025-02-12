package lotto.utill;

import static lotto.utill.RandomsWrapper.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomsWrapperTest {

    @Test
    @DisplayName(" ")
    void test() {
        List<Integer> list = getRandomNumbers();
        assertThat(list).isNotNull();
        assertThat(list.size()==6).isTrue();
    }
}