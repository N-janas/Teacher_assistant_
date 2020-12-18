object Rabaty{
    val noDiscount: Double = 0.0
    val rabat10: Double = 0.1
    val rabat25: Double = 0.25
    val rabat40: Double = 0.4

    var paragonCount: Int = 1
        get() = field
        set(value){
            field++
        }
    var rachunekCount: Int = 1
        get() = field
        set(value){
            field++
        }
    var fakturaCount: Int = 1
        get() = field
        set(value){
            field++
        }
}