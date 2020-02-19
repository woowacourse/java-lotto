package parser;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.lotto.WinningLotto;
import domain.money.LottoMoney;

public class GameParserTest {

    @Test
    void createLottoMoney() {
        String money = "1000";
        GameParser gameParser = new GameParser();
        LottoMoney lottoMoney = gameParser.createMoney(money);
    }

    @Test
    void createLottoMoneyThrowsException() {
        String money = "피곤해요1";
        GameParser gameParser = new GameParser();
        assertThatThrownBy(() -> gameParser.createMoney(money))
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
