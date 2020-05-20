package reactive_operators

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class flatmap {

    @Test
    fun flatmap_letters() {
        Observable.just<String>("one", "two", "three")
            .flatMap { Observable.fromIterable(it.split("")) }
            .subscribe { println("$it")}
    }
}