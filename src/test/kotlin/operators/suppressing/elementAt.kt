package operators.suppressing

import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test

class elementAt {

    fun got(s: String) {
        println("Got: $s")
    }

    fun error() {
        println("Got: Error!")
    }

    val obs = Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")

    val never = Observable.never<String>()

    val one = Observable.just("foo")

    @Test
    fun elementAt() {
        obs
            .elementAt(3)
            .subscribe { got(it) }
    }

    @Test
    fun elementAt6() {
        obs
            .elementAt(6)
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun elementAtOrError() {
        obs
            .elementAtOrError(5)
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun elementAtOrError6() {
        obs
            .elementAtOrError(6)
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun singleElement() {
        obs
            .singleElement()
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun singleElement1() {
        one
            .singleElement()
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun singleElement0() {
        never
            .singleElement()
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun first() {
        obs
            .firstElement()
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun last() {
        obs
            .lastElement()
            .subscribe ( { got(it) }, { error() } )
    }

    @Test
    fun lastNever() {
        never
            .lastElement()
            .subscribe ( { got(it) }, { error() } )
    }


}