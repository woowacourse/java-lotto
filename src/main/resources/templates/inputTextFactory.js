window.onload = function () {
    creatTexts();
};

function creatTexts() {
    let div = document.getElementById("lottoNumbers");
    for (let i = 1; i <= document.getElementById("customLottoCount").innerText; i++) {
        let newDiv = document.createElement("div");
        newDiv.setAttribute("style", "50px");
        newDiv.className = "input-group mb-3";

        let newInnerDiv = document.createElement("div");
        newInnerDiv.className = "input-group-prepend";

        let newSpan = document.createElement("span");
        newSpan.className = "input-group-text";
        newSpan.id = "basic-addon" + i;
        newSpan.innerText = "수동로또" + i;

        var newText = document.createElement("input");
        newText.setAttribute("aria-label", "로또");
        newText.setAttribute("aria-describedby", "basic-addon1");
        newText.type = "text";
        newText.name = "autoLottoNumbers";
        newText.placeholder = "번호를 입력하세요.";

        newInnerDiv.append(newSpan);
        newDiv.append(newInnerDiv);
        newDiv.append(newText);
        div.append(newDiv);
    }
}