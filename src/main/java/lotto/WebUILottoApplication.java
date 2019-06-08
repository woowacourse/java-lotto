package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
	public static void main(String[] args) {

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "form.html");
		});

		post("/test", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("money", req.queryParams("money"));
			return render(model, "form.html");
		});

		post("/lottos", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("number1", req.queryParams("number1"));
			model.put("number2", req.queryParams("number2"));
			model.put("number3", req.queryParams("number3"));
			model.put("number4", req.queryParams("number4"));
			model.put("number5", req.queryParams("number5"));
			model.put("number6", req.queryParams("number6"));
			model.put("bonus", req.queryParams("bonus"));
			req.queryParams("number1");
			return render(model, "form.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
