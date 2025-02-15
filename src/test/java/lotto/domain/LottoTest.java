package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @DisplayName("올바른 값 입력 테스트")
    @Test
    void correctTest() {
        String input = "1,2,3,4,5,6";

        assertThatCode(() -> Lotto.from(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("공백을 허용한다.")
    @ParameterizedTest
    @ValueSource(strings = {" 1,2,3,4,5,6", "1,2,3,4,5,6 ", "1, 2,3,4,5,6 "})
    void acceptBlank(String input) {
        assertThatCode(() -> Lotto.from(input))
                .doesNotThrowAnyException();
    }


    @DisplayName("숫자가_6개가_아니라면_에러를_발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void sizeNotEqualLottoSize(String input) {
        assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_NUM_SIZE.getMessage());
    }

    @DisplayName("지정한_구분자를_사용해야_한다")
    @ParameterizedTest
    @ValueSource(strings = {"1.2.3.4.5.6", "1 2 3 4 5 6"})
    void useCorrectSeparator(String input) {
        assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.USE_SEPARATOR.getMessage());
    }

    @DisplayName("로또번호가_로또에_존재하면_True_반환")
    @Test
    void returnTrueIfHasLottoNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);

        for (LottoNumber lottoNumber : lottoNumbers) {
            assertThat(lotto.hasNumber(lottoNumber)).isTrue();
        }
    }

    @DisplayName("번호가_없다면_False_반환")
    @Test
    void returnFalseIfNotHasLottoNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        LottoNumber notContainNumber = new LottoNumber(7);
        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto.hasNumber(notContainNumber)).isFalse();
    }

    @DisplayName("일치하는_개수를_반환한다")
    @Test
    void returnCorrectCount() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        Lotto winnerLotto = new Lotto(Stream.of(1, 6, 7, 8, 9, 10).map(LottoNumber::new).toList());
        long expect = 2;

        assertThat(lotto.getMatchCount(winnerLotto)).isEqualTo(expect);
    }

    @DisplayName("보너스넘버가_있다면_True_반환")
    @Test
    void returnTrueIfHasBonusNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);

        for (LottoNumber bonusNumber : lottoNumbers) {
            assertThat(lotto.hasNumber(bonusNumber)).isTrue();
        }
    }

    @DisplayName("보너스넘버가_없다면_False_반환")
    @Test
    void returnFalseIfNotHasBonusNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber bonusNumber = new LottoNumber(7);

        assertThat(lotto.hasNumber(bonusNumber)).isFalse();
    }

    @DisplayName("출력은_오름차순으로_정렬된다")
    @Test
    void printWithSort() {
        List<LottoNumber> lottoNumbers = Stream.of(45, 1, 44, 2, 43, 3).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        String expect = "[1, 2, 3, 43, 44, 45]";

        assertThat(lotto.toString()).hasToString(expect);
    }

}
