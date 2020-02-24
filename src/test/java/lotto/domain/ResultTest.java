package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class ResultTest {
    List<Integer> inputNumbers;

    @BeforeEach
    void initiate() {
        inputNumbers = new ArrayList<>();
        inputNumbers.add(1);
        inputNumbers.add(2);
        inputNumbers.add(3);
        inputNumbers.add(4);
        inputNumbers.add(5);
        inputNumbers.add(6);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateEarningRate() {
        Result result = new Result();
        PurchaseAmount purchaseAmount = PurchaseAmount.calculate(1000);
        Lotto lotto = new Lotto(inputNumbers);
        PurchaseLottos purchaseLottos = new PurchaseLottos(Arrays.asList(lotto));
        WinningRule winningRule = new WinningRule(inputNumbers,7);
        result.calculateRank(purchaseLottos,winningRule);
        assertThat(result.calculateEarningRate(purchaseAmount)).isEqualTo(199999900);
    }
}
