package lotto.Controller;

import lotto.dto.LottosDTO;
import lotto.service.LottoService;
import lotto.util.StringUtil;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class LottosController {
    private static final String NEXT_LINE = "\r\n";
    private final static LottoService lottoService = new LottoService();

    public static final Route CREATE_LOTTOS = (request, response) -> {
        List<String> manualLottoNumbers =
                StringUtil.convertToList(request.queryParams("manualLottoNumbers"), NEXT_LINE);
        LottosDTO.Create lottosDto = lottoService.createLottos(manualLottoNumbers);

        Map<String, Object> model = new HashMap<>();
        model.put("lottosDto", lottosDto);
        return render(model, "lottos.html");
    };
}
