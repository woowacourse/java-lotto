package lotto.controller;

import lotto.dto.LottoStatisticsResponse;

public interface WinningController {
    LottoStatisticsResponse compareWinningNumber(String inputWinningNumber, String bonusBall);
}
