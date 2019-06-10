package com.woowacourse.lotto.controller;

import com.google.gson.Gson;
import com.woowacourse.lotto.controller.dto.AutoLottoBuyingRequestDto;
import com.woowacourse.lotto.controller.dto.LottoDrawingRequestDto;
import com.woowacourse.lotto.controller.dto.ManualLottoBuyingRequestDto;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.service.LottoService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoWebController {
    private static final LottoService lottoService;

    static {
        lottoService = new LottoService();
    }

    public static String index(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("minBuyingMoney", Lotto.UNIT_PRICE);
        return render(model, "index.html");
    }

    /***
     * 자동 로또 구입 API
     * @param req
     * 요청 JSON 예시:
     * {
     *     "quantity": 2
     * }
     * @param res
     * 응답 JSON 예시:
     * {
     *     "result": "ok",
     *     "lottos": [
     *         {
     *             "id": 10,
     *             "number0": 4,
     *             "number1": 7,
     *             "number2": 27,
     *             "number3": 33,
     *             "number4": 41,
     *             "number5": 42,
     *             "number6": 0,
     *             "price": 1000,
     *             "regDate": {
     *                 "date": {
     *                     "year": 2019,
     *                     "month": 6,
     *                     "day": 6
     *                 },
     *                 "time": {
     *                     "hour": 15,
     *                     "minute": 11,
     *                     "second": 30,
     *                     "nano": 0
     *                 }
     *             }
     *         },
     *         // ...
     *     ]
     * }
     */
    public static Map<String, Object> buyAutoLotto(Request req, Response res) {
        try {
            return handleAutoBuyingRequest(new Gson().fromJson(req.body(), AutoLottoBuyingRequestDto.class));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Map<String, Object> resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", e.getMessage());
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> resMap = createResMapWithResult(ResultState.ERROR);
            resMap.put("message", e.getMessage());
            return resMap;
        }
    }

    private static Map<String, Object> handleAutoBuyingRequest(AutoLottoBuyingRequestDto request) {
        LottoQuantity quantity = LottoQuantity.of(request.getQuantity());
        NumberGenerator numberGenerator = new RandomNumberGenerator(LottoNumber.LOTTO_NUMBER_MIN, LottoNumber.LOTTO_NUMBER_MAX);

        Map<String, Object> resMap = createResMapWithResult(ResultState.OK);
        resMap.put("lottos", IntStream.of(quantity.toInt())
            .mapToObj(i -> LottoFactory.createLotto(numberGenerator))
            .map(lottoService::addLotto)
            .collect(Collectors.toList()));
        return resMap;
    }

    /***
     * 수동 로또 구입 API
     * @param req
     * 요청 JSON 예시:
     * {
     *     "numbers": [
     *     	[1, 2, 3, 4, 5, 6, 7],
     *     	[2, 6, 10, 11, 23, 32]
     *     ]
     * }
     * @param res {@link #buyAutoLotto(Request, Response)}의 응답 예시 참고.
     */
    public static Map<String, Object> buyManualLotto(Request req, Response res) {
        try {
            return handleManualBuyingRequest(new Gson().fromJson(req.body(), ManualLottoBuyingRequestDto.class));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Map<String, Object> resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", e.getMessage());
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> resMap = createResMapWithResult(ResultState.ERROR);
            resMap.put("message", e.getMessage());
            return resMap;
        }
    }

    private static Map<String, Object> handleManualBuyingRequest(ManualLottoBuyingRequestDto request) {
        Map<String, Object> resMap = createResMapWithResult(ResultState.OK);
        resMap.put("lottos", request.getNumbers().stream()
            .map(LottoNumberGroup::of)
            .map(LottoFactory::createLotto)
            .map(lottoService::addLotto)
            .collect(Collectors.toList()));
        return resMap;
    }

    /**
     * 구입한 로또를 당첨 번호와 함께 추첨
     *
     * @param req JSON 요청 예시:
     *            {
     *            "lottos": [1, 2, 3], // 주의: 로또 번호가 아니라 생성된 로또의 id임
     *            "winningNumbers": [1, 2, 3, 4, 5, 6],
     *            "winningBonusNumber": 7
     *            }
     * @param res JSON 응답 예시:
     *            {
     *            "result": "ok",
     *            "aggregation": {
     *            "id": 13,
     *            "lottoRound": 1,
     *            "cntFirst": 1,
     *            "cntSecond": 0,
     *            "cntThird": 0,
     *            "cntFourth": 0,
     *            "cntFifth": 0,
     *            "cntNone": 1,
     *            "prizeMoneySum": 2000000000,
     *            "regDate": {
     *            // ...
     *            }
     *            }
     *            }
     */
    public static Map<String, Object> draw(Request req, Response res) {
        try {
            return handleLottoDrawingRequest(new Gson().fromJson(req.body(), LottoDrawingRequestDto.class));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Map<String, Object> resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", e.getMessage());
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> resMap = createResMapWithResult(ResultState.ERROR);
            resMap.put("message", e.getMessage());
            return resMap;
        }
    }

    private static Map<String, Object> handleLottoDrawingRequest(LottoDrawingRequestDto request) {
        assertLottoIdsAreExist(request.getLottos());
        WinningLotto winningLotto = new WinningLotto(LottoNumberGroup.of(request.getWinningNumbers()), LottoNumber.of(request.getWinningBonusNumber()));
        WinningAggregator aggregator = new WinningAggregator();
        request.getLottos().stream()
            .map(lottoService::findLottoById)
            .map(Lotto::from)
            .map(winningLotto::match)
            .forEach(aggregator::addResult);
        Map<String, Object> resMap = createResMapWithResult(ResultState.OK);
        resMap.put("aggregation", lottoService.addAggregation(aggregator, winningLotto, request.getLottos()));
        return resMap;
    }

    private static void assertLottoIdsAreExist(List<Long> lottoIds) {
        // 로또 id가 존재하는지 확인
        lottoIds.forEach(lottoService::findLottoById);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Map<String, Object> createResMapWithResult(ResultState state) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", state.getResultString());
        return map;
    }

    public static Map<String, Object> retrieveSingleResult(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            System.out.println("id: " + req.params("id"));
            AggregationDto aggregation = lottoService.findAggregationById(Long.parseLong(req.params("id")));
            resMap = createResMapWithResult(ResultState.OK);
            resMap.put("aggregation", aggregation);
        } catch (NumberFormatException e) {
            resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", "올바르지 않은 id 형식입니다.");
        } catch (Exception e) {
            resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", e.getMessage());
        }
        return resMap;
    }

    /**
     * 응답 상태를 추상화한 타입
     * OK: 요청이 정상적으로 처리됨
     * FAIL: 클라이언트측 사유에 의한 요청 처리 실패
     * ERROR: 서버측 사유에 의한 요청 처리 실패
     */
    private enum ResultState {
        OK("ok"), FAIL("fail"), ERROR("error");

        private String resultString;

        ResultState(String string) {
            this.resultString = string;
        }

        public String getResultString() {
            return resultString;
        }
    }
}
