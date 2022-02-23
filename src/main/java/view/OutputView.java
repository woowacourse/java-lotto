package view;

import model.LottoDTO;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "개를 구매했습니다";

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printLottos(List<LottoDTO> lottoStorageDTO) {
        System.out.println(lottoStorageDTO.size() + PURCHASE_MESSAGE);
        lottoStorageDTO
                .forEach(lottoDTO -> System.out.println(lottoDTO.getNumbers()));
    }

    public void printResultMessage() {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
    }

    public void printResult(int matchNumber, int value, int count, int bonusMoney) {
        if (value != bonusMoney) {
            System.out.println(matchNumber + "개 일치, 보너스 볼 일치(" + value + "원) - " + count + "개");
            return;
        }

        System.out.println(matchNumber + "개 일치 (" + value + "원) - " + count + "개");

    }

    public void printRateOfReturn(double rateOfReturn) {
        if (rateOfReturn < 1) {
            System.out.println("총 수익률은 " + rateOfReturn + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
            return;
        }
        System.out.println("총 수익률은 " + rateOfReturn + "입니다.(기준이 1이기 때문에 결과적으로 이득라는 의미임)");
    }
}