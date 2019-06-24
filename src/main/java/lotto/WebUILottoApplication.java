package lotto;

import com.woowacourse.lotto.controller.LottoResultController;
import com.woowacourse.lotto.controller.UserLottoController;
import com.woowacourse.lotto.controller.WinningLottoController;
import com.woowacourse.lotto.view.OutputViewWeb;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
	private static final WinningLottoController winningLottoController = new WinningLottoController();
	private static final UserLottoController userLottoController = new UserLottoController();
	private static final LottoResultController lottoResultController = new LottoResultController();

	public static void main(String[] args) {
		staticFiles.location("/static");

		get("/", ((request, response) -> OutputViewWeb.render(new HashMap<>(), "/index.html")));

		post("/inputCountOfLotto", ((request, response) -> userLottoController.inputCountOfLotto(request)));

		post("inputWinningLotto", (request, response) -> winningLottoController.inputWinningLotto(request));

		post("/generateLottos", (request, response) -> userLottoController.generateUserLotto(request));

		post("/matchLotto", (request, response) -> lottoResultController.matchLotto(request));

		get("/lottoResult", (request, response) -> lottoResultController.getLottoResult(request));

		post("/searchLottoResult", ((request, response) -> lottoResultController.searchLottoResult(request)));
	}
}
