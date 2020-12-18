
class Produkt(val nazwa: String, var netto: Double, var vat: Double){
    var brutto: Double

    init {
        brutto = netto*vat + netto
    }

    fun passArgs(): Array<String>{
        return arrayOf(nazwa, netto.toString(), "${vat*100}%", "%.2f".format(brutto))
    }

    override fun toString(): String {
        return "$nazwa $netto ${vat*100}% "+"%.2f ".format(brutto)
    }
}


class Zakup(var produkt: Produkt, var ilosc: Int, var rabat: Double = Rabaty.noDiscount){

    fun obliczKoszt(): Double{
        return produkt.brutto * ilosc - produkt.brutto * rabat * ilosc
    }

    fun passArgs(): Array<String> {
        return arrayOf(*produkt.passArgs(), ilosc.toString(), rabat.toString(), "%.2f".format(obliczKoszt()))
    }

    override fun toString(): String {
        return "$produkt $ilosc $rabat %.2f".format(obliczKoszt())
    }
}