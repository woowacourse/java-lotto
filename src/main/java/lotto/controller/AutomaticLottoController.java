package lotto.controller;

import lotto.application.lottoticket.LottoTicketService;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.dto.LottoTicketsDto;
import spark.Route;

public class AutomaticLottoController {
    public static final Route fetchAutomaticLotto = (req, res) -> {
        String numOfAutomaticLotto = req.queryParams("numOfAutomaticLotto");

        LottoTickets lottoTickets = LottoTicketService.getAutomaticLottoTickets(numOfAutomaticLotto);

        LottoTicketsDto lottoTicketsDto = LottoTicketService.getLottoTicketsDto(lottoTickets);
        LottoTicketService.savePurchasedLottoTickets(lottoTicketsDto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketsDto);
    };

    private AutomaticLottoController() {
    }
}
