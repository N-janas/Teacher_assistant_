
fun main(){
    val dowodyPlatnosci = listOf<DowodPlatnosci>(
        Paragon(
                Firma("Biedronka", "7290016351", "ul. Bartnicza 13, 43-300 Bielsko-Biała"),
                "02-04-2020",
                listOf(
                        Zakup(
                                Produkt("Pomidor", 5.39, 0.23),
                                ilosc = 3
                        ),
                        Zakup(
                                Produkt("Woda 2L", 7.59, 0.23),
                                ilosc = 1
                        ),
                        Zakup(
                                Produkt("Ser", 6.79, 0.08),
                                ilosc = 2,
                                rabat = Rabaty.rabat25
                        )
                )
        ),
        Paragon(
                Firma("Lidl", "2793237304", "ul. Mickiewicza 7, 81-222 Gdynia"),
                "14-08-2020",
                listOf(
                        Zakup(
                                Produkt("Zeszyt", 4.59, 0.23),
                                ilosc = 5
                        ),
                        Zakup(
                                Produkt("Dlugopis", 1.19, 0.08),
                                ilosc = 3
                        ),
                        Zakup(
                                Produkt("Kubek", 9.99, 0.23),
                                ilosc = 1,
                                rabat = Rabaty.rabat10
                        ),
                        Zakup(
                                Produkt("Spinacze biurowe paczka (50 sztuk)", 4.47, 0.23),
                                ilosc = 4,
                                rabat = Rabaty.rabat40
                        ),
                        Zakup(
                                Produkt("Cyrkiel", 11.23, 0.23),
                                ilosc = 1
                        )
                )
        ),
        Rachunek(
                Firma("Ikea", "9243263100", "ul. Kochanowskiego 21, 81-222 Gdynia"),
                "27-03-2020",
                listOf(
                        Zakup(
                                Produkt("Bateria kuchenna", 229.0, 0.23),
                                ilosc = 1
                        ),
                        Zakup(
                                Produkt("Rekawica kuchenna", 9.99, 0.08),
                                ilosc = 2
                        ),
                        Zakup(
                                Produkt("Serwetki (30 sztuk)", 7.99, 0.23),
                                ilosc = 2,
                                rabat = Rabaty.rabat25
                        )
                ),
                Osoba("Jan", "Kowalski")
        ),
        Faktura(
                Firma("Mercedes Benz", "1112223334", "ul. Jaglana 13, 01-464 Warszawa"),
                "11-12-2020",
                listOf(
                        Zakup(
                                Produkt("Mercedes GLC 220 d4MATIC", 135_934.0, 0.23),
                                ilosc = 1,
                                rabat = Rabaty.rabat10
                        )
                ),
                Firma("Deller", "6250948142", "ul. Debow 2, 01-464 Warszawa")
        )
    )

    for (dowod in dowodyPlatnosci){
        dowod.Drukuj()
    }
}