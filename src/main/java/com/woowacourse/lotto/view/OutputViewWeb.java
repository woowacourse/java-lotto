package com.woowacourse.lotto.view;

import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static com.woowacourse.lotto.view.OutputViewConsole.PRINT_EARNINGS_RATE;

public class OutputViewWeb {
	private static final String NEW_LINE_FOR_CONSOLE = "\n";
	private static final String NEW_LINE_FOR_WEB = "<br>";
	private static final String PRINT_SUM = "총 당첨금액은 %d 원 입니다.";

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	public static String printLottoResult(Map<String, Object> model) {
		String ranks = String.valueOf(model.get("ranks"));
		model.put("ranks", ranks.replaceAll(NEW_LINE_FOR_CONSOLE, NEW_LINE_FOR_WEB));
		model.put("earningRate", String.format(PRINT_EARNINGS_RATE + NEW_LINE_FOR_WEB, model.get("earningRate")));
		model.put("sum", String.format(PRINT_SUM + NEW_LINE_FOR_WEB, model.get("sum")));
		return render(model, "/searchLottoResult.html");
	}
}
