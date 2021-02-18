package lotto;

import lotto.domain.Result;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class LottoApplication {
    public static void main(String[] args) {
        // Machine를 생성
        // Machine에 돈을줌 -> List<LottoTickets>
        //List<LottoTickets> 뷰에 전달

        // Machine에게 메시지 -> 결과 달라 : 등수에 따른 갯수(Map), 수익률을 알려줌
        // 뷰에 결과 전달

    }

}
