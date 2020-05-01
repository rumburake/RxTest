package operators.reducing.collection

import com.google.common.collect.ImmutableList
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiConsumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.functions.Supplier
import org.junit.Test

class collect {

    @Test
    fun collect() {
        Observable.just("Alpha", "Beta", "Gamma", "Beta")
            .collect(
                { HashSet() }
            ) { obj: HashSet<String>, e: String ->
                obj.add(e)
            }
            .subscribe { s: HashSet<String> ->
                println(
                    "Received: $s"
                )
            }
    }

    @Test
    fun guava() {
        Observable.just("Alpha", "Beta", "Gamma")
            .collect(
                { ImmutableList.builder() },
                { list: ImmutableList.Builder<Any>, element: String ->
                    list.add(element)
                }
            )
            .map {
                it.build()
            }
            .subscribe { s: ImmutableList<Any> ->
                println(
                    "Received: $s"
                )
            }
    }
}