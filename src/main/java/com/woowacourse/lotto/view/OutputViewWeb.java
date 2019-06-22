package com.woowacourse.lotto.view;

import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class OutputViewWeb {
	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	public static String printLottoResult(Map<String, Object> model) {
		model.put("earningRate", String.format(PRINT_EARNINGS_RATE + NEW_LINE, model.get("earningRate")));
		model.put("sum", String.format(PRINT_SUM + NEW_LINE, model.get("sum")));
		return render(model, "/searchLottoResult.html");
	}
}
