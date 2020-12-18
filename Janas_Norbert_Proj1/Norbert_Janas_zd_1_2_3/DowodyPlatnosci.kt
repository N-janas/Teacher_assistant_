
abstract class DowodPlatnosci(var podmiot: Firma, var data: String, var listaZakupow: List<Zakup>){
    abstract fun Drukuj()
    /*
    Gdyby w poleceniu zadania nie należałoby robić Drukuj abstrakcyjnej, można oszczędzić lini kodu
    otwierając metodę na przesłanianie i tworząc ciało Drukuj jako znajdowanie odstępów; wypisywanie formatu tabeli;
    a w funkcjach przesłoniętych wypisywać podmioty (nagłówek); super.Drukuj() (tabela); wypisać sume;
     */
}


class Paragon(
        firma: Firma,
        data: String,
        listaZakupow: List<Zakup>): DowodPlatnosci(firma, data, listaZakupow){

    var sumKoszt: Double

    init {
        sumKoszt = policzKoszt()
    }

    private fun policzKoszt(): Double{
        var koszt: Double = 0.0
        for (zakup in listaZakupow){
            koszt += zakup.obliczKoszt()
        }
        return  koszt
    }

    override fun Drukuj() {
        // Do formatowania odstępu
        val nameL = listaZakupow
                .map { it.produkt.nazwa.length }
                .max()
        val nettoL = listaZakupow
                .map { it.produkt.netto.toString().length }
                .max()
        val bruttoL = listaZakupow
                .map { "%.2f".format(it.produkt.brutto).length }
                .max()
        val kosztL = listaZakupow
                .map { it.obliczKoszt().toString().length }
                .max()
        //
        println("-".repeat((90)))
        println("\t Paragon nr ${Rabaty.paragonCount}")
        println(podmiot)
        println("Data zakupu: $data")

        // Formatowanie tabeli
        var lambda: (Int?, String) -> Int = {x, name -> if(x != null && x >= name.length) x else name.length }

        println("   %-${lambda(nameL, "nazwa")}s %-${lambda(nettoL, "netto")}s %-6s %-${lambda(bruttoL, "brutto")}s %-5s %-5s %-${lambda(kosztL, "koszt")}s"
                .format("Nazwa", "Netto", "Vat", "Brutto", "Ilosc", "Rabat", "Koszt")
        )
        for ((i, zakup) in listaZakupow.withIndex()){
            println("$i: %-${lambda(nameL, "nazwa")}s %-${lambda(nettoL, "netto")}s %-6s %-${lambda(bruttoL, "brutto")}s %-5s %-5s %-${lambda(kosztL, "koszt")}s"
                    .format(*zakup.passArgs())
            )
        }
        //
        println("Suma zakupow: %.2f".format(policzKoszt()))
        println("-".repeat((90)))
        Rabaty.paragonCount++
    }
}


class Rachunek(
        firma: Firma,
        data: String,
        listaZakupow: List<Zakup>,
        var nabywca: Osoba): DowodPlatnosci(firma, data, listaZakupow){

    var sumKoszt: Double

    init {
        sumKoszt = policzKoszt()
    }

    private fun policzKoszt(): Double{
        var koszt: Double = 0.0
        for (zakup in listaZakupow){
            koszt += zakup.obliczKoszt()
        }
        return  koszt
    }


    override fun Drukuj() {
        // Do formatowania odstępu
        val nameL = listaZakupow
                .map { it.produkt.nazwa.length }
                .max()
        val nettoL = listaZakupow
                .map { it.produkt.netto.toString().length }
                .max()
        val bruttoL = listaZakupow
                .map { "%.2f".format(it.produkt.brutto).length }
                .max()
        val kosztL = listaZakupow
                .map { it.obliczKoszt().toString().length }
                .max()
        //
        println("-".repeat((90)))
        println("\t Rachunek nr ${Rabaty.rachunekCount}")
        println(podmiot)
        println("Data zakupu: $data")
        println(nabywca)

        // Formatowanie tabeli
        var lambda: (Int?, String) -> Int = {x, name -> if(x != null && x >= name.length) x else name.length }

        println("   %-${lambda(nameL, "nazwa")}s %-${lambda(nettoL, "netto")}s %-6s %-${lambda(bruttoL, "brutto")}s %-5s %-5s %-${lambda(kosztL, "koszt")}s"
                .format("Nazwa", "Netto", "Vat", "Brutto", "Ilosc", "Rabat", "Koszt")
        )
        for ((i, zakup) in listaZakupow.withIndex()){
            println("$i: %-${lambda(nameL, "nazwa")}s %-${lambda(nettoL, "netto")}s %-6s %-${lambda(bruttoL, "brutto")}s %-5s %-5s %-${lambda(kosztL, "koszt")}s"
                    .format(*zakup.passArgs())
            )
        }
        //
        println("Suma zakupow: %.2f".format(policzKoszt()))
        println("-".repeat((90)))
        Rabaty.rachunekCount++
    }
}


class Faktura(
        firma: Firma,
        data: String,
        listaZakupow: List<Zakup>,
        var nabywca: Firma): DowodPlatnosci(firma, data, listaZakupow){

    var sumKoszt: Double

    init {
        sumKoszt = policzKoszt()
    }

    private fun policzKoszt(): Double{
        var koszt: Double = 0.0
        for (zakup in listaZakupow){
            koszt += zakup.obliczKoszt()
        }
        return  koszt
    }

    override fun Drukuj() {
        // Do formatowania odstępu
        val nameL = listaZakupow
                .map { it.produkt.nazwa.length }
                .max()
        val nettoL = listaZakupow
                .map { it.produkt.netto.toString().length }
                .max()
        val bruttoL = listaZakupow
                .map { "%.2f".format(it.produkt.brutto).length }
                .max()
        val kosztL = listaZakupow
                .map { it.obliczKoszt().toString().length }
                .max()
        //
        println("-".repeat((90)))
        println("\t Faktura nr ${Rabaty.fakturaCount}")
        println(podmiot)
        println(nabywca)
        println("Data zakupu: $data")

        // Formatowanie tabeli
        var lambda: (Int?, String) -> Int = {x, name -> if(x != null && x >= name.length) x else name.length }

        println("   %-${lambda(nameL, "nazwa")}s %-${lambda(nettoL, "netto")}s %-6s %-${lambda(bruttoL, "brutto")}s %-5s %-5s %-${lambda(kosztL, "koszt")}s"
                .format("Nazwa", "Netto", "Vat", "Brutto", "Ilosc", "Rabat", "Koszt")
        )
        for ((i, zakup) in listaZakupow.withIndex()){
            println("$i: %-${lambda(nameL, "nazwa")}s %-${lambda(nettoL, "netto")}s %-6s %-${lambda(bruttoL, "brutto")}s %-5s %-5s %-${lambda(kosztL, "koszt")}s"
                    .format(*zakup.passArgs())
            )
        }
        //
        println("Suma zakupow: %.2f".format(policzKoszt()))
        println("-".repeat((90)))
        Rabaty.fakturaCount++
    }
}
