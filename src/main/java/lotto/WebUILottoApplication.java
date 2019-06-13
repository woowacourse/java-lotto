package lotto;

import com.woowacourse.lotto.dao.DBConnector;
import com.woowacourse.lotto.dao.LottoResultDAO;
import com.woowacourse.lotto.dao.UserLottoDAO;
import com.woowacourse.lotto.dao.WinningLottoDAO;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import com.woowacourse.lotto.utils.StringSeparator;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.*;

import static com.woowacourse.lotto.view.OutputView.*;
import static spark.Spark.*;

public class WebUILottoApplication {
	public static final String PRINT_SUM = "총 당첨금액은 %d원" + " 입니다.";
	private static final String NEW_LINE = "<br>";
	private static final String WHITE_SPACE = "\\r\\n";
	private static DBConnector DBConnector = new DBConnector();
	private static LottoResultDAO lottoResultDAO = new LottoResultDAO(DBConnector.getConnection());
	private static UserLottoDAO userLottoDAO = new UserLottoDAO(DBConnector.getConnection());
	private static WinningLottoDAO winningLottoDAO = new WinningLottoDAO(DBConnector.getConnection());

	public static void main(String[] args) {
		staticFiles.location("/static");

		get("/", ((request, response) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "/index.html");
		}));

		post("/inputCountOfLotto", ((request, response) -> {
			try {
				int purchasedMoney = Integer.parseInt(request.queryParams("lottoMoney"));
				int countOfManualLotto = Integer.parseInt(request.queryParams("countOfManualLotto"));
				LottoMoney lottoMoney = new LottoMoney(purchasedMoney);
				request.session().attribute("countOfManualLotto", countOfManualLotto);
				request.session().attribute("lottoMoney", new LottoMoney(purchasedMoney));
				request.session().attribute("lottosFactory", new LottosFactory(lottoMoney, countOfManualLotto));
				Map<String, Object> model = new HashMap<>();
				return render(model, "/inputWinningLotto.html");
			} catch (Exception e) {
				Map<String, Object> model = new HashMap<>();
				model.put("errorMessage", e.getMessage());
				return render(model, "/index.html");
			}
		}));

		post("inputWinningLotto", (request, response) -> {
			try {
				int bonusBall = Integer.parseInt(request.queryParams("bonusBall"));
				List<String> winningLottoNumbers = StringSeparator.splitString(request.queryParams("winningLotto"));
				WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusBall);
				int round = addWinningLotto(winningLotto);
				int countOfManualLotto = request.session().attribute("countOfManualLotto");
				request.session().attribute("round", round);
				request.session().attribute("winningLotto", winningLotto);

				Map<String, Object> model = new HashMap<>();
				model.put("countOfManualLotto", countOfManualLotto);
				return render(model, "/inputManualLotto.html");
			} catch (Exception e) {
				Map<String, Object> model = new HashMap<>();
				return render(model, "/inputWinningLotto.html");
			}
		});

		post("/generateLottos", (request, response) -> {
			try {
				int countOfManualLotto = request.session().attribute("countOfManualLotto");
				LottoMoney lottoMoney = request.session().attribute("lottoMoney");
				LottosFactory lottosFactory = request.session().attribute("lottosFactory");
				List<String> list = null;
				if (countOfManualLotto > 0) {
					list = Arrays.asList(request.queryParams("manualLottos").split(WHITE_SPACE));
				}
				Lottos lottos = lottosFactory.generateLotto(list);

				List<String> purchasedLotto = new ArrayList<>();
				lottos.getLottos().stream().forEach(x -> purchasedLotto.add(x.toString()));
				addUserLotto(lottos, request.session().attribute("round"));

				request.session().attribute("lottos", lottos);
				Map<String, Object> model = new HashMap<>();
				model.put("countOfManualLotto", countOfManualLotto);
				model.put("countOfAutoLotto", lottoMoney.getCountOfLotto() - countOfManualLotto);
				model.put("purchasedLotto", purchasedLotto);
				return render(model, "/showPurchasedLottos.html");
			} catch (Exception e) {
				Map<String, Object> model = new HashMap<>();
				model.put("errorMessage", e.getMessage());
				return render(model, "/inputManualLotto.html");
			}
		});

		post("/matchLotto", (request, response) -> {
			try {
				WinningLotto winningLotto = request.session().attribute("winningLotto");
				LottoResult lottoResult = new LottoResult(winningLotto, request.session().attribute("lottos"));
				int round = request.session().attribute("round");

				LottoMoney lottoMoney = request.session().attribute("lottoMoney");
				long earningRate = lottoMoney.calculateEarningsRate(lottoResult.sum());
				addLottoResult(round, lottoMoney, lottoResult);
				String matchedResult = findLottoResultRankById(round);

				Map<String, Object> model = new HashMap<>();
				model.put("matchedResult", matchedResult);
				model.put("earningRate", earningRate);
				return render(model, "/showLottoResult.html");
			} catch (Exception e) {
				Map<String, Object> model = new HashMap<>();
				model.put("errorMessage", e.getMessage());
				return render(model, "/showPurchasedLottos.html");
			}
		});

		get("/lottoResult", (request, response) -> {
			try {
				List<Integer> rounds = getLottoRound();
				request.session().attribute("rounds", rounds);
				Map<String, Object> model = new HashMap<>();
				model.put("rounds", rounds);
				return render(model, "/searchLottoResult.html");
			} catch (Exception e) {
				Map<String, Object> model = new HashMap<>();
				return render(model, "/index.html");
			}
		});

		post("/searchLottoResult", ((request, response) -> {
			try {
				int selectedRound = Integer.parseInt(request.queryParams("selectedRound"));
				List<Integer> rounds = request.session().attribute("rounds");
				Map<String, Long> sumAndEarningRate = findLottoResultSumAndEarningRateById(selectedRound);
				Map<String, Object> model = new HashMap<>();
				model.put("selectNumber", selectedRound);
				model.put("rounds", rounds);
				model.put("ranks", findLottoResultRankById(selectedRound));
				model.put("earningRate", String.format(PRINT_EARNINGS_RATE + NEW_LINE, sumAndEarningRate.get("earningRate")));
				model.put("sum", String.format(PRINT_SUM + NEW_LINE, sumAndEarningRate.get("sum")));
				return render(model, "/searchLottoResult.html");
			} catch (Exception e) {
				Map<String, Object> model = new HashMap<>();
				model.put("errorMessage", e.getMessage());
				return render(model, "/searchLottoResult.html");
			}
		}));
	}

	private static void printLottoResult(Map<LottoRank, Integer> ranks, StringBuilder stringBuilder) {
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

	private static String findLottoResultRankById(int round) throws SQLException {
		Map<LottoRank, Integer> ranks = lottoResultDAO.findLottoResultRankById(round);
		StringBuilder stringBuilder = new StringBuilder();
		printLottoResult(ranks, stringBuilder);
		return stringBuilder.toString();
	}

	private static Map<String, Long> findLottoResultSumAndEarningRateById(int selectedRound) throws SQLException {
		return lottoResultDAO.findSumAndEarningRateById(selectedRound);
	}

	private static List<Integer> getLottoRound() throws SQLException {
		return winningLottoDAO.getLottoRound();
	}

	private static void addLottoResult(int round, LottoMoney lottoMoney, LottoResult lottoResult) throws SQLException {
		lottoResultDAO.addLottoResult(round, lottoMoney, lottoResult);
	}

	private static void addUserLotto(Lottos lottos, int round) throws SQLException {
		List<Lotto> lotto = lottos.getLottos();
		for (int i = 0; i < lotto.size(); i++) {
			userLottoDAO.addUserLotto(lotto.get(i), round);
		}
	}

	private static int addWinningLotto(WinningLotto winningLotto) throws SQLException {
		return winningLottoDAO.addWinningLotto(winningLotto);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
