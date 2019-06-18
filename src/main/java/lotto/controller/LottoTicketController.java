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

        LottoTicketService lottoTicketService = LottoTicketService.getInstance();
        long numOfManualLotto = lottoTicketService.getNumOfManualLotto(num, numOfLotto);
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

        LottoTicketService lottoTicketService = LottoTicketService.getInstance();
        LottoTicket lottoTicket = lottoTicketService.makeLottoTicket(numbers);
        LottoTicketDTO lottoTicketDto = lottoTicketService.getLottoTicketDto(lottoTicket);
        lottoTicketService.savePurchasedLottoTicket(lottoTicketDto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketDto);
    };

    public static final Route fetchAutomaticLotto = (req, res) -> {
        String numOfAutomaticLotto = req.queryParams("numOfAutomaticLotto");

        LottoTicketService lottoTicketService = LottoTicketService.getInstance();
        LottoTickets lottoTickets = lottoTicketService.getAutomaticLottoTickets(numOfAutomaticLotto);

        LottoTicketsDTO lottoTicketsDto = lottoTicketService.getLottoTicketsDto(lottoTickets);
        lottoTicketService.savePurchasedLottoTickets(lottoTicketsDto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketsDto);
    };

    private LottoTicketController() {
    }
}
