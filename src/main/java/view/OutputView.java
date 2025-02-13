package view;

import dto.*;

public class OutputView {

    public void printTicketPurchaseAmount(TicketAmountResponse response) { // TODO : 상수 분리
        System.out.println(response.amount() + "개를 구매했습니다.");
    }

    public void printLottos(LottosResponse response) {
        response.lottos().forEach(this::printLottoNumbers);
    }

    public void printLottoResult(LottoResultsResponse response) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        response.detailResponses().forEach(this::printLottoResultDetail);
    }

    private void printLottoResultDetail(LottoResultDetailResponse response) {
        LottoRankDetailResponse rankDetail = response.rankDetailResponse();
        System.out.printf("%d개 일치", rankDetail.matchNumber());

        if (rankDetail.isBonusNumber()) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.printf(" (%d원)- %d개 \n", rankDetail.price(), response.count());
    }

    private void printLottoNumbers(LottoNumbersResponse response) { // TODO : 상수 분리
        String joined = String.join(", ", response.numbers());
        System.out.println("[" + joined + "]");
    }
}
