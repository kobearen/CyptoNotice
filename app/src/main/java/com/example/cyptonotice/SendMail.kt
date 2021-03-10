package com.example.cyptonotice

fun main(args : Array<String>) {
    var to = "to@example.com"
    var from = "from@example.com"
    var fromName = "SenderKotlin"

    var content = """
    {
        "personalizations": [
            {
                "to": [
                    {
                        "email": "$to"
                    }
                ]
            }
        ],
        "from": {
            "email": "$from",
            "name": "$fromName"
        },
        "subject": "テスト",
        "content": [
            {
                "type": "text/plain",
                "value": "Kotlinから送った"
            },
            {
                "type": "text/html",
                "value": "<html><body>Kotlinから送った</body></html>"
            }
        ]
    }
    """

//    FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer *****APIキー*****")
//    "https://api.sendgrid.com/v3/mail/send".httpPost().body((content)).response { req, res, result ->
//        when(result) {
//            is Result.Failure -> {
//                println("APIのコールに失敗")
//            }
//            is Result.Success -> {
//                when(res.httpStatusCode) {
//                    202 -> println("メール送信成功")
//                    400, 401, 413 -> println("SendGridから400番台のエラーコードが返ってきた")
//                    else -> println("想定していないコードが返ってきた")
//                }
//            }
//        }
//        println(req)
//        println(res)
//        println(result)
    }
