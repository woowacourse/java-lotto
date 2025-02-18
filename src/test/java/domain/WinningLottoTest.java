package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lottogenerator.LottoGenerator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {
    @DisplayName("당첨 로또 생성 성공")
    @Test
    void winningLottoCreation() {

        final Lotto lotto = Lotto.of(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).toList());
        final int bonusNumber = 7;

        assertThatCode(() -> WinningLotto.of(lotto, LottoNumber.of(bonusNumber)))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호와 당첨 번호 중복 시 예외 발생")
    @Test
    void duplicateBonusNumberCreationThrowException() {

        final Lotto lotto = Lotto.of(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).toList());
        final int bonusNumber = 4;

        assertThatThrownBy(() -> WinningLotto.of(lotto, LottoNumber.of(bonusNumber)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("로또 등수 계산하기 테스트")
    @ParameterizedTest
    @CsvSource({
            "1,2,3,4,5,6, FIRST_PLACE",
            "1,2,3,4,5,7, SECOND_PLACE",
            "1,2,3,4,5,8, THIRD_PLACE",
            "1,2,3,4,9,10, FOURTH_PLACE",
            "11,12,13,4,5,6, FIFTH_PLACE",
            "11,12,13,14,5,6, SIXTH_PLACE"
    })
    void calculatePrizesTest(String num1, String num2, String num3, String num4, String num5, String num6,
                             Prize expectedPrize) {
        // 원하는 로또 생성을 위한 익명 클래스 생성
        LottoGenerator fixedLottoGenerator = new LottoGenerator() {
            private final List<Integer> numbers
                    = Stream.of(num1, num2, num3, num4, num5, num6)
                    .map(Integer::parseInt)
                    .toList();

            @Override
            public List<LottoNumber> generate() {
                return numbers.stream()
                        .map(LottoNumber::of)
                        .toList();
            }
        };

        // 우승 로또 준비
        Lotto winningNumbers = Lotto.of(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).toList());
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, LottoNumber.of(bonusNumber));

        // 발급 받은 로또
        Lottos lottos = Lottos.ofSize(1, fixedLottoGenerator);

        // 당첨 결과 계산
        List<Prize> prizes = winningLotto.calculatePrizes(lottos);

        assertThat(prizes).containsExactly(expectedPrize);
    }
}
