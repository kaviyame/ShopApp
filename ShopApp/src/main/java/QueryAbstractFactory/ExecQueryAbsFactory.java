package QueryAbstractFactory;

public class ExecQueryAbsFactory extends CRUDAbstractFactory {

	final String SHOW_Operation = "SHOW";

	@Override
	public DQL getDQL(String DQLOperation) {
		if (SHOW_Operation.equalsIgnoreCase(DQLOperation))
			return new ShowDQL();
		return null;
	}

	@Override
	public DML getDML(String DMLOperation) {
		return null;
	}

}
