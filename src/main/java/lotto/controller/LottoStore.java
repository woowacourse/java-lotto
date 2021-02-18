package lotto.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import lotto.domain.LottoAnnouncement;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.viewer.AnnouncementInputView;
import lotto.viewer.MoneyInputView;
import lotto.viewer.OutputView;

public class LottoStore {

    private final static int LOTTO_PRICE = 1000;
    private static final int DECIMAL_TRIM_NUMERATOR = 100;
    private static final double DECIMAL_TRIM_DENOMINATOR = 100.00;

    private final MoneyInputView moneyInputView;
    private final AnnouncementInputView announcementInputView;
    private final OutputView outputView;

    public LottoStore() {
        Scanner scanner = new Scanner(System.in);
        moneyInputView = new MoneyInputView(scanner);
        announcementInputView = new AnnouncementInputView(scanner);
        outputView = new OutputView();
    }

    public void process() {
        Lottos lottos = buyLotto();
        LottoAnnouncement lottoAnnouncement = announcementInputView.inputAnnouncement();
        Map<LottoRank, Integer> lottoResultStatistics = lottos.getStatistics(lottoAnnouncement);
        printLottoResult(lottoResultStatistics, lottos);
    }

    public Lottos buyLotto() {
        Money possessedMoney = moneyInputView.purchaseMoney();
        Lottos purchasedLottos = new Lottos(possessedMoney.getLottoPieces());
        outputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    public void printLottoResult(Map<LottoRank, Integer> lottoResultStatistics, Lottos lottos) {
        double profitRate = calculateProfitRate(lottoResultStatistics, lottos.getSize());
        outputView.printLottoStatistics(lottoResultStatistics, profitRate);
    }

    public double calculateProfitRate(Map<LottoRank, Integer> lottosResult, int lottoPiece) {
        double sum = 0;
        for (Entry<LottoRank, Integer> keyValue : lottosResult.entrySet()) {
            sum += keyValue.getKey().getPrizeMoney() * keyValue.getValue();
        }
        double investCapital = lottoPiece * LOTTO_PRICE;
        double rawProfitRate = sum / investCapital;
        return Math.round(rawProfitRate * DECIMAL_TRIM_NUMERATOR) / DECIMAL_TRIM_DENOMINATOR;
    }
}
