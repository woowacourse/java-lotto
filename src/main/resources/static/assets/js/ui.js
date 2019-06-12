let max = 0
let amount = 0

function addManualInputs() {
    if (max === amount) {
        alert("투자금이 부족합니다.")
        return
    }
    const textbox = document.createElement("input")
    textbox.setAttribute("type","text");
    textbox.setAttribute("name", "manualNumbers[" + amount + "]")
    document.getElementById("manualNumbersContainer").appendChild(textbox)
    document.getElementById("manualNumbersContainer").appendChild(document.createElement("br"))
    amount++
    if (amount == 1) {
        document.getElementById("guide").innerHTML = "쉼표로 6개의 번호를 구분하여 입력해주세요.";
    }
}

function removeManualInputs() {
    if (amount > 0) {
        const container = document.getElementById("manualNumbersContainer")
        container.removeChild(container.lastChild)
        container.removeChild(container.lastChild)
        amount--
        if (amount == 0) {
            document.getElementById("guide").innerHTML = "";
        }
    }
}

function resetManualInputs() {
    if (document.getElementById("investment").value < 0
            || isNaN(document.getElementById("investment").value)) {
        document.getElementById("investment").value = 0
    }
    max = Math.floor(document.getElementById("investment").value / price)
    while (max < amount) {
        removeManualInputs()
    }
}