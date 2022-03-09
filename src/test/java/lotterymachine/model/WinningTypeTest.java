package lotterymachine.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"false:0:INVALID", "false:3:THREE", "false:4:FOUR", "false:5:FIVE", "true:5:BONUS_FIVE",
            "false:6:SIX"}, delimiter = ':')
    @DisplayName("보너스 볼 보유 여부와 일치하는 로또 번호 개수를 통해 당첨 타입을 확인한다.")
    void find(boolean bonus, int number, String expected) {
        WinningType winningType = WinningType.find(bonus, number);
        assertThat(winningType).isEqualTo(WinningType.valueOf(expected));
    }

    @ParameterizedTest
    @CsvSource(value = {"BONUS_FIVE:true", "FIVE:false"}, delimiter = ':')
    @DisplayName("보너스 볼 당첨 타입인지 판별한다.")
    void isBonus(String winningLottery, boolean expected) {
        WinningType given = WinningType.valueOf(winningLottery);
        assertThat(given.isBonus()).isEqualTo(expected);
    }
}
