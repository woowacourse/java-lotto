public class BonusNumberParser {

    public int parse(String number) {
        int bonusNumber = Integer.parseInt(number);
        new LottoNumberValidator().validate(bonusNumber);
        return bonusNumber;
    }
}
