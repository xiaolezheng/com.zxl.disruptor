package com.lmax.test1;

import com.lmax.disruptor.EventFactory;

/**
 * 
 * just think of a pojo
 * 
 * @author guolei
 * 
 * 
 */
public final class ValueEvent {
	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(final long value) {
		this.value = value;
	}

	public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
		public ValueEvent newInstance() {
			return new ValueEvent();
		}
	};
}