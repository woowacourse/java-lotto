package model.lotto;

import java.util.List;

import model.bonusball.BonusBall;
import model.bonusball.BonusBallDTO;
import model.result.Rank;
import model.result.RateOfReturn;
import model.result.WinningResult;
import model.winningnumber.LottoWinningNumber;
import model.winningnumber.LottoWinningNumberDTO;
import view.OutputView;

public class LottoGame {
    private final OutputView outputView = new OutputView();

    private LottoStorage lottoStorage;
    private RateOfReturn rateOfReturn;
    private LottoWinningNumber lottoWinningNumber;
    private BonusBall bonusBall;

    public LottoStorage makeLottos(String input) {
        LottoCount lottoCount = new LottoCount(input);
        storeMoneyInRateOfReturn(lottoCount);
        lottoStorage = new LottoStorage(lottoCount);
        return lottoStorage;
    }

    private void storeMoneyInRateOfReturn(LottoCount lottoCount) {
        rateOfReturn = new RateOfReturn(lottoCount);
    }

    public void storeWinningNumber(List<String> input) {
        lottoWinningNumber = new LottoWinningNumber(input);
    }

    public void storeBonusBall(String input) {
        bonusBall = new BonusBall(input);
        lottoWinningNumber.validateReduplicationWithBonusBall(input);
    }

    public WinningResult calcLottoWithWinningNumber() {
        return lottoStorage.calcWinningNumber(new BonusBallDTO(bonusBall.getNumber()),
                new LottoWinningNumberDTO(lottoWinningNumber.getWinningNumbers()));
    }

    public double showResult(WinningResult winningResult) {
        for (Rank rank : winningResult.getWinningCount().keySet()) {
            outputView.printResult(rank.getMatchNumber(), rank.getValue(),
                    winningResult.getWinningCount().get(rank), Rank.BONUS.getValue());
        }

        return rateOfReturn.getRateOfReturn(winningResult);
    }
}
