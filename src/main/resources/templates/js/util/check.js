function checkNumber(number) {
    var pattern = /^[0-9]+$/g;
    var regExp = new RegExp(pattern);

    return !regExp.test(number);
}