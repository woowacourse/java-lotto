package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.LottoNumberGenerator;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LottoTest {

    private WinningLotto winningLotto;

    @BeforeEach
    public void setUp() {
        Lotto lotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = LottoNumber.of(7);
        winningLotto = new WinningLotto(lotto, bonusBall);
    }


    @ParameterizedTest
    @DisplayName("로또 등수 테스트")
    @MethodSource("parametersProvider")
    public void winTest(Set<LottoNumber> lottoNumbers, Rank actual) {
        Lotto lotto = new Lotto(lottoNumbers);
        Rank rank = winningLotto.match(lotto);
        assertThat(actual).isEqualTo(rank);
    }

    static Stream<Arguments> parametersProvider(){
        return Stream.of(
                arguments(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6), Rank.FIRST),
                arguments(LottoNumberGenerator.of(1, 2, 3, 4, 5, 7), Rank.SECOND),
                arguments(LottoNumberGenerator.of(1, 2, 3, 4, 5, 44), Rank.THIRD),
                arguments(LottoNumberGenerator.of(1, 2, 3, 4, 43, 44), Rank.FOURTH),
                arguments(LottoNumberGenerator.of(1, 2, 3, 42, 43, 44), Rank.FIFTH),
                arguments(LottoNumberGenerator.of(1, 2, 41, 42, 43, 44), Rank.SIXTH)
        );
    }

    @Test
    @DisplayName("당첨 번호가 6자리가 아니면 예외 발생")
    public void checkWinningNumberIs6Test() {
        assertThatThrownBy(
                () -> new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외 발생")
    public void checkDuplicatedNumberTest() {
        assertThatThrownBy(
                () -> new Lotto(LottoNumberGenerator.of(1, 1, 3, 4, 5, 46))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1부터 6사이의 로또 생성시 일치 테스트")
    void generateOneToSixLottoNumberTest() {
        Set<LottoNumber> lottoNumbers = LottoNumberGenerator.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.generateLottoNumbers(new FixedNumbersGenerator(lottoNumbers));
        Lotto actual = new Lotto(lottoNumbers);
        assertThat(lotto).isEqualTo(actual);
    }

    @Test
    @DisplayName("5부터 10사이의 로또 생성시 일치 테스트")
    void generateFiveToTenLottoNumberTest() {
        Set<LottoNumber> lottoNumbers = LottoNumberGenerator.of(5, 6, 7, 8, 9, 10);
        Lotto lotto = Lotto.generateLottoNumbers(new FixedNumbersGenerator(lottoNumbers));
        Lotto actual = new Lotto(lottoNumbers);
        assertThat(lotto).isEqualTo(actual);
    }
}
