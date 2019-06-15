package lotto;

import com.woowacourse.lotto.controller.LottoResultController;
import com.woowacourse.lotto.controller.UserLottoController;
import com.woowacourse.lotto.controller.WinningLottoController;
import com.woowacourse.lotto.domain.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static com.woowacourse.lotto.view.OutputView.*;
import static spark.Spark.*;

public class WebUILottoApplication {
	private static final String NEW_LINE = "<br>";
	private static final WinningLottoController winningLottoController = new WinningLottoController();
	private static final UserLottoController userLottoController = new UserLottoController();
	private static final LottoResultController lottoResultController = new LottoResultController();

	public static void main(String[] args) {
		staticFiles.location("/static");

		get("/", ((request, response) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "/index.html");
		}));

		post("/inputCountOfLotto", ((request, response) -> userLottoController.inputCountOfLotto(request, response)));

		post("inputWinningLotto", (request, response) -> winningLottoController.inputWinningLotto(request, response));

		post("/generateLottos", (request, response) -> userLottoController.generateUserLotto(request, response));

		post("/matchLotto", (request, response) -> lottoResultController.matchLotto(request, response));

		get("/lottoResult", (request, response) -> lottoResultController.getLottoResult(request, response));

		post("/searchLottoResult", ((request, response) -> lottoResultController.searchLottoResult(request, response)));
	}

	public static void printLottoResult(Map<LottoRank, Integer> ranks, StringBuilder stringBuilder) {
		for (LottoRank lottoRank : ranks.keySet()) {
			printRankResult(ranks, stringBuilder, lottoRank);
		}
	}

	private static void printRankResult(Map<LottoRank, Integer> ranks, StringBuilder stringBuilder, LottoRank lottoRank) {
		if (lottoRank == LottoRank.SECOND) {
			stringBuilder.append(String.format(PRINT_SECOND_OF_LOTTO + NEW_LINE, lottoRank.getCount(), lottoRank.getPrice(), ranks.get(lottoRank)));
			return;
		}
		stringBuilder.append(String.format(PRINT_RESULT_OF_LOTTO + NEW_LINE, lottoRank.getCount(), lottoRank.getPrice(), ranks.get(lottoRank)));
	}

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
