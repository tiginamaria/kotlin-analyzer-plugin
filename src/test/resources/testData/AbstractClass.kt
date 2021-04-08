package testData

abstract class AbstractClass {
    abstract fun abstractMethod(intParam: Int, strParam: String = "default string"): String

    fun commonMethod(longArg: Long) {
        var str = ""
        while (str.length < longArg) {
            str = abstractMethod(str.length, str)
        }
    }
}
