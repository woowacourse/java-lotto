package lotto;

import com.google.gson.Gson;
import lotto.domain.Lotto;
import lotto.domain.LottoGameResult;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.view.LottosDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
	private static final int START_COUNT = 0;
//	private static final Test controller = new Test();

	public static void main(String[] args) {
		final Gson gson = new Gson();
		externalStaticFileLocation("src/main/resources/templates");
		Test controller = new Test();
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "form.html");
		});

		post("/lottos", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			InputParser parser = new InputParser();
//			LottoService service = new LottoService(Integer.parseInt(req.queryParams("money")));
			controller.injectMoney(Integer.parseInt(req.queryParams("money")));
			Lotto lotto = parser.makeLotto(req.queryParams("lotto"));
			controller.buy(lotto);

//			service.buy(lotto);
			int autoCount = controller.assignAutoPurchaseCount();
			model.put("manualCount", 1);
			model.put("autoCount", autoCount);

			DtoConverter converter = new DtoConverter();
			LottosDto dtos = converter.convertLottosToDto(controller.getLottos());
//			model.put("lottoList", gson.toJson(dtos.getLottos()));
			model.put("lottoList", dtos.getLottos());

			return render(model, "form2.html");
		});

		post("/winning", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			InputParser parser = new InputParser();
			Lotto lotto = parser.makeLotto(req.queryParams("winninglotto"));
			LottoNumber lottoNumber = parser.makeLottoNumber(Integer.parseInt(req.queryParams("bonusnumber")));
			WinningLotto winningLotto = WinningLotto.of(lotto, lottoNumber);
			LottoGameResult gameResult = controller.gameResult();
			gameResult.match(winningLotto);
			model.put("profit", String.format("%.1f",gameResult.profit(1000)));
			model.put("stat", controller.showGameResult(gameResult));
			return render(model, "result.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
