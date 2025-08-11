приложение, которое по REST принимает запрос вида
POST api/v1/wallet
{
walletId: UUID,
operationType: DEPOSIT or WITHDRAW,
amount: 1000
}

после выполняeт логику по изменению счета в базе данных

также есть возможность получить баланс кошелька
GET api/v1/wallets/{WALLET_UUID}

стек:
java 8-17
Spring 3
Postgresql

Достигнутая производительность, если БД и само приложение запущены в Docker контейнерах: 110 RPS (запросов в секунду)
