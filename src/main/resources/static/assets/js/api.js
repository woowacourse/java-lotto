const BASE_URL = "http://localhost:4567"

function submit(type) {
    const data = Object.fromEntries(new FormData(document.getElementById(type)))
    deleteFrontPage()
    axios.post(BASE_URL + "/" + type,
        data, {
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            document.getElementById("result_page").innerHTML = res.data
        }).catch(function (err) {
            alert(err)
        }
    )
}

function submitHistory() {
    submit("history")
}

function submitPurchase() {
    submit("purchase")
}