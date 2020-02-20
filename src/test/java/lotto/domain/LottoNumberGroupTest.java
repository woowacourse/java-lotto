package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberGroupTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또_번호_생성_테스트() {
        Assertions.assertThat(LottoNumberGroup.getInstance(1))
                .isInstanceOf(LottoNumber.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또_번호_중복_생성_테스트() {
        Assertions.assertThat(LottoNumberGroup.getInstance(1))
                .isEqualTo(LottoNumberGroup.getInstance(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @SuppressWarnings("NonAsciiCharacters")
    void 로또_숫자_범위_밖의_입력이_들어온_경우_테스트(int i) {
        Assertions.assertThatThrownBy(() -> LottoNumberGroup.getInstance(i))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lotto number out of range.");
    }
}
