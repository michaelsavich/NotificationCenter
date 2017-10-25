package net.michaelsavich.notification;

/**
 * A simple interface for objects that want to respond to {@link Notification} objects being posted to a {@link NotificationCenter}.
 * An object must implement this interface to be {@link NotificationCenter#addObserver(NotificationObserver, String) registered} to react to Notifications.
 */
public interface NotificationObserver {
	/**
	 * Callback method to respond to a posted {@link Notification}.
	 * @param notification The Notification that was posted. May or may not have an attached object.
	 */
	public void receiveNotification(Notification notification);
}