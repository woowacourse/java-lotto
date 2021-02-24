package lotto.controller;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Scanner;
import lotto.domain.LottoAnnouncement;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Piece;
import lotto.domain.generator.LottoAutoGenerator;
import lotto.domain.generator.LottoManualGenerator;
import lotto.exception.LottoAnnouncementException;
import lotto.exception.LottoException;
import lotto.exception.MoneyException;
import lotto.exception.PieceException;
import lotto.viewer.AnnouncementInputView;
import lotto.viewer.LottoGeneratorInputView;
import lotto.viewer.MoneyInputView;
import lotto.viewer.OutputView;
import lotto.viewer.PieceInputView;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;
    private static final int DECIMAL_TRIM_NUMERATOR = 100;
    private static final double DECIMAL_TRIM_DENOMINATOR = 100.00;

    private final AnnouncementInputView announcementInputView;
    private final LottoGeneratorInputView lottoGeneratorInputView;
    private final MoneyInputView moneyInputView;
    private final PieceInputView pieceInputView;
    private final OutputView outputView;

    public LottoStore() {
        Scanner scanner = new Scanner(System.in);
        pieceInputView = new PieceInputView(scanner);
        moneyInputView = new MoneyInputView(scanner);
        announcementInputView = new AnnouncementInputView(scanner);
        lottoGeneratorInputView = new LottoGeneratorInputView(scanner);
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
        Piece manualPieces = receiveManualPieces(possessedMoney);
        Piece autoPieces = new Piece(possessedMoney, manualPieces.getAnotherPiece(possessedMoney));
        LottoAutoGenerator lottoAutoGenerator = new LottoAutoGenerator();
        Lottos purchasedLottos = boughtLottos(manualPieces);
        purchasedLottos.addExtraPieces(lottoAutoGenerator, autoPieces.getPiece());
        outputView.printPurchasedLottos(purchasedLottos, manualPieces);
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
        } catch (MoneyException moneyException) {
            outputView.printMoneyException(moneyException);
            candidateMoney = receiveValidMoney();
        }
        return candidateMoney;
    }

    private LottoAnnouncement receiveValidLottoAnnouncement() {
        LottoAnnouncement candidateLottoAnnouncement;
        try {
            candidateLottoAnnouncement = announcementInputView.inputAnnouncement();
        } catch (LottoAnnouncementException lottoAnnouncementException) {
            outputView.printLottoAnnouncementException(lottoAnnouncementException);
            candidateLottoAnnouncement = receiveValidLottoAnnouncement();
        }
        return candidateLottoAnnouncement;
    }

    private Piece receiveManualPieces(Money possessedMoney) {
        Piece candidateManualPiece;
        try {
            candidateManualPiece = pieceInputView.inputManualPieces(possessedMoney);
        } catch (PieceException pieceException) {
            outputView.printPieceException(pieceException);
            candidateManualPiece = pieceInputView.inputManualPieces(possessedMoney);
        }
        return candidateManualPiece;
    }

    private Lottos boughtLottos(Piece manualPiece) {
        LottoManualGenerator lottoManualGenerator;
        Lottos lottos;
        try {
            lottoManualGenerator = lottoGeneratorInputView.lottoManualGenerator(manualPiece);
            lottos = new Lottos(lottoManualGenerator, manualPiece.getPiece());
        } catch (LottoException lottoException) {
            outputView.printLottoException(lottoException);
            lottos = boughtLottos(manualPiece);
        }
        return lottos;
    }
}
