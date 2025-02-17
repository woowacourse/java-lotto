package domain;

import fixture.LottoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    private static final List<Integer> ALL_LOTTO_NUMBERS = IntStream.rangeClosed(
            LottoRule.MIN_LOTTO_NUMBER.getValue(),
            LottoRule.MAX_LOTTO_NUMBER.getValue()
    ).boxed().toList();

    @Test
    @DisplayName("로또는 설정된 개수의 숫자를 포함해야 한다")
    void 로또는_설정된_개수의_숫자를_포함해야_한다() {
        // given
        List<LottoNumber> validNumbers = LottoFixture.createLottoNumbers(
                ALL_LOTTO_NUMBERS.subList(0, LottoRule.LOTTO_SELECTION_SIZE.getValue()));

        // when
        // then
        assertThatCode(() -> Lotto.from(validNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또가 설정된 개수보다 적으면 예외가 발생해야 한다")
    void 로또가_설정된_개수보다_적으면_예외가_발생해야_한다() {
        // given
        List<LottoNumber> invalidNumbers = LottoFixture.createLottoNumbers(
                ALL_LOTTO_NUMBERS.subList(0, LottoRule.LOTTO_SELECTION_SIZE.getValue() - 1));

        // when
        // then
        assertThatThrownBy(() -> Lotto.from(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또가 설정된 개수보다 많으면 예외가 발생해야 한다")
    void 로또가_설정된_개수보다_많으면_예외가_발생해야_한다() {
        // given
        List<LottoNumber> invalidNumbers = LottoFixture.createLottoNumbers(
                ALL_LOTTO_NUMBERS.subList(0, LottoRule.LOTTO_SELECTION_SIZE.getValue() + 1));

        // when
        // then
        assertThatThrownBy(() -> Lotto.from(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호는 오름차순으로 정렬되어야 한다")
    void 로또_번호는_오름차순으로_정렬되어야_한다() {
        // given
        List<LottoNumber> numbers = LottoFixture.createLottoNumbers(
                ALL_LOTTO_NUMBERS.subList(0, LottoRule.LOTTO_SELECTION_SIZE.getValue()));

        Collections.shuffle(numbers);

        List<LottoNumber> expectedSortedNumbers =
                numbers.stream()
                        .sorted(Comparator.comparingInt(LottoNumber::getValue))
                        .toList();

        // when
        Lotto lotto = Lotto.from(numbers);

        // then
        assertThat(lotto.getNumbers()).isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("로또는 기대한 출력 형식으로 변환되어야 한다")
    void 로또는_기대한_출력_형식으로_변환되어야_한다() {
        // given
        List<LottoNumber> numbers = LottoFixture.createLottoNumbers(ALL_LOTTO_NUMBERS.subList(0, LottoRule.LOTTO_SELECTION_SIZE.getValue()));
        Lotto lotto = Lotto.from(numbers);

        // when
        String lottoFormat = lotto.toString();

        // then
        assertThat(lottoFormat).isEqualTo(
                "[" + numbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")) + "]");
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생해야 한다")
    void 로또_번호가_중복되면_예외가_발생해야_한다() {
        // given
        List<LottoNumber> numbers = LottoFixture.createLottoNumbers(
                ALL_LOTTO_NUMBERS.subList(0, LottoRule.LOTTO_SELECTION_SIZE.getValue() - 1)
        );
        numbers.add(numbers.getFirst()); // 중복 값 추가

        // when
        // then
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
