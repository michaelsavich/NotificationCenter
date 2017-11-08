package net.michaelsavich.notification;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Runtime.getRuntime;

/**
 * An instance of NotificationCenter that dispatches {@link Notification notifications} to their {@link NotificationObserver observers} concurrently.
 * Note that an instance of this object is returned by the static method {@link NotificationCenter#primaryAsync()}. This class is exposed as public primarily because it
 * may be useful to subclass AsynchronousNotificationCenter to implement custom bookkeeping. Furthermore, if for some reason you don't want to use
 * the singleton-like behavior of the static methods in NotificationCenter, you can instantiate this class directly and manage it yourself.
 */

public class AsynchronousNotificationCenter extends NotificationCenter {

	/**
	 * {@inheritDoc}
	 * <p>
	 * The implementation of this method provided by AsynchronousNotificationCenter is asynchronous.
	 * </p>
	 * <p>
	 * When calling this method, remember that no guarantee is made regarding the order in which observers are notified.
	 * </p>
	 */
	@Override
	public void post(Notification notification) {
		getObservers(notification.getName()).stream()
		.map((o) -> (Runnable) () -> o.receiveNotification(notification))
		.forEach(ex::execute);
	}
	private ExecutorService ex = Executors.newFixedThreadPool(getRuntime().availableProcessors());
}
