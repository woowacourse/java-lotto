package lotto.Controller;

import lotto.dto.LottosDTO;
import lotto.service.LottoService;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class LottosController {

    private final static LottoService lottoService = new LottoService();

    public static final Route CREATE_LOTTOS = (request, response) -> {
        LottosDTO.Create lottosDto = lottoService.createLottos(request.queryParams("manualLottoNumbers"));

        Map<String, Object> model = new HashMap<>();
        model.put("lottosDto", lottosDto);
        return render(model, "lottos.html");
    };
}
