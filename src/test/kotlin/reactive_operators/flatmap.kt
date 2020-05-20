package reactive_operators

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class flatmap {

    @Test
    fun flatmap_letters() {
        Observable.just<String>("one", "two", "three")
            .flatMap { Observable.fromIterable(it.split("")) }
            .subscribe { print("$it ")}
    }

    @Test
    fun flatmap_parse() {
        Observable.just("1 time I found 2 fish in 3 lakes with 4 eyes.", "99 bottles and 15 crew")
            .flatMap { Observable.fromIterable(it.split(" ")) }
            .filter { it.matches(Regex("[0-9]+"))}
            .subscribe { print("$it ")}
    }
}