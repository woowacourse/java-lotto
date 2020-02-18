package lotto.model;

public class winNumber {
    private String winNumbers;
    public winNumber(String winNumbers) {
        isNullOrEmptyString(winNumbers);
        this.winNumbers = winNumbers;
    }

    private void isNullOrEmptyString(String winNumbers) {
        if (winNumbers == null || winNumbers == "") {
            throw new NullPointerException("당첨 번호에 null 혹은 빈문자열을 입력할 수 없습니다.");
        }
    }
}
