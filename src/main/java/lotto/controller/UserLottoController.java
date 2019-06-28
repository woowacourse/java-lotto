package lotto.controller;

import lotto.domain.UserLottos;
import lotto.service.UserLottoService;
import lotto.service.UserLottoTranslator;
import lotto.view.WebView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.Map;

public class UserLottoController {

    private UserLottoController() {

    }

    public static Map<String, Object> userLotto(Request request, Response response) {
        UserLottoTranslator presentation = userLottoPresentation(request);
        UserLottos userLottos = UserLottoService.userLottos(presentation);
        return WebView.userLottoJson(userLottos);
    }

    private static UserLottoTranslator userLottoPresentation(Request request) {
        return new UserLottoTranslator(request.queryParams("lottoMoney"),
                request.queryParams("manualCount"), Arrays.asList(request.queryParamsValues("manualLottos")));

    }
}
