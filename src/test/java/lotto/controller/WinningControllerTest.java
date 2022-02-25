package lotto.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ControllerConfig;
import lotto.dto.LottoStatisticsResponse;

class WinningControllerTest {

    @Test
    @DisplayName("당첨번호와 로또번호를 비교한다.")
    public void compareWinningNumber() {
        // given
        PurchaseController purchaseController = ControllerConfig.getPurchaseController();
        purchaseController.purchase("3000");
        WinningController winningController = ControllerConfig.getWinningController();
        LottoStatisticsResponse lottoStatisticsResponse = winningController.compareWinningNumber("1, 2, 3, 4, 5, 6",
            "7");
        // when

        Assertions.assertAll(
            () -> assertThat(lottoStatisticsResponse.getMoney()).isEqualTo(3000),
            () -> assertThat(
            lottoStatisticsResponse.getResponseCountMap().values()
                .stream()
                .mapToLong(l -> l)
                .sum()
            ).isEqualTo(3)
        );

        // then
    }
}