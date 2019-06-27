$.getScript("js/util/check.js");

document.querySelector('#purchaseButton').addEventListener('click', purchase);

function purchase() {
    var form = document.querySelector("#purchase");
    var price = document.querySelector('#purchaseAmount').value;
    var count = document.querySelector('#manualCount').value;

    if (checkNumber(price) || checkNumber(count)) {
        alert('숫자를 입력해주세요.');
        return;
    }

    form.submit();
}