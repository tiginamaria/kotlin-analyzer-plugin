package testData

class CompanionObjectClass {

    companion object {
        class CompanionClass(private val x: List<Set<Int>>) {
            fun companionClassMethod() {
                print(x)
            }
        }

        private const val CONST_VALUE = "CONST_VALUE"
    }
}