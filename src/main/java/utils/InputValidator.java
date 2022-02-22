package utils;

public class InputValidator {

    private static final String INPUT_IS_NULL = "입력 값은 널 값일 수 없습니다.";
    private static final String INPUT_IS_EMPTY = "입력 값은 비어있을 수 없습니다.";

    public void validateNull(String purchaseAmount){
        if(purchaseAmount == null){
            throw new NullPointerException(INPUT_IS_NULL);
        }
    }

    public void validateEmpty(String purchaseAmount) {
        if (purchaseAmount.isEmpty()) {
            throw new IllegalArgumentException(INPUT_IS_EMPTY);
        }
    }

}
