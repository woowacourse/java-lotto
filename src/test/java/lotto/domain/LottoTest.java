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
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    private static Stream<Arguments> numberSizeTestArguments() {
        return Stream.of(
                Arguments.arguments(toLottoNumberList(List.of(1, 2, 3, 4, 5))),
                Arguments.arguments(toLottoNumberList(List.of(1, 2, 3, 4, 5, 6, 7)))
        );
    }

    private static List<LottoNumber> toLottoNumberList(List<Integer> numbers) {
        return numbers.stream().map(LottoNumber::new).toList();
    }

    @Test
    @DisplayName("로또 번호는 6개여야 한다.")
    void correctLottNumberSize() {
        List<LottoNumber> lottoNumberList = toLottoNumberList(List.of(1, 2, 3, 4, 5, 6));

        assertThatCode(() -> new Lotto(lottoNumberList))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호 개수가 6개가 아니면 에러를 반환한다.")
    @MethodSource("numberSizeTestArguments")
    @ParameterizedTest
    void invalidLottoNumberSize(List<LottoNumber> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_NUM_SIZE.getMessage());
    }

    // TODO InputView 테스트로 넘긴다.


    @DisplayName("로또번호가_로또에_존재하면_True_반환")
    @Test
    void returnTrueIfHasLottoNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);

        for (LottoNumber lottoNumber : lottoNumbers) {
            assertThat(lotto.hasLottoNumber(lottoNumber)).isTrue();
        }
    }

    @DisplayName("번호가_없다면_False_반환")
    @Test
    void returnFalseIfNotHasLottoNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        LottoNumber notContainNumber = new LottoNumber(7);
        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto.hasLottoNumber(notContainNumber)).isFalse();
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
            assertThat(lotto.hasLottoNumber(bonusNumber)).isTrue();
        }
    }

    @DisplayName("보너스넘버가_없다면_False_반환")
    @Test
    void returnFalseIfNotHasBonusNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber bonusNumber = new LottoNumber(7);

        assertThat(lotto.hasLottoNumber(bonusNumber)).isFalse();
    }

    // TODO OutputView 테스트로 변경한다.

//    @DisplayName("출력은_오름차순으로_정렬된다")
//    @Test
//    void printWithSort() {
//        List<LottoNumber> lottoNumbers = Stream.of(45, 1, 44, 2, 43, 3).map(LottoNumber::new).toList();
//        Lotto lotto = new Lotto(lottoNumbers);
//        String expect = "[1, 2, 3, 43, 44, 45]";
//
//        assertThat(lotto.toString()).hasToString(expect);
//    }

}
