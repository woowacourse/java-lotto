package lotto.controller;

import lotto.application.lottoticket.LottoTicketService;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.dto.LottoTicketDto;
import lotto.domain.lottoticket.dto.LottoTicketsDto;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketController {
    public static final Route fetchNumOfManualLotto = (req, res) -> {
        String num = req.queryParams("numOfManualLotto");
        String numOfLotto = req.queryParams("numOfLotto");

        long numOfManualLotto = LottoTicketService.getNumOfManualLotto(num, numOfLotto);
        JsonUtil.setResponseTypeForJson(res);
        return "{\"numOfManualLotto\":\"" + numOfManualLotto + "\"}";
    };

    public static final Route fetchManualLotto = (req, res) -> {
        List<String> numbers = new ArrayList<>();
        numbers.add(req.queryParams("firstNum"));
        numbers.add(req.queryParams("secondNum"));
        numbers.add(req.queryParams("thirdNum"));
        numbers.add(req.queryParams("fourthNum"));
        numbers.add(req.queryParams("fifthNum"));
        numbers.add(req.queryParams("sixthNum"));

        LottoTicket lottoTicket = LottoTicketService.makeLottoTicket(numbers);
        LottoTicketDto lottoTicketDto = LottoTicketService.getLottoTicketDto(lottoTicket);
        LottoTicketService.savePurchasedLottoTicket(lottoTicketDto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketDto);
    };

    public static final Route fetchAutomaticLotto = (req, res) -> {
        String numOfAutomaticLotto = req.queryParams("numOfAutomaticLotto");

        LottoTickets lottoTickets = LottoTicketService.getAutomaticLottoTickets(numOfAutomaticLotto);

        LottoTicketsDto lottoTicketsDto = LottoTicketService.getLottoTicketsDto(lottoTickets);
        LottoTicketService.savePurchasedLottoTickets(lottoTicketsDto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketsDto);
    };

    private LottoTicketController() {
    }
}
