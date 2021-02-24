package lotto.controller;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Scanner;
import lotto.domain.LottoAnnouncement;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Piece;
import lotto.domain.generator.LottoAutoGenerator;
import lotto.domain.generator.LottoManualGenerator;
import lotto.exception.LottoAnnouncementException;
import lotto.exception.LottoException;
import lotto.exception.MoneyException;
import lotto.exception.PieceException;
import lotto.viewer.InputView;
import lotto.viewer.OutputView;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    private final InputView inputView;
    private final OutputView outputView;

    public LottoStore() {
        Scanner scanner = new Scanner(System.in);
        inputView = new InputView(scanner);
        outputView = new OutputView();
    }

    public void process() {
        Lottos lottos = buyLotto();
        LottoAnnouncement lottoAnnouncement = receiveValidLottoAnnouncement();
        LottoResult lottoResult = new LottoResult(lottoAnnouncement, lottos);
        outputView.printLottoStatistics(lottoResult);
    }

    public Lottos buyLotto() {
        Money possessedMoney = receiveValidMoney();
        Piece manualPieces = receiveManualPieces(possessedMoney);
        Piece autoPieces = manualPieces.getAnotherPiece(possessedMoney);
        LottoAutoGenerator lottoAutoGenerator = new LottoAutoGenerator();
        Lottos purchasedLottos = boughtManualLottos(manualPieces);
        purchasedLottos.addExtraPieces(lottoAutoGenerator, autoPieces.getPieceNumber());
        outputView.printPurchasedLottos(purchasedLottos, manualPieces);
        return purchasedLottos;
    }

    private Money receiveValidMoney() {
        Money candidateMoney;
        try {
            candidateMoney = inputView.purchaseMoney();
        } catch (MoneyException moneyException) {
            outputView.printMoneyException(moneyException);
            candidateMoney = receiveValidMoney();
        }
        return candidateMoney;
    }

    private LottoAnnouncement receiveValidLottoAnnouncement() {
        LottoAnnouncement candidateLottoAnnouncement;
        try {
            candidateLottoAnnouncement = inputView.inputAnnouncement();
        } catch (LottoAnnouncementException lottoAnnouncementException) {
            outputView.printLottoAnnouncementException(lottoAnnouncementException);
            candidateLottoAnnouncement = receiveValidLottoAnnouncement();
        }
        return candidateLottoAnnouncement;
    }

    private Piece receiveManualPieces(Money possessedMoney) {
        Piece candidateManualPiece;
        try {
            candidateManualPiece = inputView.inputManualPieces(possessedMoney);
        } catch (PieceException pieceException) {
            outputView.printPieceException(pieceException);
            candidateManualPiece = inputView.inputManualPieces(possessedMoney);
        }
        return candidateManualPiece;
    }

    private Lottos boughtManualLottos(Piece manualPiece) {
        LottoManualGenerator lottoManualGenerator;
        Lottos lottos;
        try {
            lottoManualGenerator = inputView.lottoManualGenerator(manualPiece);
            lottos = new Lottos(lottoManualGenerator, manualPiece.getPieceNumber());
        } catch (LottoException lottoException) {
            outputView.printLottoException(lottoException);
            lottos = boughtManualLottos(manualPiece);
        }
        return lottos;
    }
}
