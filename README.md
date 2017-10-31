# NotificationCenter
Yet another implementation of NSNotificationCenter in Java. ☕️


More detailed information coming soon, but for now, example usage: 
`Class Foo implements NotificationObserver {//...}
Foo f = new Foo();
NotificationCenter.primary.addObserver(f,"somethingDidChange");
NotificationCenter.primary.post("somethingDidChange");`
