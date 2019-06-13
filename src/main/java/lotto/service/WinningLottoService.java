package lotto.service;

import lotto.utils.ViewUtils;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.service.LottoService.NUMBERS;

public class WinningLottoService {

    public static Route makeWinningLottoPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put(NUMBERS, req.session().attribute(NUMBERS));
        return ViewUtils.render(model, "winningLotto.html");
    };
}
