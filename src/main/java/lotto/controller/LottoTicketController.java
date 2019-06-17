package lotto.controller;

import lotto.application.lottoticket.LottoTicketService;
import lotto.controller.util.JsonUtil;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.LottoTicketsDTO;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketController {
    public static final String PATH_NUM_OF_MANUAL_LOTTO = "/num-of-manual-lotto";
    public static final String PATH_MANUAL_LOTTO = "/manual-lotto";
    public static final String PATH_AUTOMATIC_LOTTO = "/automatic-lotto";

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
        LottoTicketDTO lottoTicketDto = LottoTicketService.getLottoTicketDto(lottoTicket);
        LottoTicketService.savePurchasedLottoTicket(lottoTicketDto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketDto);
    };

    public static final Route fetchAutomaticLotto = (req, res) -> {
        String numOfAutomaticLotto = req.queryParams("numOfAutomaticLotto");

        LottoTickets lottoTickets = LottoTicketService.getAutomaticLottoTickets(numOfAutomaticLotto);

        LottoTicketsDTO lottoTicketsDto = LottoTicketService.getLottoTicketsDto(lottoTickets);
        LottoTicketService.savePurchasedLottoTickets(lottoTicketsDto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketsDto);
    };

    private LottoTicketController() {
    }
}
