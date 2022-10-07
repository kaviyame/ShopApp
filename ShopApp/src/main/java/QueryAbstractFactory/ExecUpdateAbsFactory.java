package QueryAbstractFactory;

public class ExecUpdateAbsFactory extends CRUDAbstractFactory {

	final String ADD_OPERATION = "ADD";
	final String UPDATE_OPERATION = "UPDATE";
	final String REMOVE_OPERATION = "REMOVE";

	@Override
	public DML getDML(String DMLOperation) {
		if (ADD_OPERATION.equalsIgnoreCase(DMLOperation))
			return new AddDML();
		else if (UPDATE_OPERATION.equalsIgnoreCase(DMLOperation))
			return new UpdateDML();
		else if (REMOVE_OPERATION.equalsIgnoreCase(DMLOperation))
			return new RemoveDML();
		return null;
	}

	@Override
	public DQL getDQL(String DQLOperation) {
		return null;
	}
}
