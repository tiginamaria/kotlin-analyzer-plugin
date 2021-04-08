package testData

class LambdaClass {

    fun lambdaMethod(): List<Int> {
        return listOf(1, 2, 3, 4).filter { x -> x > 2 }.map { x -> x * x }
    }
}
