package testData

open class OpenClass(private val param1: Boolean, param2: Int) {
    open fun openMethod(): Int {
        return if (param1) {
            2 * 2
        } else {
            3 * 3
        }
    }
}

open class SimpleClass(private val param1: Boolean, param2: Int) : OpenClass(param1, param2) {
    override fun openMethod(): Int {
        return if (param1) {
            2 + 2
        } else {
            3 + 3
        }
    }
}
