package lotto.controller;

import lotto.utils.ViewUtils;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.controller.LottoController.NUMBERS;

public class WinningLottoController {

    public static Route makeWinningLottoPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put(NUMBERS, req.session().attribute(NUMBERS));
        return ViewUtils.render(model, "winningLotto.html");
    };
}
