package lotto.controller;

import lotto.application.LottoSession;
import lotto.application.lottoticket.LottoTicketService;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.WinningLottoDto;
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
        LottoSession.setWinningLotto(winningLotto);

        WinningLottoDto winningLottoDto = LottoTicketService.getWinningLottoDto(winningLotto);

        return JsonUtil.convertDtoToJsonStringWith(res, winningLottoDto);
    };

    private WinningLottoController() {
    }
}
