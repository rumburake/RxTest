package operators.reducing.collection

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class tomap {

    @Test
    fun tomap() {
        Observable.just("Alpha", "Beta", "Gamma")
            .toMap { s -> s[0] }
            .subscribe { s -> println("Received: $s") }
    }


    @Test
    fun tomaplen() {
        Observable.just("Alpha", "Beta", "Gamma")
            .toMap ( { s -> s[0] }, { s -> s.length} )
            .subscribe { s -> println("Received: $s") }
    }

    @Test
    fun tomultimap() {
        Observable.just("Alpha", "Beta", "Gamma")
            .toMultimap { obj: String -> obj.length }
            .subscribe { s ->
                println(
                    "Received: $s"
                )
            }
    }

}