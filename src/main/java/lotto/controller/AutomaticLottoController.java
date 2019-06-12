package lotto.controller;

import lotto.application.lottoticket.AutomaticLottoService;
import lotto.domain.lottoticket.dto.LottoTicketsDto;
import spark.Route;

public class AutomaticLottoController {
    public static final Route fetchAutomaticLotto = (req, res) -> {
        String numOfAutomaticLotto = req.queryParams("numOfAutomaticLotto");

        LottoTicketsDto lottoTicketsDto = AutomaticLottoService.getAutomaticLottoTickets(numOfAutomaticLotto);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoTicketsDto);
    };

    private AutomaticLottoController() {
    }
}
