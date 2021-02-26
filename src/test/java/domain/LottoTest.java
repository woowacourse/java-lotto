package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("Lotto를 생성하는 기능")
    @Test
    void generate() {
        //given
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        //when
        Lotto lotto = new Lotto(lottoNumbers);

        //then
        assertThat(lotto).isNotNull();
    }

    @DisplayName("LottoNumbers에 중복이 있는 경우")
    @Test
    void generateWithDuplicatedLottoNumbers() {
        //given
        int[] lottoNumbers = new int[]{1, 1, 3, 4, 5, 6};

        //when //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoNumbers 길이가 6이 아닌 경우")
    @ParameterizedTest
    @MethodSource
    void generateIfLottoNumbersLengthNotSatisfied(int[] lottoNumbers) {
        //when //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<int[]> generateIfLottoNumberLengthNotSatisfied() {
        return Stream.of(new int[]{1, 2, 3, 4, 5}
                , new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @DisplayName("구입금액에 따라 Lotto 개수를 반환하는 기능")
    @ParameterizedTest
    @CsvSource(value = {
            "1000:1", "2000:2", "5000:5", "10000:10"
    }, delimiter = ':')
    void calculateLottoNumber(int moneyValue, int expectedLottoNumber) {
        //given
        Money money = new Money(moneyValue);

        //when
        long lottoNumber = Lotto.calculateLottoNumber(money);

        //then
        assertThat(lottoNumber).isEqualTo(expectedLottoNumber);
    }
}
