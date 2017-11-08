package net.michaelsavich.notification;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * NotificationCenter objects register {@link NotificationObserver NotificationObservers}
 * to be notified when a certain {@link Notification} is posted.
 */
public abstract class NotificationCenter {

	/**
	 * Gets the default <b>(synchronous)</b> NotificationCenter instance.
	 * This method returns the same instance each time it is called, functioning like a singleton.
	 * <p>
	 * Note both that this method and {@link NotificationCenter#primaryAsync()} return different instances, and that
	 * the object returned by this method notifies observers <b>synchronously.</b>
	 * </p>
	 * <p>
	 * Calling this method for the first time creates a new NotificationCenter, as the field backing
	 * it is <a href=https://en.wikipedia.org/wiki/Lazy_initialization>lazily initialized</a>. Suffice it to say,
	 * if you do not intend to use the NotificationCenter provided by this method, it is best to avoid calling it entirely.
	 * </p>
	 * @return An instance of a concrete NotificationCenter subclass.
	 */
	public static NotificationCenter primary() {
		if (primary == null) {
			primary = new SynchronousNotificationCenter();
		}
		return primary;
	}
	private static SynchronousNotificationCenter primary = null;

	/**
	 * Gets the default <b>(asynchronous)</b> NotificationCenter instance.
	 * This method returns the same instance each time it is called, functioning like a singleton.
	 * <p>
	 * Note both that this method and {@link NotificationCenter#primary()} return different instances, and that
	 * the object returned by this method notifies observers <b>asynchronously.</b>
	 * </p>
	 * <p>
	 * Calling this method the first time creates a new NotificationCenter, that is to say, the field backing
	 * it is <a href=https://en.wikipedia.org/wiki/Lazy_initialization>lazily initialized</a>. Suffice it to say,
	 * if you do not intend to use the NotificationCenter provided by this method, it is best to avoid calling it entirely.
	 * </p>
	 * @return An instance of a concrete NotificationCenter subclass.
	 */
	public static NotificationCenter primaryAsync() {
		if (primaryAsync == null) {
			primaryAsync = new AsynchronousNotificationCenter();
		}
		return primaryAsync;
	}
	private static AsynchronousNotificationCenter primaryAsync = null;

	/**
	 * The Map used to route incoming {@link Notification} objects to their registered {@link NotificationObserver NotificationObservers}.
	 */
	private Map<String,Set<NotificationObserver>> dispatchTable = new HashMap<>();

	protected Set<NotificationObserver> getObservers(String notificationName) {
		return dispatchTable.getOrDefault(notificationName, new HashSet());
	}

	/**
	 * Posts a {@link Notification} that observers can react to.
	 * @param notification The Notification to post.
	 */
	public abstract void post(Notification notification);

	/**
	 * Posts a {@link Notification} with the specified name.
	 * Equivalent to calling {@code NotificationCenter.post(new Notification(notificationName, null))}.
	 * @param notificationName The Notification to post.
	 */
	public void post(String notificationName) {
		this.post(new Notification(notificationName, null));
	}

	/**
	 * Adds an observer that reacts when a specific {@link Notification} is posted.
	 * More than one object can register to observe a Notification.
	 * @param observer The observer to add.
	 * @param notificationName The name of the Notification to register the observer for.
	 */
	public void addObserver(NotificationObserver observer, String notificationName) {
		dispatchTable.putIfAbsent(notificationName, new HashSet<>());
		dispatchTable.get(notificationName).add(observer);
	}

	/**
	 * Removes an observer so that it will no longer react to a {@link Notification}.
	 * @param observer The observer to remove.
	 * @param notificationName The name of the Notification to unregister the observer for.
	 */
	public void removeObserver(NotificationObserver observer, String notificationName) {
		Set<NotificationObserver> observers = dispatchTable.get(notificationName);
		if (observers != null) observers.remove(observer);
	}

}
