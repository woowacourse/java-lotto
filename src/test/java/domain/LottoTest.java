package domain;

import fixture.LottoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("로또는 6개의 숫자를 포함해야 한다")
    void 로또는_6개의_숫자를_포함해야_한다() {
        // given
        List<LottoNumber> validNumbers = LottoFixture.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when
        // then
        assertThatCode(() -> Lotto.from(validNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또가 6개의 숫자보다 적으면 예외가 발생해야 한다")
    void 로또가_6개의_숫자보다_적으면_예외가_발생해야_한다() {
        // given
        List<LottoNumber> invalidNumbers = LottoFixture.createLottoNumbers(List.of(1, 2, 3, 4, 5));

        // when
        // then
        assertThatThrownBy(() -> Lotto.from(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또가 6개의 숫자보다 많으면 예외가 발생해야 한다")
    void 로또가_6개의_숫자보다_많으면_예외가_발생해야_한다() {
        // given
        List<LottoNumber> invalidNumbers = LottoFixture.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6, 7));

        // when
        // then
        assertThatThrownBy(() -> Lotto.from(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호는 오름차순으로 정렬되어야 한다")
    void 로또_번호는_오름차순으로_정렬되어야_한다() {
        // given
        List<LottoNumber> numbers = LottoFixture.createLottoNumbers(List.of(6, 5, 4, 3, 2, 1));
        List<LottoNumber> sortedNumbers = LottoFixture.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when
        Lotto lotto = Lotto.from(numbers);

        // then
        assertThat(lotto.getNumbers()).isEqualTo(sortedNumbers);
    }

    @Test
    @DisplayName("로또는 기대한 출력 형식으로 변환되어야 한다")
    void 로또는_기대한_출력_형식으로_변환되어야_한다() {
        // given
        List<LottoNumber> numbers = LottoFixture.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = Lotto.from(numbers);

        // when
        String lottoFormat = lotto.toString();

        // then
        assertThat(lottoFormat).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생해야 한다")
    void 로또_번호가_중복되면_예외가_발생해야_한다() {
        // given
        List<LottoNumber> numbers = LottoFixture.createLottoNumbers(List.of(1, 2, 3, 4, 5, 5));

        // when
        // then
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
