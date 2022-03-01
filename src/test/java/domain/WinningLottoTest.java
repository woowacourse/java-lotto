package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    @Test
    @DisplayName("WinningLotto 객체가 정상적으로 생성되는 경우")
    void createWinningLotto() {
        Lotto lotto = LottoFactory.createLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        assertThat(winningLotto).isNotNull();
    }

    @Test
    @DisplayName("WinningLotto 객체 생성 시 당첨 로또가 null 인 경우")
    void createWinningLottoWithNullLotto() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        assertThatThrownBy(() ->
            new WinningLotto(null, bonusNumber))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("WinningLotto 객체 생성 시 보너스 번호가 null 인 경우")
    void createWinningLottoWithNullBonus() {
        Lotto lotto = LottoFactory.createLotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() ->
            new WinningLotto(lotto, null))
            .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @MethodSource("parameterProvider")
    @DisplayName("당청번호와 구매의 로또 번호의 일치 여부를 계산하는 경우 1")
    void matchLotto(List<Integer> buyNumbers, List<Integer> winningNumbers, int bonusNumber,
        LottoReward exceptedReward) {
        Lotto bueLotto = LottoFactory.createLotto(buyNumbers);
        Lotto lotto = LottoFactory.createLotto(winningNumbers);
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(bonusNumber);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusLottoNumber);

        LottoReward reward = winningLotto.match(bueLotto);

        assertThat(reward).isEqualTo(exceptedReward);
    }

    private static Stream<Arguments> parameterProvider() {
        return Stream.of(
            Arguments.of(Arrays.asList(1, 2, 3, 4, 17, 16), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.FIRST),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 17, 7), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.SECOND),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 17, 8), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.THIRD),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 44, 6), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.FOURTH),
            Arguments.of(Arrays.asList(1, 2, 3, 14, 27, 38), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.FIFTH),
            Arguments.of(Arrays.asList(1, 2, 23, 14, 27, 38), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.NONE),
            Arguments.of(Arrays.asList(1, 22, 23, 14, 27, 38), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.NONE),
            Arguments.of(Arrays.asList(11, 22, 23, 14, 27, 38), Arrays.asList(1, 2, 3, 4, 17, 16), 7, LottoReward.NONE)
        );
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복된 경우")
    void checkDuplicateNumber() {
        Lotto winningLotto = LottoFactory.createLotto(Arrays.asList(12, 23, 6, 44, 17, 16));

        LottoNumber bonusNumber = LottoNumber.valueOf(12);

        assertThatThrownBy(() -> new WinningLotto(winningLotto, bonusNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 유효한 경우")
    void checkValidDuplicateNumber() {
        Lotto winningLotto = LottoFactory.createLotto(Arrays.asList(12, 23, 6, 44, 17, 16));

        LottoNumber bonusNumber = LottoNumber.valueOf(13);

        assertThat(new WinningLotto(winningLotto, bonusNumber)).isNotNull();
    }
}
