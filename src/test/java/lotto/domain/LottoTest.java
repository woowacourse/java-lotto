package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.exception.DuplicateLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 객체 생성 테스트")
    @Test
    void create() {
        Lotto lotto = new Lotto(Arrays.asList(11, 25, 32, 41, 7, 3));

        assertThat(lotto).isEqualTo(new Lotto(Arrays.asList(11, 25, 32, 41, 7, 3)));
    }

    @DisplayName("로또번호 중복 에러테스트")
    @Test
    void duplicateException() {
        assertThatThrownBy(() ->
            new Lotto(Arrays.asList(11, 25, 32, 41, 11, 3))
        ).isInstanceOf(DuplicateLottoNumberException.class);
    }
}