package lotto.domain;

import lotto.domain.result.Count;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountTest {
    @Test
    @DisplayName("Count가 정상적으로 작동하는지 테스트")
    void countTest(){
        Count count = new Count();
        count.addCount();
        assertThat(count.getCount()).isEqualTo(1);
    }
}
