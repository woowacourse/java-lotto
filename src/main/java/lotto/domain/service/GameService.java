package lotto.domain.service;

import java.sql.SQLException;

public class GameService {
    public void saveGame(final int newRound, final String manualLottos, final String totalPurchaseCount, final String manualCount, final String bonusNumber, final String winningLottoNumber, final String money) throws SQLException {
        ManualLottoService manualLottoService = new ManualLottoService();
        AutoLottoService autoLottoService = new AutoLottoService();
        WinningLottoService winningLottoService = new WinningLottoService();
        ResultService resultService = new ResultService();

        // 지난 주 당첨 로또 입력
        winningLottoService.addWinningLotto(newRound, bonusNumber, winningLottoNumber);

        // 수동 로또 입력
        if (Integer.parseInt(manualCount) != 0) {
            manualLottoService.addLotto(newRound, manualLottos);
        }

        // 자동 로또 입력
        autoLottoService.addLotto(newRound, totalPurchaseCount, manualCount);

        // 당첨 결과 입력
        resultService.setResult(newRound, Integer.parseInt(money));
    }
}
