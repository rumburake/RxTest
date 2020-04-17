package observables.other

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class fromCallable {

    @Test
    fun long() {
        println("Getting...")
        val result = longArray()
        println("Got: $result")
        println("Minding other business.")
    }

    @Test
    fun fromCallable() {
        println("Getting...")

        Observable.fromCallable { longArray() }
            .observeOn(Schedulers.computation())
            .subscribe { foo -> println("RX Got: $foo") }

        println("Minding other business.")
    }

    fun longArray(): List<Int> {
        val numbers = ArrayList<Int>()
        for (i in 1..100) {
            numbers += i
            Thread.sleep(100)
        }
        return numbers
    }

    @Test
    fun error() {
        Observable.fromCallable { 1 / 0}
            .subscribe(
                { println("Got $it")},
                { println("Error; ${it.localizedMessage}") }
            )
    }
}