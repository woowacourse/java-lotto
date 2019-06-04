package lotto;

import com.google.gson.Gson;
import com.woowacourse.lotto.controller.LottoWebController;
import com.woowacourse.lotto.domain.*;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();

        staticFiles.location("/public");

        get("/", LottoWebController::index);
        post("/buy", LottoWebController::buyLotto, gson::toJson);
    }
}
