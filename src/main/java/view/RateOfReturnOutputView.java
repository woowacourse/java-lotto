package view;

public class RateOfReturnOutputView implements OutputView<Double> {

    private static final String TOTAL_RETURN_MESSAGE = "총 수익률은 ";
    private static final String RATE_FORMAT = "%.2f";
    private static final String TOTAL_RETURN_END_MESSAGE = "입니다.";

    @Override
    public void printOutputData(final Double rateOfReturn) {
        System.out.println(TOTAL_RETURN_MESSAGE + String.format(RATE_FORMAT, rateOfReturn) + TOTAL_RETURN_END_MESSAGE);
    }
}

