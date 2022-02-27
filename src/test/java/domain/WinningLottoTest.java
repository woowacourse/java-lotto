package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTest {

    private static WinningLotto winningLotto;

    @BeforeAll
    static void setUp() {
        LottoTicket winningNumbers = createNewLotto(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = LottoNumber.of(7);

        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    private static LottoTicket createNewLotto(int... value) {
        List<LottoNumber> lottoNumbers = Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return LottoTicket.createManualLotto(lottoNumbers);
    }

    @DisplayName("7개 모두 중복되지 않으면 생성 성공")
    @Test
    void constructor_DistinctLottoNumbers_Successful() {
        LottoTicket validLottoTicket = createNewLotto(1, 2, 3, 4, 5, 6);
        LottoNumber validLottoNumber = LottoNumber.of(7);

        assertThatNoException().isThrownBy(() -> new WinningLotto(validLottoTicket, validLottoNumber));
    }

    @DisplayName("보너스 번호중복되면 생성 실패")
    @Test
    void constructor_DuplicateBonusNumber_Fail() {
        LottoTicket invalidLottoTicket = createNewLotto(1, 2, 3, 4, 5, 6);
        LottoNumber invalidLottoNumber = LottoNumber.of(6);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningLotto(invalidLottoTicket, invalidLottoNumber))
                .withMessageMatching("보너스 번호는 당첨 번호와 달라야 합니다.");
    }

    @DisplayName("6개 모두 일치 시 1등")
    @Test
    void getLottoResult_SixMatchingNumbers_ReturnsFirstPrize() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 5, 6);

        assertThat(winningLotto.getLottoResult(lottoTicket)).isEqualTo(LottoResult.FIRST);
    }

    @DisplayName("5개 일치, 보너스 일치 시 2등")
    @Test
    void getLottoResult_FiveMatchingNumbersWithBonus_ReturnsSecondPrize() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 5, 7);

        assertThat(winningLotto.getLottoResult(lottoTicket)).isEqualTo(LottoResult.SECOND);
    }

    @DisplayName("5개 일치 시 3등")
    @Test
    void getLottoResult_FiveMatchingNumbersWithoutBonus_ReturnsThirdPrize() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 5, 11);

        assertThat(winningLotto.getLottoResult(lottoTicket)).isEqualTo(LottoResult.THIRD);
    }

    @DisplayName("4개 일치 시 4등")
    @Test
    void getLottoResult_FourMatchingNumbers_ReturnsFourthPrize() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 11, 12);

        assertThat(winningLotto.getLottoResult(lottoTicket)).isEqualTo(LottoResult.FOURTH);
    }

    @DisplayName("3개 일치 시 5등")
    @Test
    void getLottoResult_ThreeMatchingNumbers_ReturnsFifthPrize() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 11, 12, 13);

        assertThat(winningLotto.getLottoResult(lottoTicket)).isEqualTo(LottoResult.FIFTH);
    }

    @DisplayName("3개 미만 일치 시 낙첨")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(strings = {"11,12,13,14,15,16", "1,11,12,13,14,15", "1,2,11,12,13,14"})
    void getLottoResult_LessThanThreeMatchingNumbers_ReturnsNull(String value) {
        int[] lottoNumbers = Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .mapToInt(i -> i)
                .toArray();

        LottoTicket lottoTicket = createNewLotto(lottoNumbers);

        assertThat(winningLotto.getLottoResult(lottoTicket)).isEqualTo(null);
    }

}
