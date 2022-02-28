package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Test
    @DisplayName("로또 번호는 6자리 숫자로 생성된다.")
    void createSixSizeNumbers() {
        List<LottoNumber> lottoNumbers = givenNumbers(1, 2, 3, 4, 5, 6);

        assertThat(new Lotto(lottoNumbers)).isNotNull();
    }

    @ParameterizedTest(name = "잘못된 로또 번호 : {0}")
    @MethodSource("getNumbers")
    @DisplayName("맞춘 번호에 따라 등수를 반환한다.")
    void findRank(List<LottoNumber> lottoNumbers) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(lottoNumbers))
            .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    @Test
    @DisplayName("로또 번호가 중복될 경우 예외가 발생한다.")
    void throwExceptionWhenDuplicateNumbers() {
        List<LottoNumber> lottoNumbers = givenNumbers(1, 2, 3, 4, 5, 5);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(lottoNumbers))
            .withMessageMatching("로또 번호는 중복될 수 없다.");
    }

    @Test
    @DisplayName("로또 숫자가 일치하는 만큼 개수를 반환한다.")
    void matchNumbers() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 6));

        assertThat(lotto.countMatchNumbers(lotto)).isEqualTo(6);
    }


    @ParameterizedTest
    @CsvSource(value = {"6,true", "7,false"})
    @DisplayName("해당하는 숫자가 포함되어 있는지 확인한다.")
    void checkNotContainsBonusNumber(int number, boolean expected) {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 6));

        assertThat(lotto.contains(new LottoNumber(number))).isEqualTo(expected);
    }

    @Test
    @DisplayName("외부에서 생성된 번호가 변경되어도 생성된 로또의 번호는 바뀌지 않는다.")
    void immutabilityLotto() {
        List<LottoNumber> lottoNumbers = givenNumbers(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);

        LottoNumber addLottoNumber = new LottoNumber(7);
        lottoNumbers.add(addLottoNumber);

        assertThat(lotto.contains(addLottoNumber)).isFalse();
    }

    private static List<LottoNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    }

    private static Stream<List<LottoNumber>> getNumbers() {
        return Stream.of(
            givenNumbers(1, 2, 3, 4, 5),
            givenNumbers(1, 2, 3, 4, 5, 6, 7)
        );
    }
}
