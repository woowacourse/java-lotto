package lotto.common.utill;

import static lotto.common.constant.Constant.*;
import static lotto.common.utill.RandomsWrapper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomsWrapperTest {

    @Test
    @DisplayName(" ")
    void test() {
        List<Integer> list = getRandomIntList();
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(LOTTO_SIZE);
    }
}
