package domain;

public interface Observable {

	public void register(Observer observer);

	public void remove(Observer observer);

	public void notify(Object type);

}
