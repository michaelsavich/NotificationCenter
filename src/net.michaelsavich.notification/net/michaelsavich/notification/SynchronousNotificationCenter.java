package net.michaelsavich.notification;

import java.util.HashSet;

/**
 * An instance of NotificationCenter that dispatches {@link Notification notifications} to their {@link NotificationObserver observers} serially.
 * Note that an instance of this object is returned by the static method {@link NotificationCenter#primary()}. This class is exposed as public primarily because it
 * may be useful to subclass SynchronousNotificationCenter to implement custom bookkeeping. Furthermore, if for some reason you don't want to use
 * the singleton-like behavior of the static methods in NotificationCenter, you can instantiate this class directly and manage it yourself.
 */
public class SynchronousNotificationCenter extends NotificationCenter {

	/**
	 * {@inheritDoc}
	 * <p>
	 * The implementation of this method provided by SynchronousNotificationCenter is
	 * synchronous, and will halt everything until all objects have finished responding.
	 * As such, avoid adding {@link NotificationObserver observers} with long-running callbacks to a SynchronousNotificationCenter.
	 * </p>
	 * <p>
	 * When calling this method, remember that no guarantee is made regarding the order in which  are notified.
	 * </p>
	 */
	@Override
	public void post(Notification notification) {
			getObservers(notification.getName())
				.forEach(o -> o.receiveNotification(notification));
	}

}
