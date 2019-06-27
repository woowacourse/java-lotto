package lotto.domain.service;

public class GameService {

    private static final GameService INSTANCE = new GameService();

    private GameService() {
    }

    public static GameService getInstance() {
        return INSTANCE;
    }

    public static final int NOT_EXIST_MANUALLOTTO = 0;

    public void saveGame(final int newRound, final String manualLottos, final String totalPurchaseCount, final String manualCount, final String bonusNumber, final String winningLottoNumber, final String money) {

        // 지난 주 당첨 로또 입력
        WinningLottoService.getInstance().addWinningLotto(newRound, bonusNumber, winningLottoNumber);

        // 수동 로또 입력
        if (Integer.parseInt(manualCount) != NOT_EXIST_MANUALLOTTO) {
            ManualLottoService.getInstance().addLotto(newRound, manualLottos);
        }

        // 자동 로또 입력
        AutoLottoService.getInstance().addLotto(newRound, totalPurchaseCount, manualCount);

        // 당첨 결과 입력
        ResultService.getInstance().setResult(newRound, Integer.parseInt(money));
    }
}
