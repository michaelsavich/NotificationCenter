package net.michaelsavich.notification;

import java.util.Optional;

/**
 * A Notification with a name and, optionally, an attached object.
 * Notifications are intended to be {@link NotificationCenter#post(Notification) posted} to an instance of {@link NotificationCenter}.
 */
public class Notification {
	private final String name;
	private final Object payload;

	/**
	 * Convenience constructor equivalent to calling new {@code Notification(name, null)}.
	 * @param name The name of this Notification. Although it is legal to pass null to this parameter,
	 *             doing so creates a Notification that effectively cannot be dispatched, and so it is strongly
	 *             discouraged to pass null here.
	 */
	public Notification(String name) {
		this(name,null);
	}
	/**
	 * Convenience constructor equivalent to calling new {@code Notification(name, null)}.
	 * @param name The name of this Notification. Although it is legal to pass null to this parameter,
	 *             doing so creates a Notification that effectively cannot be dispatched, and so it is strongly
	 *             discouraged to pass null here.
	 * @param payload An object to attach to this Notification. This object can be used by the observer,
	 *                potentially obviating the need for the observer to ask the sender for additional information.
	 */
	public Notification(String name, Object payload) {
		this.name = name;
		this.payload = payload;
	}

	/**
	 * The name of this Notification.
	 * This {@link String} is what {@link NotificationObserver} objects are registered against.
	 * @return The name of this Notification.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the attached {@link Notification#payload}, if one exists.
	 * @return An Optional containing either the payload or null if there is no payload.
	 */
	public Optional<Object> getObject() {
		return Optional.ofNullable(payload);
	}

	/**
	 * Convenience method that attempts to cast the {@link Notification#payload} to a {@link String}.
	 * Strings are a very common type of payload, so this method is provided to make frequent operations more convenient.
	 * @return An Optional that contains the payload iff it exists and can be casted to a String.
	 */
	public Optional<String> getString() {
		String result = null;
		if (payload instanceof String) result = (String) payload;
		return Optional.ofNullable(result);
	}

	/**
	 * Convenience method that attempts to cast the {@link Notification#payload} to a {@link Integer}.
	 * Integers are a very common type of payload, so this method is provided to make frequent operations more convenient.
	 * @return An Optional that contains the payload iff it exists and can be casted to a Integer.
	 */
	public Optional<Integer> getInteger() {
		Integer result = null;
		if (payload instanceof Integer) result = (Integer) payload;
		return Optional.ofNullable(result);
	}

}