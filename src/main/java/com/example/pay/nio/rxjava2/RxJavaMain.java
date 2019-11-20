package com.example.pay.nio.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class RxJavaMain {
    public static void main(String[] args) {
        Observable observable = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

        Observable.just(1,2,3).subscribe(observer);
        Observable.just(1,2,3).all(integer -> integer>5).subscribe(aBoolean -> System.out.println(aBoolean));


        List<String> words = Arrays.asList(
                "the",
                "quick",
                "brown",
                "fox",
                "jumped",
                "over",
                "the",
                "lazy",
                "dogs"
        );
        Observable.fromIterable(words).flatMap(word->Observable.fromArray(word.split("")))
                .distinct().sorted().subscribe(System.out::println);


        List<String> list = Arrays.asList(
                "blue", "red", "green", "yellow", "orange", "cyan", "purple"
        );

        Disposable flowable = Flowable.fromIterable(list).skip(2).subscribe(System.out::println);
    }
}
