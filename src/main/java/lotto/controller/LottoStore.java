package lotto.controller;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Scanner;
import lotto.domain.LottoAnnouncement;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.controller.generator.LottoAutoGenerator;
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
        LottoAnnouncement lottoAnnouncement = receiveValidLottoAnnouncement();
        EnumMap<LottoRank, Integer> lottoResultStatistics = lottos.getStatistics(lottoAnnouncement);
        printLottoResult(lottoResultStatistics, lottos);
    }

    public Lottos buyLotto() {
        Money possessedMoney = receiveValidMoney();
        LottoAutoGenerator lottoAutoGenerator = new LottoAutoGenerator();
        Lottos purchasedLottos =
            new Lottos(lottoAutoGenerator, possessedMoney.getLottoPieces(LOTTO_PRICE));
        outputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    public void printLottoResult(EnumMap<LottoRank, Integer> lottoResultStatistics, Lottos lottos) {
        double profitRate = calculateProfitRate(lottoResultStatistics, lottos.getSize());
        outputView.printLottoStatistics(lottoResultStatistics, profitRate);
    }

    public double calculateProfitRate(EnumMap<LottoRank, Integer> lottosResult, int lottoPiece) {
        double sum = 0;
        for (Entry<LottoRank, Integer> keyValue : lottosResult.entrySet()) {
            sum += keyValue.getKey().getPrizeMoney() * keyValue.getValue();
        }
        double investCapital = lottoPiece * LOTTO_PRICE;
        double rawProfitRate = sum / investCapital;
        return Math.round(rawProfitRate * DECIMAL_TRIM_NUMERATOR) / DECIMAL_TRIM_DENOMINATOR;
    }

    private Money receiveValidMoney() {
        Money candidateMoney;

        try {
            candidateMoney = moneyInputView.purchaseMoney();
        } catch (Exception e) {
            candidateMoney = receiveValidMoney();
        }

        return candidateMoney;
    }

    private LottoAnnouncement receiveValidLottoAnnouncement() {
        LottoAnnouncement candidateLottoAnnouncement;

        try {
            candidateLottoAnnouncement = announcementInputView.inputAnnouncement();
        } catch (Exception e) {
            candidateLottoAnnouncement = receiveValidLottoAnnouncement();
        }

        return candidateLottoAnnouncement;
    }
}
