package QueryAbstractFactory;

public abstract class CRUDAbstractFactory {
	public abstract DML getDML(String DMLOperation);

	public abstract DQL getDQL(String DQLOperation);
}
