package com.woowacourse.lotto.controller;

import com.google.gson.Gson;
import com.woowacourse.lotto.controller.dto.AutoLottoBuyingRequestDto;
import com.woowacourse.lotto.controller.dto.LottoBuyingRequestDto;
import com.woowacourse.lotto.controller.dto.LottoDrawingRequestDto;
import com.woowacourse.lotto.controller.dto.ManualLottoBuyingRequestDto;
import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
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
     * 로또 구입 API
     * @param req
     * 요청 JSON 예시:
     * {
     *     "buyingMoney": 14000,
     *     "manualNumbers": [
     *          [1, 2, 3, 4, 5, 6],
     *          // ...
     *     ]
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
    public static Map<String, Object> buyLotto(Request req, Response res) {
        LottoBuyingRequestDto request = new Gson().fromJson(req.body(), LottoBuyingRequestDto.class);
        Map<String, Object> resMap;
        try {
            resMap = handleLottoBuyingRequest(request);
        } catch (IllegalArgumentException e) {
            resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", e.getMessage());
        } catch (Exception e) {
            resMap = createResMapWithResult(ResultState.ERROR);
            resMap.put("message", e.getMessage());
        }
        return resMap;
    }

    private static Map<String, Object> handleLottoBuyingRequest(LottoBuyingRequestDto req) {
        BuyingMoney money = new BuyingMoney(req.getBuyingMoney());
        LottoQuantity totalQuantity = money.getQuantity();
        List<Lotto> lottos = buyManualLotto(req);
        assertBuyingMoneyEnough(totalQuantity, lottos);
        LottoQuantity autoQuantity = LottoQuantity.of(totalQuantity.toInt() - lottos.size());
        lottos.addAll(buyAutoLotto(autoQuantity));
        List<LottoDto> storedLottos = saveLottos(lottos);
        Map<String, Object> resMap = createResMapWithResult(ResultState.OK);
        resMap.put("lottos", storedLottos);
        return resMap;
    }

    private static List<Lotto> buyManualLotto(LottoBuyingRequestDto req) {
        return req.getManualNumbers().stream()
            .map(LottoNumberGroup::of)
            .map(LottoFactory::createLotto)
            .collect(Collectors.toList());
    }

    private static void assertBuyingMoneyEnough(LottoQuantity totalQuantity, List<Lotto> lottos) {
        if (totalQuantity.compareTo(LottoQuantity.of(lottos.size())) == -1) {
            throw new IllegalArgumentException("금액이 부족합니다.");
        }
    }

    private static List<Lotto> buyAutoLotto(LottoQuantity autoQuantity) {
        NumberGenerator numberGenerator = new RandomNumberGenerator(LottoNumber.LOTTO_NUMBER_MIN, LottoNumber.LOTTO_NUMBER_MAX);
        return IntStream.range(0, autoQuantity.toInt())
            .mapToObj(i -> LottoFactory.createLotto(numberGenerator))
            .collect(Collectors.toList());
    }

    private static List<LottoDto> saveLottos(List<Lotto> lottos) {
        return lottos.stream()
                .map(lottoService::addLotto)
                .collect(Collectors.toList());
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
        WinningAggregator aggregator = createAggregation(request.getLottos(), winningLotto);
        Map<String, Object> resMap = createResMapWithResult(ResultState.OK);
        resMap.put("aggregation", lottoService.addAggregation(aggregator, winningLotto, request.getLottos()));
        return resMap;
    }

    private static WinningAggregator createAggregation(List<Long> lottoIds, WinningLotto winningLotto) {
        WinningAggregator aggregator = new WinningAggregator();
        lottoIds.stream()
            .map(lottoService::findLottoById)
            .map(Lotto::from)
            .map(winningLotto::match)
            .forEach(aggregator::addResult);
        return aggregator;
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

    public static Map<String, Object> retrieveSingleAggregation(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            AggregationDto aggregation = lottoService.findAggregationById(Long.parseLong(req.params("id")));
            resMap = createResMapWithResult(ResultState.OK);
            aggregation.setLottos(lottoService.findLottosByAggregationId(aggregation.getId()));
            aggregation.setWinningLotto(lottoService.findWinningLottoByAggregationId(aggregation.getId()));
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

    public static Map<String, Object> retrieveAggregations(Request req, Response res) {
        Map<String, Object> resMap;
        try {
            String queryTop = getQueryOrDefault(req, "top", "5");
            resMap = createResMapWithResult(ResultState.OK);
            resMap.put("aggregations", lottoService.findLatestNAggregation(Integer.parseInt(queryTop)));
        } catch (NumberFormatException e) {
            resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", "인자가 숫자가 아닙니다.");
        } catch (Exception e) {
            resMap = createResMapWithResult(ResultState.FAIL);
            resMap.put("message", e.getMessage());
        }
        return resMap;
    }

    private static String getQueryOrDefault(Request req, String query, String defaultValue) {
        String ret = req.queryParams("top");
        if (ret == null) {
            ret = defaultValue;
        }

        return ret;
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
