package lotto.service;

import lotto.utils.ViewUtils;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class WinningLottoService {

    public static Route makeWinningLottoPage = (req,res) ->{
        Map<String, Object> model = new HashMap<>();
        model.put("numbers", req.session().attribute("numbers"));
        return ViewUtils.render(model, "winningLotto.html");
    };
}
