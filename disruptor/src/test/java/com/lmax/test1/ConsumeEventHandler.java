package com.lmax.test1;

import com.lmax.disruptor.EventHandler;

public class ConsumeEventHandler implements EventHandler<ValueEvent> {
	@Override
	public void onEvent(ValueEvent event, long sequence, boolean endOfBatch)
			throws Exception {

		// TODO Auto-generated method stub
		System.out.println("==================" + event.getValue());
	}

}