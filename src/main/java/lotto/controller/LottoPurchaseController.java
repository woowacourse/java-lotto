package lotto.controller;

import lotto.StandardResponse;
import lotto.StatusResponse;
import lotto.domain.lotto.Lotto;
import lotto.dto.PurchaseDto;
import lotto.service.LottoService;
import lotto.utils.JsonUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoPurchaseController {
    private static LottoService service;
    private static int INTERNAL_SERVER_ERROR_CODE = 500;

    static {
        service = new LottoService();
    }

    public static Route serveInputPage = (Request request, Response response) -> {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("lotto_price", Lotto.LOTTO_PRICE);
        return render(dataMap, "../static/input.html");
    };

    public static Route buyLotto = (request, response) -> {
        response.type("application/json");
        try {
            PurchaseDto purchaseInfo = createPurchaseInfo(request);
            purchaseInfo = service.buyLotto(purchaseInfo);
            return new StandardResponse(StatusResponse.SUCCESS, JsonUtils.toJsonTree(purchaseInfo)).toJson();
        } catch (Exception e) {
            response.status(INTERNAL_SERVER_ERROR_CODE);
            return new StandardResponse(StatusResponse.ERROR, e.getMessage()).toJson();
        }
    };

    private static PurchaseDto createPurchaseInfo(Request request) {
        PurchaseDto purchaseInfo = new PurchaseDto();
        purchaseInfo.setBudget(Integer.parseInt(request.queryParams("budget")));
        purchaseInfo.setAutoCount(Integer.parseInt(request.queryParams("autoCount")));
        purchaseInfo.setManualCount(Integer.parseInt(request.queryParams("manualCount")));
        if (request.queryParamsValues("manualLottos") == null) {
            purchaseInfo.setManualLottos(new ArrayList<>());
            return purchaseInfo;
        }
        purchaseInfo.setManualLottos(Arrays.asList(request.queryParamsValues("manualLottos")));
        return purchaseInfo;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}