package net.spy.memcached;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;

import net.spy.memcached.ops.Operation;
import net.spy.memcached.transcoders.Transcoder;

/**
 * Builder for more easily configuring a ConnectionFactory.
 */
public class ConnectionFactoryBuilder {

	private BlockingQueue<Operation> opQueue;
	private BlockingQueue<Operation> readOpQueue;
	private BlockingQueue<Operation> writeOpQueue;

	private Transcoder<Object> transcoder;

	private FailureMode failureMode;

	private Collection<ConnectionObserver> initialObservers
		= Collections.emptyList();

	private OperationFactory opFact;

	private long opTimeout = -1;
	private boolean isDaemon = true;
	private boolean shouldOptimize = true;

	private int readBufSize = -1;
	private HashAlgorithm hashAlg;

	public ConnectionFactoryBuilder setOpQueue(BlockingQueue<Operation> q) {
		opQueue = q;
		return this;
	}

	public ConnectionFactoryBuilder setReadOpQueue(BlockingQueue<Operation> q) {
		readOpQueue = q;
		return this;
	}

	public ConnectionFactoryBuilder setWriteOpQueue(BlockingQueue<Operation> q) {
		writeOpQueue = q;
		return this;
	}

	public ConnectionFactoryBuilder setTranscoder(Transcoder<Object> t) {
		transcoder = t;
		return this;
	}

	public ConnectionFactoryBuilder setFailureMode(FailureMode fm) {
		failureMode = fm;
		return this;
	}

	public ConnectionFactoryBuilder setInitialObservers(
			Collection<ConnectionObserver> obs) {
		initialObservers = obs;
		return this;
	}

	public ConnectionFactoryBuilder setOpFact(OperationFactory f) {
		opFact = f;
		return this;
	}

	public ConnectionFactoryBuilder setOpTimeout(long t) {
		opTimeout = t;
		return this;
	}

	public ConnectionFactoryBuilder setDaemon(boolean d) {
		isDaemon = d;
		return this;
	}

	public ConnectionFactoryBuilder setShouldOptimize(boolean o) {
		shouldOptimize = o;
		return this;
	}


	public ConnectionFactoryBuilder setReadBufferSize(int to) {
		readBufSize = to;
		return this;
	}

	public ConnectionFactoryBuilder setHashAlg(HashAlgorithm to) {
		hashAlg = to;
		return this;
	}

	/**
	 * Get the ConnectionFactory set up with the provided parameters.
	 */
	public ConnectionFactory build() {
		return new DefaultConnectionFactory() {

			@Override
			public BlockingQueue<Operation> createOperationQueue() {
				return opQueue == null ?
						super.createOperationQueue() : opQueue;
			}

			@Override
			public BlockingQueue<Operation> createReadOperationQueue() {
				return readOpQueue == null ?
						super.createReadOperationQueue() : readOpQueue;
			}

			@Override
			public BlockingQueue<Operation> createWriteOperationQueue() {
				return writeOpQueue == null ?
						super.createWriteOperationQueue() : writeOpQueue;
			}

			@Override
			public Transcoder<Object> getDefaultTranscoder() {
				return transcoder == null ?
						super.getDefaultTranscoder() : transcoder;
			}

			@Override
			public FailureMode getFailureMode() {
				return failureMode == null ?
						super.getFailureMode() : failureMode;
			}

			@Override
			public HashAlgorithm getHashAlg() {
				return hashAlg == null ? super.getHashAlg() : hashAlg;
			}

			@Override
			public Collection<ConnectionObserver> getInitialObservers() {
				return initialObservers;
			}

			@Override
			public OperationFactory getOperationFactory() {
				return opFact == null ? super.getOperationFactory() : opFact;
			}

			@Override
			public long getOperationTimeout() {
				return opTimeout == -1 ?
						super.getOperationTimeout() : opTimeout;
			}

			@Override
			public int getReadBufSize() {
				return readBufSize == -1 ?
						super.getReadBufSize() : readBufSize;
			}

			@Override
			public boolean isDaemon() {
				return isDaemon;
			}

			@Override
			public boolean shouldOptimize() {
				return shouldOptimize;
			}
		};

	}

}