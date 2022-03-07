package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseInfoTest {

    @Test
    @DisplayName("로또 구매에 대한 정보를 생성하는 기능")
    void createLottoPurchaseInfo() {
        List<List<Integer>> lottos = List.of(List.of(1, 2, 3, 4, 5, 6));
        LottoPurchaseCount lottoPurchaseCount = new LottoPurchaseCount(1, 0);

        assertThat(new LottoPurchaseInfo(lottos, lottoPurchaseCount)).isNotNull();
    }
}