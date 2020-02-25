package lotto.parser;

import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameParserTest {

    @Test
    void createLottoMoney() {
        String money = "1000";
        GameParser gameParser = new GameParser();
        assertThat(gameParser.parseInputToInt(money)).isEqualTo(1000);
    }

    @Test
    void createLottoMoneyThrowsException() {
        String money = "피곤해요1";
        GameParser gameParser = new GameParser();
        assertThatThrownBy(() -> gameParser.parseInputToInt(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createWinningLotto() {
        String winningLottoInput = "1,2,3,4,5,6";
        String bonusNumberInput = "7";
        GameParser gameParser = new GameParser();
        WinningLotto winningLotto = gameParser.createWinningLotto(winningLottoInput, bonusNumberInput);
    }
}
