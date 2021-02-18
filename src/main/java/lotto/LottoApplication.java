package lotto;

import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.Machine;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class LottoApplication {
    public static void main(String[] args) {
        String moneyValue = InputView.getMoneyInput();
        Machine machine = new Machine(moneyValue);

        List<LottoTicket> lottoTickets = machine.buyTickets();

        OutputView.printTickets(lottoTickets);

        String winnerNumbersValue = InputView.getWinningNumbersInput();
        String bonusBallValue = InputView.getBonusBallInput();
        Result result = machine.getResult(winnerNumbersValue, bonusBallValue, lottoTickets);

        OutputView.printResult(result);
    }

}
