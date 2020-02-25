package lotto.parser;

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
}
