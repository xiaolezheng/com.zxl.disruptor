package com.lmax.test1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

public class TestShow {

	private static final int BUFFER_SIZE = 32;

	private final RingBuffer<ValueEvent> ringBuffer = RingBuffer.createSingleProducer(ValueEvent.EVENT_FACTORY,BUFFER_SIZE,new YieldingWaitStrategy());
	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

	private final ConsumeEventHandler handler = new ConsumeEventHandler();

	private final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

	private final BatchEventProcessor<ValueEvent> batchEventProcessor = new BatchEventProcessor<ValueEvent>(
			ringBuffer, sequenceBarrier, handler);

	public TestShow() {
		ringBuffer.addGatingSequences(batchEventProcessor.getSequence());
	}

	public void consume() {
		EXECUTOR.submit(batchEventProcessor);
	}

	public void produce() {
		new Thread(new Producer(ringBuffer)).start();
	}

	public static void main(String[] args) {
		TestShow test = new TestShow();
		test.produce();
		test.consume();
	
	}
}