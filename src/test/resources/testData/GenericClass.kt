package testData

class GenericClass<T: Collection<Int>> {

    fun method(t: T) {
        print(t)
    }

}
