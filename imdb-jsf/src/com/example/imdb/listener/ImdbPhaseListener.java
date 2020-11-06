package com.example.imdb.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class ImdbPhaseListener implements PhaseListener {
	private long startTime;
	@Override
	public void afterPhase(PhaseEvent pe) {
		long stopTime= System.nanoTime();
		System.err.println(pe.getPhaseId().getName()
				+": "+(stopTime-startTime));
	}

	@Override
	public void beforePhase(PhaseEvent pe) {
		startTime= System.nanoTime();
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
