package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.lotto.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import static lotto.view.InputView.REQUEST_INPUT_MANUAL_LOTTO_NUMBERS;
import static lotto.view.InputView.REQUEST_LAST_WIN_LOTTO_NUMBERS;

public class LottoController {

    public void run() {
        try {
            LottoTicketBuyingRequest lottoTicketBuyingRequest = getLottoTicketBuyingRequest();
            LottoTicket lottoTicket = getLottoTicket(lottoTicketBuyingRequest);
            OutputView.printLottoTicket(lottoTicket);
            WinningNumbers winningNumbers = getWinningNumbers();
            OutputView.printResult(matchLottoTicket(lottoTicket, winningNumbers));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private LottoTicketBuyingRequest getLottoTicketBuyingRequest() {
        Money money = new Money(InputView.getMoney());
        LottoAmount numberOfManualLotto = new LottoAmount(InputView.getNumberOfManualLotto());

        LottoTicketBuyingRequest lottoTicketBuyingRequest = new LottoTicketBuyingRequest(money, numberOfManualLotto);
        for (int i = 0; i < numberOfManualLotto.getValue(); i++) {
            List<String> splitLottoNumbersInput = InputView.getSplitLottoNumbers(REQUEST_INPUT_MANUAL_LOTTO_NUMBERS);
            lottoTicketBuyingRequest.submitManualLottoLine(getLottoLine(splitLottoNumbersInput));
        }

        return lottoTicketBuyingRequest;
    }


    private LottoTicket getLottoTicket(LottoTicketBuyingRequest lottoTicketBuyingRequest) {
        return LottoTicketGenerator.getInstance().createLottoTicket(lottoTicketBuyingRequest);
    }

    private WinningNumbers getWinningNumbers() {
        List<String> splitLottoNumbersInput = InputView.getSplitLottoNumbers(REQUEST_LAST_WIN_LOTTO_NUMBERS);
        LottoLine lottoLine = getLottoLine(splitLottoNumbersInput);

        String bonusBallInput = InputView.getBonusLottoNumber();
        LottoNumber bonusLottoNumber = new LottoNumber(bonusBallInput);

        return new WinningNumbers(lottoLine, bonusLottoNumber);
    }

    private LottoLine getLottoLine(List<String> splitLottoNumbersInput) {
        ArrayList<LottoNumber> lottoNumberList = new ArrayList();

        for (int i = 0; i < splitLottoNumbersInput.size(); i++) {
            lottoNumberList.add(new LottoNumber(splitLottoNumbersInput.get(i)));
        }

        return new LottoLine(lottoNumberList);
    }

    private LottoResult matchLottoTicket(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        List<Rank> rankList = lottoTicket
                .matchLottoLines(winningNumbers);
        return new LottoResult(rankList);
    }
}
