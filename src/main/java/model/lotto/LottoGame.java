package model.lotto;

import java.util.Arrays;
import java.util.List;

import model.bonusball.BonusBall;
import model.result.RateOfReturn;
import model.result.Statistics;
import model.winningnumber.LottoWinningNumber;
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

    public void compareLottoWithWinningNumber() {
        lottoStorage.compare(bonusBall.getBonusBallDTO(), lottoWinningNumber.getWinningNumbersDTO());
    }

    public double showResult() {
        Arrays.stream(Statistics.values())
                .forEach(statistics -> outputView.printResult(statistics.getMatchNumber(), statistics.getValue(),
                        statistics.getCount(), Statistics.BONUS.getValue()));

        return rateOfReturn.getRateOfReturn();
    }
}
