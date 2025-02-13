package view;

public class InputValidator {

    //TODO: 예외메세지 작성

    public void validatePurchaseAmount(String input) {
        if(!isNumber(input)) {
            throw new IllegalArgumentException();
        }

        int purchaseAmount = Integer.parseInt(input);
        if(purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException();
        }
    }

    public void validateWinningNumber(String input) {
        String[] winningNumbers = input.split(",");
        if(winningNumbers.length != 6) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
