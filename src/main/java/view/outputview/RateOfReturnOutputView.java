package view.outputview;

public class RateOfReturnOutputView implements OutputView<Double> {
    private static final String TOTAL_RETURN_MESSAGE = "총 수익률은 %.2f입니다.\n";

    @Override
    public void showOutputData(Double rateOfReturn) {
        System.out.printf(TOTAL_RETURN_MESSAGE, rateOfReturn);
    }
}

