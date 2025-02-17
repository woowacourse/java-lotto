package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.properties.LottoProperties;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("중복이 없는 6개의 숫자로 로또 객체를 생성한다")
    @Test
    void createLottoWithDistinctNumbers() {
        List<Integer> distinctNumbers = Arrays.asList(1, 2, 3, 4, 5, 45);
        List<LottoNumber> distinctLottoNumbers = distinctNumbers.stream().map(LottoNumber::of).toList();

        assertThatCode(() -> Lotto.of(distinctLottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("6개의 숫자에 중복이 있을 경우 예외를 던진다")
    @Test
    void throwExceptionWhenCreateLottoWithDuplicateNumbers() {
        List<Integer> duplicateNumber = Arrays.asList(1, 2, 3, 4, 6, 6);
        List<LottoNumber> duplicateLottoNumber = duplicateNumber.stream().map(LottoNumber::of).toList();

        assertThatThrownBy(() -> Lotto.of(duplicateLottoNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("NULL일 경우 예외를 던진다")
    @Test
    void throwExceptionWithNull() {
        assertThatThrownBy(() -> Lotto.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 번호는 %d개여야 합니다.", LottoProperties.COUNT_OF_NUMBERS));
    }

    @DisplayName("로또 번호의 개수가 6개가 아닐 경우 예외를 던진다")
    @Test
    void throwExceptionIfSizeOfLottoIsNot6() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::of).toList();

        assertThatThrownBy(() -> Lotto.of(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 객체 생성 시 숫자들을 정렬한다")
    @Test
    void createOrderedLotto() {
        List<Integer> unorderedNumbers = Arrays.asList(43, 26, 1, 3, 5, 2);
        List<LottoNumber> unorderedLottoNumbers = unorderedNumbers.stream().map(LottoNumber::of).toList();
        Lotto lotto = Lotto.of(unorderedLottoNumbers);

        List<Integer> orderedNumbers = unorderedNumbers.stream().sorted().toList();
        List<LottoNumber> orderedLottoNumbers = orderedNumbers.stream().map(LottoNumber::of).toList();
        Lotto orderedLotto = Lotto.of(orderedLottoNumbers);
        assertThat(lotto.toString()).isEqualTo(orderedLotto.toString());
    }
}
