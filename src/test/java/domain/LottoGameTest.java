package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.DefaultWinningPrizeStrategy;
import domain.strategy.WinningPrizeStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {

    private final WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();
    private final NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private LottoGame lottoGame;

    @AfterEach
    void afterEach() {
        lottoGame = null;
    }


}