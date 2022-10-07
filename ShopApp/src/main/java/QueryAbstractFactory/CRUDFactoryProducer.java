package QueryAbstractFactory;

public class CRUDFactoryProducer {
	final static String DML = "DML";
	final static String DQL = "DQL";

	public CRUDAbstractFactory getFactory(String execfactory) {
		if (execfactory.equalsIgnoreCase(DML))
			return new ExecUpdateAbsFactory();
		else if (execfactory.equalsIgnoreCase(DQL))
			return new ExecQueryAbsFactory();
		return null;
	}
}
