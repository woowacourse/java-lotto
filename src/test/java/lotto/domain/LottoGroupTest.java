package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LottoGroupTest {
    // 금액 입력

    @Test
    void getMoney() {
        assertThatCode(() -> new LottoGroup(4))
            .doesNotThrowAnyException();
    }


    // 지난주 당첨번호입ㄹ
    // 로또그룹 -> 위닝넘버
}