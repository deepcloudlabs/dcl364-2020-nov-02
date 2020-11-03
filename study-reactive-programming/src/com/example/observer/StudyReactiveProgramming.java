package com.example.observer;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

public class StudyReactiveProgramming {
	public static void main(String[] args) throws InterruptedException {
         SubmissionPublisher<TradeEvent> publisher =
        		 new SubmissionPublisher<>();
 		List<TradeEvent> events = List.of(
 				new TradeEvent("orcl", 100, 20),	
 				new TradeEvent("orcl", 101, 30),	
 				new TradeEvent("orcl", 102, 28),	
 				new TradeEvent("orcl", 103, 30),	
 				new TradeEvent("orcl", 104, 40),	
 				new TradeEvent("orcl", 105, 50),	
 				new TradeEvent("orcl", 106, 60)	
 					);
 		Subscriber<TradeEvent> subscriber1 = new AlgoTrader(); 
 		Subscriber<TradeEvent> subscriber2 = new SignalProcessor();
 		publisher.subscribe(subscriber1);
 		publisher.subscribe(subscriber2);
 		events.forEach(publisher::submit);
        TimeUnit.SECONDS.sleep(30); 
        publisher.close();
        System.err.println("Application is done.");
	}
}

class AlgoTrader implements Subscriber<TradeEvent> {
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) { }
		System.err.println("Event received from observer #1: "+event);		
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader is done.");
	}
	
}

class SignalProcessor implements Subscriber<TradeEvent> {
	private Subscription subscription;
	private double volume;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		volume += event.getPrice() * event.getQuantity();
		System.err.println("Volume: "+volume);		
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) { }

	@Override
	public void onComplete() {
		System.err.println("SignalProcessor is done.");
	}
	
}