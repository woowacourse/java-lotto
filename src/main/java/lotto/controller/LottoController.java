package lotto.controller;

import java.util.List;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;
import lotto.domain.SelfLottoCount;
import lotto.domain.WinningLotto;
import lotto.domain.dto.LottoTicketsDTO;
import lotto.utils.ParseUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String REGEX = ", ";

    public LottoController() {
    }

    public void run() {
        final Payment payment = new Payment(ParseUtils.parseInt(InputView.getInputMoney()));
        final SelfLottoCount selfLottoCount = new SelfLottoCount(payment.count(),
            ParseUtils.parseInt(InputView.getBuySelfLottoCount()));
        final LottoTickets selfLottoTickets = buySelfLottoTickets(selfLottoCount);
        final LottoTickets lottoTickets = new LottoTickets(
            payment.count() - selfLottoCount.getSelfCount(), selfLottoTickets);
        OutputView.printBuyLottoCountMessage(selfLottoCount.getSelfCount(), payment.count());
        OutputView.printLottoTicketsMessage(new LottoTicketsDTO(lottoTickets.getLottoTickets()));
        OutputView.printResultMessage(lottoTickets.getResult(createWinningLotto()), payment);
    }

    private WinningLotto createWinningLotto() {
        OutputView.printWinningLottoMessage();
        String values = InputView.getLottoNumbers();
        List<Integer> numbers = ParseUtils.parseIntegerList(values, REGEX);
        int bonusNumber = ParseUtils.parseInt(InputView.getBonusBallNumber());
        return new WinningLotto(numbers, bonusNumber);
    }

    private LottoTickets buySelfLottoTickets(SelfLottoCount selfLottoCount) {
        if (selfLottoCount.getSelfCount() == 0) {
            return new LottoTickets(0);
        }
        LottoTickets lottoTickets = new LottoTickets(
            InputView.getSelfLottoNumbers(selfLottoCount.getSelfCount())
        );
        return lottoTickets;
    }
}