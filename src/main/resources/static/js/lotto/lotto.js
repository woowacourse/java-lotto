function selectButtonEvent() {
    if (!validQuantity()) {
        alert("수동구매 개수 초과");
        return;
    }
    var lottoCheckboxes = document.querySelectorAll(".number-checkbox");
    var lottoNumbers = [];
    var lottoNumbersTemplate = document.querySelector("#lotto-numbers-template").innerText;
    var n = 0;
    document.querySelectorAll(".lotto-number").forEach(function (value) {
        n = parseInt(value.id.split("lottoNumber")[1]) + 1;
    });
    lottoCheckboxes.forEach(function (v) {
        if (v.checked) {
            lottoNumbers.push(String(v.value));
        }
        v.checked = false;
        v.nextElementSibling.className = "number-checkbox-label";
    });
    document.querySelector("#selected-numbers-area").innerHTML += lottoNumbersTemplate
        .replace("{lottoNumber}", lottoNumbers.join(" "))
        .replace("{lottoNumberInput}", lottoNumbers.join(","))
        .split("{n}").join(n);
    document.querySelector("#select-button").disabled = true;
}

function validQuantity() {
    var manualSelectCount = parseInt(document.querySelector("#manual-count").value);
    return manualSelectCount > document.querySelectorAll(".lotto-number").length;
}

function cancelButtonEvent() {
    var selectedNumbersArea = document.querySelector("#selected-numbers-area");
    document.querySelectorAll(".lotto-number").forEach(function (value) {
        if (value.checked) {
            selectedNumbersArea.removeChild(value.parentElement);
        }
    });

    document.querySelector("#cancel-button").disabled = true;
}

function clickLottoNumber(n) {
    var lottoNumberInput = document.querySelector("#lottoNumber" + n);
    if (lottoNumberInput.checked) {
        lottoNumberInput.nextElementSibling.className = "lotto-number-label-checked";
        document.querySelector("#cancel-button").disabled = false;
        return;
    }
    lottoNumberInput.nextElementSibling.className = "lotto-number-label";
    var lottoNumbers = document.querySelectorAll(".lotto-number");
    for (var i = 0, len = lottoNumbers.length; i < len; i++) {
        if (lottoNumbers[i].checked) {
            return;
        }
    }
    document.querySelector("#cancel-button").disabled = true;
}

function validManualNumbers() {
    var manualSelectCount = parseInt(document.querySelector("#manual-count").value);
    return document.querySelectorAll(".lotto-number").length === manualSelectCount;
}

function clickPurchaseButton() {
    if (!validManualNumbers()) {
        alert("올바른 수동 구매 번호를 입력해주세요.");
        return;
    }
    var manualCount = document.querySelector("#manual-count").value;
    var purchaseAmount = document.querySelector("#purchase-amount-input").value;
    var lottoNumbers = [];
    document.querySelectorAll(".lotto-number").forEach(function (value) {
        lottoNumbers.push(value.value);
    });
    axios.post("/purchase", {
        manualCount: manualCount,
        purchaseAmount: purchaseAmount,
        lottoNumbers: lottoNumbers
    })
    .then(changeToWinningLotto)
    .catch(function (error) {
        alert(error)
    });
}

function changeToWinningLotto(){
    document.querySelector("#purchase-form").style.display = "none";
    document.querySelector("#winning-lotto-area").style.display = "";
}

function validWinningLotto(winningLottoNumber, bonusNumber){
    var winningLotto = winningLottoNumber.concat("," + bonusNumber)
        .split(",")
        .map((v)=>parseInt(v))
        .sort()
        .filter((x, i, a) => a.indexOf(x) == i)
        .filter((x) => x >= 1 && x <= 45);

    return winningLotto.length == 7;
}

function clickWinningLottoButton(){
    var winningLottoNumber = document.querySelector("#winning-lotto").value;
    var bonusNumber = document.querySelector("#bonus-number").value;
    if(!validWinningLotto(winningLottoNumber, bonusNumber)){
        alert("잘 못된 당첨 번호 및 보너스 번호입니다.");
        return;
    }

    axios.post("/winninglotto", {
        winningLotto:winningLottoNumber,
        bonusNumber:bonusNumber
    })
        .then(function(response){
            changeToResult(response.data.id);
        })
        .catch(function (error) {
            alert(error)
        });
}

function changeToResult(lottoRoundId){
    clickShowLottoResult();
}

function clickShowLottoPurchase(){
    document.querySelector(".lotto-results-area").style.display = "none";
    document.querySelector("#winning-lotto-area").style.display = "none";
    document.querySelector("#purchase-form").style.display = "";
}

function clickShowLottoResult(){
    document.querySelector(".lotto-results-area").style.display = "";
    document.querySelector("#winning-lotto-area").style.display = "none";
    document.querySelector("#purchase-form").style.display = "none";
    axios.get("/winningResult")
        .then(function(response){
            var roundSelect = document.querySelector("#round-select");
            var rounds = roundSelect.getElementsByClassName("rounds");
            if(rounds.length > 0){
                for(var i=0, len=rounds.length; i<len;i++){
                    roundSelect.removeChild(rounds[i]);
                }
            }
            var roundData = response.data;
            for(var i=0, len=roundData.length; i<len; i++){
                var roundTemplate = document.querySelector("#round-template").innerText;
                roundSelect.innerHTML += roundTemplate.split("{roundId}").join(roundData[i].id);
            }
        })
        .catch(function(error){
            alert(error)
        });
}

function roundClickEvent(lottoRoundId){
    lottoRoundId = parseInt(lottoRoundId);
    if(lottoRoundId < 0){
        return;
    }
    var lottoResult = document.querySelector("#lotto-result");
    if(lottoResult.childElementCount > 0){
        lottoResult.innerHTML = "";
    }
    axios.get("/winningResult/" + lottoRoundId)
        .then(function(response){
            var winningResultTemplate = document.querySelector("#winning-result-template").innerText;
            var winningResultData = response.data;

            for(var resultElement in winningResultData){
                winningResultTemplate = winningResultTemplate.split(resultElement).join(winningResultData[resultElement]);
            }
            lottoResult.innerHTML += winningResultTemplate;
        })
        .catch(function(error){
            alert(error)
        });
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#select-button").addEventListener("click", selectButtonEvent);
    document.querySelector("#cancel-button").addEventListener("click", cancelButtonEvent);
    document.querySelector("#button-purchase").addEventListener("click", clickPurchaseButton);
    document.querySelector("#winning-lotto-button").addEventListener("click", clickWinningLottoButton);
    document.querySelector("#show-lotto-purchase").addEventListener("click", clickShowLottoPurchase);
    document.querySelector("#show-lotto-result").addEventListener("click", clickShowLottoResult);
});