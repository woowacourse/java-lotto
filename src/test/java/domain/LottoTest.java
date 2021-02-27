package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
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

    private static Stream<int[]> generateIfLottoNumbersLengthNotSatisfied() {
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
        Money money = Money.createPurchasingLottoMoney(moneyValue);

        //when
        CountOfPurchasingLotto lottoNumber = Lotto.calculateLottoNumber(money);

        //then
        assertThat(lottoNumber).isEqualTo(new CountOfPurchasingLotto(expectedLottoNumber));
    }

    @DisplayName("로또넘버가 로또에 포함되는 숫자인지 확인하는 기능")
    @ParameterizedTest
    @CsvSource(value = {
            "1:true", "2:true", "3:true", "4:true", "5:true", "6:true", "7:false", "8:false"
    }, delimiter = ':')
    void contains(int lottoNumberValue, boolean expected) {
        //given
        Lotto lotto = new Lotto(new int[]{1, 2, 3, 4, 5, 6});
        LottoNumber lottoNumber = new LottoNumber(lottoNumberValue);

        //when
        boolean actual = lotto.contains(lottoNumber);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("두 로또 중에서 일치하는 로또숫자 개수를 파악하는 기능")
    @ParameterizedTest
    @MethodSource
    void findMatchCount(int[] lottoNumbers, int expected) {
        //given
        Lotto lotto = new Lotto(new int[]{1, 2, 3, 4, 5, 6});
        Lotto targetLotto = new Lotto(lottoNumbers);

        //when
        int actual = lotto.findMatchCount(targetLotto);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> findMatchCount() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, 6),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 7}, 5),
                Arguments.of(new int[]{1, 2, 3, 4, 7, 8}, 4),
                Arguments.of(new int[]{1, 2, 3, 7, 8, 9}, 3),
                Arguments.of(new int[]{1, 2, 7, 8, 9, 10}, 2),
                Arguments.of(new int[]{1, 7, 8, 9, 10, 11}, 1),
                Arguments.of(new int[]{7, 8, 9, 10, 11, 12}, 0)
        );
    }
}
