package lotto.controller;

import lotto.config.ServiceConfig;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.WinningTicket;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.StringInputNumberGenerator;
import lotto.dto.LottoStatisticsResponse;
import lotto.service.MoneyService;
import lotto.service.WinningService;
import lotto.utils.IntegerUtils;

public class InputWinningController {

    private final WinningService winningService;
    private final MoneyService moneyService;

    private InputWinningController(WinningService winningService, MoneyService moneyService) {
        this.winningService = winningService;
        this.moneyService = moneyService;
    }

    private static class InputWinningControllerHelper {
        private static final InputWinningController INSTANCE = new InputWinningController(
            ServiceConfig.getWinningService(),
            ServiceConfig.getMoneyService()
        );
    }

    public static InputWinningController getInstance() {
        return InputWinningControllerHelper.INSTANCE;
    }

    public LottoStatisticsResponse compareWinningNumber(String inputWinningNumber, String inputBonusBall) {
        WinningTicket winningTicket = createWinningTicket(inputWinningNumber, inputBonusBall);
        return new LottoStatisticsResponse(winningService.compare(winningTicket), moneyService.inquire());
    }

    private WinningTicket createWinningTicket(String inputWinningNumber, String inputBonusBall) {
        LottoTicket ticket = createTicketFromInput(inputWinningNumber);
        LottoNumber lottoNumber = createLottoNumberFromInput(inputBonusBall);
        return new WinningTicket(ticket, lottoNumber);
    }

    private LottoNumber createLottoNumberFromInput(String inputBonusBall) {
        return LottoNumber.from(IntegerUtils.parse(inputBonusBall));
    }

    private LottoTicket createTicketFromInput(String inputWinningNumber) {
        NumberGenerator generator = new StringInputNumberGenerator(inputWinningNumber);
        return LottoTicket.createTicket(generator);
    }
}
