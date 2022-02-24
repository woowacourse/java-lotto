package view;

import java.util.List;

import model.lotto.LottoDTO;

public class OutputView {
    private static final String PURCHASE_MESSAGE = "개를 구매했습니다";
    private static final String RATE_OF_RETURN_MESSAGE = "당첨 통계";
    private static final String LINE = "---------";
    private static final String RESULT_LOTTO_BONUS_BALL = "%d개 일치, 보너스 볼 일치(%d원) - %d개%n";
    private static final String RESULT_LOTTO_NUMBER = "%d개 일치 (%d원) - %d개%n";
    private static final String RESULT_RATE_OF_RETURN_LOSS = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String RESULT_RATE_OF_RETURN_GAIN = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이득라는 의미임)";

    public void printErrorMessage(String message) {
        printMessage(message);
    }

    public void printLottos(List<LottoDTO> lottoStorageDTO) {
        printMessage(lottoStorageDTO.size() + PURCHASE_MESSAGE);
        lottoStorageDTO
                .forEach(lottoDTO -> System.out.println(lottoDTO.getNumbers()));
    }

    public void printResultMessage() {
        printNewLine();
        printMessage(RATE_OF_RETURN_MESSAGE);
        printMessage(LINE);
    }

    public void printResult(int matchNumber, int value, int count, int bonusMoney) {
        if (value == bonusMoney) {
            System.out.printf(RESULT_LOTTO_BONUS_BALL, matchNumber, value, count);
            return;
        }
        System.out.printf(RESULT_LOTTO_NUMBER, matchNumber, value, count);
    }

    public void printRateOfReturn(double rateOfReturn) {
        if (rateOfReturn < 1) {
            System.out.printf(RESULT_RATE_OF_RETURN_LOSS, rateOfReturn);
            return;
        }
        System.out.printf(RESULT_RATE_OF_RETURN_GAIN, rateOfReturn);
    }

    private void printNewLine() {
        System.out.println();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}