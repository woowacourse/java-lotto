package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import lotto.domain.lotto.Lotto;
import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidLottoNumberCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 객체 생성 테스트")
    @Test
    void create() {
        Lotto lotto = new Lotto(Arrays.asList(1, 25, 32, 45, 7, 3));
        Lotto comparingLotto = new Lotto(Arrays.asList(1, 25, 32, 45, 7, 3));

        assertThat(lotto.match(comparingLotto)).isEqualTo(6);
        assertThat(lotto).isNotEqualTo(comparingLotto);
    }

    @DisplayName("로또 번호 중복 테스트")
    @Test
    void duplicateException() {
        assertThatThrownBy(() ->
            new Lotto(Arrays.asList(1, 25, 32, 11, 11, 45))
        ).isInstanceOf(DuplicateLottoNumberException.class);
    }

    @DisplayName("로또 번호 6개 입력 안했을 때 테스트")
    @Test
    void countException() {
        assertThatThrownBy(() ->
                new Lotto(Arrays.asList(1, 25, 32, 41, 45))
        ).isInstanceOf(InvalidLottoNumberCountException.class);
        assertThatThrownBy(() ->
                new Lotto(Arrays.asList(1, 25, 32, 41, 12, 13, 45))
        ).isInstanceOf(InvalidLottoNumberCountException.class);
    }
}