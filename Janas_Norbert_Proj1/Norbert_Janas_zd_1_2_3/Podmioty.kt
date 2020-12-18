
class Osoba(var imie:String, var nazwisko: String){
    override fun toString(): String {
        return "Imie: $imie \t Nazwisko: $nazwisko"
    }
}

class Firma(var nazwa: String, var nip: String, var adres: String){
    override fun toString(): String {
        return "Firma: $nazwa \nNIP: $nip \nAdres: $adres"
    }
}