package lotto.controller;

import lotto.application.lottoresult.LottoResultService;
import lotto.application.lottoticket.LottoTicketService;
import lotto.controller.util.JsonUtil;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoresult.LottoStatistics;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.WinningLottoDTO;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class WinningLottoController {
    public static final String PATH_WINNING_LOTTO = "/winning-lotto";

    public static final Route fetchWinningLotto = (req, res) -> {
        List<String> numbers = new ArrayList<>();
        numbers.add(req.queryParams("firstNum"));
        numbers.add(req.queryParams("secondNum"));
        numbers.add(req.queryParams("thirdNum"));
        numbers.add(req.queryParams("fourthNum"));
        numbers.add(req.queryParams("fifthNum"));
        numbers.add(req.queryParams("sixthNum"));

        LottoTicketService lottoTicketService = LottoTicketService.getInstance();
        LottoTicket winningTicket = lottoTicketService.makeLottoTicket(numbers);
        LottoNumber bonusBall = lottoTicketService.makeBonusBall(req.queryParams("bonusNum"));

        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);
        LottoResultService lottoResultService = LottoResultService.getInstance();
        LottoStatistics lottoStatistics = lottoResultService.calculateLottoStatistics(winningLotto);
        lottoResultService.saveLottoStatistics(lottoStatistics);

        WinningLottoDTO winningLottoDto = lottoResultService.getWinningLottoDto(winningLotto);
        lottoResultService.saveWinningLotto(winningLottoDto);

        return JsonUtil.convertDtoToJsonStringWith(res, winningLottoDto);
    };

    private WinningLottoController() {
    }
}
