import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class flatmap_java {

    @Test
    public void flatmap_letters() {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma");
        source.flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }
}
