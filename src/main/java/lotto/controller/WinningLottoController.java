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
    public static final Route fetchWinningLotto = (req, res) -> {
        List<String> numbers = new ArrayList<>();
        numbers.add(req.queryParams("firstNum"));
        numbers.add(req.queryParams("secondNum"));
        numbers.add(req.queryParams("thirdNum"));
        numbers.add(req.queryParams("fourthNum"));
        numbers.add(req.queryParams("fifthNum"));
        numbers.add(req.queryParams("sixthNum"));

        LottoTicket winningTicket = LottoTicketService.makeLottoTicket(numbers);
        LottoNumber bonusBall = LottoTicketService.makeBonusBall(req.queryParams("bonusNum"));

        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);
        LottoStatistics lottoStatistics = LottoResultService.calculateLottoStatistics(winningLotto);
        LottoResultService.saveLottoStatistics(lottoStatistics);

        WinningLottoDTO winningLottoDto = LottoResultService.getWinningLottoDto(winningLotto);
        LottoResultService.saveWinningLotto(winningLottoDto);

        return JsonUtil.convertDtoToJsonStringWith(res, winningLottoDto);
    };

    private WinningLottoController() {
    }
}
