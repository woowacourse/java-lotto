package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGroupTest {
    @Test
    void 구입개수_확인() {
        String money = "14000";
        LottoGroup lottoGroup = new LottoGroup(money);
        assertThat(lottoGroup.getCount()).isEqualTo(14);
    }
}

