function manualSelectPlusButtonClickEvent() {
    var manualSelectCountDiv = document.querySelector("#manual-count");
    var manualSelectCount = parseInt(manualSelectCountDiv.value);
    manualSelectCountDiv.value = ++manualSelectCount;
    if (manualSelectCount >= 1) {
        document.querySelector("#manual-minus").disabled = false;
    }
    if (manualSelectCount >= Math.floor(document.querySelector("#purchase-amount-input").value / 1000)) {
        document.querySelector("#manual-plus").disabled = true;
    }
}

function manualSelectMinusButtonClickEvent() {
    var manualSelectCountDiv = document.querySelector("#manual-count");
    var manualSelectCount = parseInt(manualSelectCountDiv.value);
    if (manualSelectCount <= 0) {
        return;
    }
    var purchaseQuantity = Math.floor(document.querySelector("#purchase-amount-input").value / 1000);

    manualSelectCountDiv.value = --manualSelectCount;
    removeSelectedNumbers(manualSelectCount);
    if (manualSelectCount <= purchaseQuantity - 1) {
        document.querySelector("#manual-plus").disabled = false;
    }
    if (manualSelectCount <= 0) {
        document.querySelector("#manual-minus").disabled = true;
    }
}

function checkPurchaseAmount() {
    var manualSelectCountDiv = document.querySelector("#manual-count");
    var purchaseAmount = document.querySelector("#purchase-amount-input").value;
    var manualSelectCount = manualSelectCountDiv.value;
    var purchaseQuantity = Math.floor(purchaseAmount / 1000);

    if (purchaseQuantity < 1) {
        manualSelectCountDiv.value = 0;
        removeSelectedNumbers(0);
        document.querySelector("#manual-plus").disabled = true;
        document.querySelector("#manual-minus").disabled = true;
        return;
    }

    if (purchaseQuantity <= manualSelectCount) {
        manualSelectCountDiv.value = purchaseQuantity;
        removeSelectedNumbers(purchaseQuantity);
        document.querySelector("#manual-plus").disabled = true;
        document.querySelector("#manual-minus").disabled = false;
        return;
    }

    if (document.querySelector("#manual-plus").disabled && purchaseQuantity > manualSelectCount) {
        document.querySelector("#manual-plus").disabled = false;
    }
}

function removeSelectedNumbers(remainCount) {
    var selectedNumbers = document.querySelector("#selected-numbers-area");
    while (selectedNumbers.childElementCount > remainCount) {
        selectedNumbers.removeChild(selectedNumbers.lastElementChild);
    }
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#manual-minus").addEventListener("click", manualSelectMinusButtonClickEvent);
    document.querySelector("#manual-plus").addEventListener("click", manualSelectPlusButtonClickEvent);
});
