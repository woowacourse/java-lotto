function appendCheckBoxes() {
    var lottoCheckboxTemplateHTML = document.querySelector("#number-checkbox-template").innerHTML;
    var lottoCheckboxArea = document.querySelector("#number-checkbox-area");
    for (var i = 1; i <= 45; i++) {
        var lottoCheckboxHTML = String(lottoCheckboxTemplateHTML);
        lottoCheckboxHTML = lottoCheckboxHTML.split("{number}").join(i);
        lottoCheckboxArea.innerHTML += lottoCheckboxHTML;
    }
}

function checkLength(checkbox) {
    var length = 0;
    var checkboxAll = document.querySelectorAll(".number-checkbox");
    var selectButton = document.querySelector("#select-button");
    checkboxAll.forEach(function (v) {
        if (v.checked) {
            length++;
        }
    });
    var max = 6;

    selectButton.disabled = length < max;

    if (length > max) {
        alert("최대 " + max + "개의 숫자를 선택할 수 있습니다.");
        checkboxAll[parseInt(checkbox) - 1].checked = false;
        return;
    }

    if (checkboxAll[parseInt(checkbox) - 1].checked) {
        checkboxAll[parseInt(checkbox) - 1].nextElementSibling.className = "number-checkbox-label-checked";
        return;
    }
    checkboxAll[parseInt(checkbox) - 1].nextElementSibling.className = "number-checkbox-label";
}

document.addEventListener("DOMContentLoaded", function () {
    appendCheckBoxes();
});