package com.example.observer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

public class LegacyObserverApp {
	public static void main(String[] args) {
		 Observable observable = new TradeEventObservable();
		 Observer observer1 = new Observer() {
			
			@Override
			public void update(Observable o, Object event) {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) { }
				System.err.println("Event received from observer #1: "+event);
			}
		};
		Observer observer2 = new Observer() {
			
			private double volume;

			@Override
			public void update(Observable o, Object event) {
				TradeEvent te = (TradeEvent) event;
				volume += te.getPrice() * te.getQuantity();
				System.err.println("Volume: "+volume);
			}
		};
		List<TradeEvent> events = List.of(
			new TradeEvent("orcl", 100, 20),	
			new TradeEvent("orcl", 101, 30),	
			new TradeEvent("orcl", 102, 28),	
			new TradeEvent("orcl", 103, 30),	
			new TradeEvent("orcl", 104, 40),	
			new TradeEvent("orcl", 105, 50),	
			new TradeEvent("orcl", 106, 60)	
				);
		observable.addObserver(observer1);
		observable.addObserver(observer2);
		events.forEach(observable::notifyObservers);
	}
}

class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}