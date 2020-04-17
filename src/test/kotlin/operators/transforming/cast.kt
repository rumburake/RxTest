package operators.transforming

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class cast {

    @Test
    fun cast() {
        Observable.just("foo", "boo")
            .cast(Object::class.java)
            .subscribe { println("Got: ${it.`class`}")}
    }
}