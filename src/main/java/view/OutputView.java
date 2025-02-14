package view;

import dto.*;

import static global.constant.LottoConstant.NUMBER_DELIMITER;

public class OutputView {

    private static final String PURCHASE_DONE = "개를 구매했습니다.";
    private static final String LOTTO_RESULT_HEADER = "\n당첨 통계\n---------";
    private static final String ROI_RESULT = "총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s라는 의미임)";
    private static final String COLLECT_COUNT = "%d개 일치";
    private static final String COLLECT_BONUS_BALL = ", 보너스 볼 일치";
    private static final String LOTTO_RESULT_DETAIL = " (%d원)- %d개 \n";
    private static final String NUMBER_OPEN_BRACE = "[";
    private static final String NUMBER_CLOSE_BRACE = "]";

    public void printTicketPurchaseAmount(TicketAmountResponse response) {
        System.out.println(response.amount() + PURCHASE_DONE);
    }

    public void printLottos(LottosResponse response) {
        response.lottos().forEach(this::printLottoNumbers);
    }

    public void printLottoResult(LottoResultsResponse response) {
        System.out.println(LOTTO_RESULT_HEADER);
        response.detailResponses().forEach(this::printLottoResultDetail);
    }

    public void printROIResult(ROIResultResponse response) {
        System.out.printf(ROI_RESULT, response.ROI(), response.benefitType());
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    private void printLottoResultDetail(LottoResultDetailResponse response) {
        LottoRankDetailResponse rankDetail = response.rankDetailResponse();
        System.out.printf(COLLECT_COUNT, rankDetail.matchNumber());

        if (rankDetail.isBonusNumber()) {
            System.out.print(COLLECT_BONUS_BALL);
        }
        System.out.printf(LOTTO_RESULT_DETAIL, rankDetail.price(), response.count());
    }

    private void printLottoNumbers(LottoNumbersResponse response) {
        String numbers = String.join(NUMBER_DELIMITER, response.numbers());
        System.out.println(NUMBER_OPEN_BRACE + numbers + NUMBER_CLOSE_BRACE);
    }
}
