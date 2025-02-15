package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @Test
    void 올바른_값_입력_테스트() {
        String input = "1,2,3,4,5,6";

        assertThatCode(() -> Lotto.from(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {" 1,2,3,4,5,6", "1,2,3,4,5,6 ", "1, 2,3,4,5,6 "})
    void 공백을_허용한다(String input) {
        assertThatCode(() -> Lotto.from(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void 숫자가_6개가_아니라면_에러를_발생한다(String input) {
        assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_NUM_SIZE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.2.3.4.5.6", "1 2 3 4 5 6"})
    void 지정한_구분자를_사용해야_한다(String input) {
        assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.USE_SEPARATOR.getMessage());
    }

    @Test
    void 로또번호가_로또에_존재하면_True_반환() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);

        for (LottoNumber lottoNumber : lottoNumbers) {
            assertThat(lotto.contains(lottoNumber)).isTrue();
        }
    }

    @Test
    void 번호가_없다면_False_반환() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        LottoNumber notContainNumber = new LottoNumber(7);
        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto.contains(notContainNumber)).isFalse();
    }

    @Test
    void 일치하는_개수를_반환한다() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        Lotto winnerLotto = new Lotto(Stream.of(1, 6, 7, 8, 9, 10).map(LottoNumber::new).toList());
        long expect = 2;

        assertThat(lotto.getMatchCount(winnerLotto)).isEqualTo(expect);
    }

    @Test
    void 보너스넘버가_있다면_True_반환() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);

        for (LottoNumber bonusNumber : lottoNumbers) {
            assertThat(lotto.hasBonusNumber(bonusNumber)).isTrue();
        }
    }

    @Test
    void 보너스넘버가_없다면_False_반환() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber bonusNumber = new LottoNumber(7);

        assertThat(lotto.hasBonusNumber(bonusNumber)).isFalse();
    }

    @Test
    void 출력은_오름차순으로_정렬된다() {
        List<LottoNumber> lottoNumbers = Stream.of(45, 1, 44, 2, 43, 3).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        String expect = "[1, 2, 3, 43, 44, 45]";

        assertThat(lotto.toString()).hasToString(expect);
    }

}
