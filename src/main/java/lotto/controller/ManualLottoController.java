package lotto.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.application.LottoSession;
import lotto.application.lottoticket.ManualLottoService;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.LottoTicketDto;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ManualLottoController {
    public static final Route fetchNumOfManualLotto = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        String num = req.queryParams("num");

        long numOfManualLotto = ManualLottoService.getNumOfManualLotto(num, LottoSession.getNumOfLotto());
        model.put("numOfManualLotto", numOfManualLotto);
        return render(model, "num-of-manual-lotto.html");
    };

    public static final Route fetchManualLotto = (req, res) -> {
        List<String> numbers = new ArrayList<>();
        numbers.add(req.queryParams("firstNum"));
        numbers.add(req.queryParams("secondNum"));
        numbers.add(req.queryParams("thirdNum"));
        numbers.add(req.queryParams("fourthNum"));
        numbers.add(req.queryParams("fifthNum"));
        numbers.add(req.queryParams("sixthNum"));

        LottoTicket lottoTicket = ManualLottoService.getManualLotto(numbers);
        LottoSession.addManualLottoTicket(lottoTicket);

        LottoTicketDto lottoTicketDto = ManualLottoService.getManualLottoDto(lottoTicket);

        res.type("application/json");
        Gson gson = new GsonBuilder().create();
        return gson.toJson(lottoTicketDto);
    };

    private ManualLottoController() {
    }
}
