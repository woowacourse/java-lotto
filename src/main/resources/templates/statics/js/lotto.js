const generateBtn = document.getElementById("generate");
const winningBtn = document.getElementById("winning");
var selectedBalls = [];
const deliveryData = {
    lottoMoney: "",
    manualCount: "",
    manualLottos: []
}

Array.prototype.remove = function () {
    var what, a = arguments, L = a.length, ax;
    while (L && this.length) {
        what = a[--L];
        while ((ax = this.indexOf(what)) !== -1) {
            this.splice(ax, 1);
        }
    }
    return this;
};

function postAjax(url, data, success) {
    const params = typeof data == 'string' ? data : Object.keys(data).map(
        function (k) { return encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) }
    ).join('&');
    console.log(params);
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState > 3 && xhr.status == 200) {
            success(xhr.responseText);
        }
    };
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(params);
    return xhr;
}

function addHistory(numberList) {
    const historyDiv = document.getElementById("history");
    makeBalls(numberList, historyDiv);
    deliveryData.manualLottos.push(selectedBalls);
    selectedBalls = [];
}

function makeBalls(number, node) {
    const span = document.createElement('span');
    number.forEach(v => {
        const ball = document.createElement('p');
        ball.className = 'ball';
        ball.innerText = v;
        span.appendChild(ball);
    })
    node.appendChild(span);
}

function clearBalls() {
    const balls = Array.from(document.getElementById('side-right').getElementsByClassName('ball'));
    balls
        .forEach(v => v.style.backgroundColor = 'white');
}

generateBtn.addEventListener('click', () => {
    postAjax('/lotto', {
        money: document.getElementById('money').value,
        manual: document.getElementById('manual').value
    }, (res) => {
        deliveryData.lottoMoney = document.getElementById('money').value;
        deliveryData.manualCount = document.getElementById('manual').value;
        document.getElementById('container').innerHTML = res;
        Array.from(document.getElementById('side-right').getElementsByClassName('ball'))
            .forEach(v => {
                v.addEventListener('click', (e) => {
                    if (selectedBalls.indexOf(e.target.innerText) === -1) {
                        selectedBalls.push(e.target.innerText);
                        e.target.style.backgroundColor = "#fa8b60";
                    } else {
                        selectedBalls.remove(e.target.innerText);
                        e.target.style.backgroundColor = "white";
                    }
                    if (selectedBalls.length > 5) {
                        clearBalls();
                        return addHistory(selectedBalls);
                    }
                })
            })

        generateBtn.addEventListener('click', () => {
            postAjax('/userLotto', deliveryData, (res) => {
                const parseData = JSON.parse(res).userLotto;
                clearBalls();
                document.getElementById('history').innerHTML = "";
                parseData
                    .map(data => addHistory(data));
                winningBtn.style.visibility = "visible";
                alert("구입완료");
            })
        }, { once: true });
    })
}, { once: true });

winningBtn.addEventListener('click', (e) => {
    postAjax('/lotto', {}, (res) => {
        document.getElementById('container').innerHTML = res;

        Array.from(document.getElementById('side-right').getElementsByClassName('ball'))
            .forEach(v => {
                v.addEventListener('click', (e) => {
                    if (selectedBalls.indexOf(e.target.innerText) === -1) {
                        selectedBalls.push(e.target.innerText);
                        e.target.style.backgroundColor = "#fa8b60";
                    } else {
                        selectedBalls.remove(e.target.innerText);
                        e.target.style.backgroundColor = "white";
                    }
                    if (selectedBalls.length > 6) {
                        return addHistory(selectedBalls);
                    }
                })
            })

        winningBtn.addEventListener('click', (e) => {
            const balls = Array.from(document.getElementById('history').getElementsByClassName('ball'))
                .map(ele => ele.innerText);
            const lottoNumber = balls.slice(0, 6);
            const bonusNumber = balls[0];
            console.log(balls);
            console.log(lottoNumber);
            console.log(bonusNumber);
            postAjax('/winningLotto', {
                numbers: lottoNumber,
                bonus: bonusNumber
            }, (res) => {
                winningBtn.addEventListener('click', (e) => {
                    fetch('/lottoResult')
                        .then(res => res.json())
                        .then(data => {
                            console.log(data)
                            document.getElementById('container').innerHTML = JSON.stringify(data);
                        });
                })
            })
        }, { once: true });
    })
}, { once: true });

