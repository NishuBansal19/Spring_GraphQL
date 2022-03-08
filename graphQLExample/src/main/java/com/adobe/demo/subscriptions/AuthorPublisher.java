package com.adobe.demo.subscriptions;

import org.springframework.stereotype.Component;

import com.adobe.demo.entity.Author;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

@Component
public class AuthorPublisher {

    private final Flowable<Author> publisher;

    private ObservableEmitter<Author> emitter;
    
    public AuthorPublisher() {
        Observable<Author> authorObservable = Observable.create(emitter -> {
        	System.out.println("Emitter ==> " + emitter);
            this.emitter = emitter;
        });
        publisher = authorObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(final Author author) {
        emitter.onNext(author);
    }


    public Flowable<Author> getPublisher() {
        return publisher;
    }
}
