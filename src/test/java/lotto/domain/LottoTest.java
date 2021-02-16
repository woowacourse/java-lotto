package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 객체 생성 테스트")
    @Test
    void create() {
        Lotto lotto = new Lotto(Arrays.asList(11, 25, 32, 41, 7, 3));

        assertThat(lotto).isEqualTo(new Lotto(Arrays.asList(11, 25, 32, 41, 7, 3)));
    }



}