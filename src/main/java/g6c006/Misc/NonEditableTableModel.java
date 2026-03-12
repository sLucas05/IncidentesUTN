package g6c006.Misc;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("all")
public class NonEditableTableModel extends DefaultTableModel {
	private boolean[] editableColumns;

	public NonEditableTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		editableColumns = new boolean[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			editableColumns[i] = true;
		}
	}

	public void setColumnEditable(int columnIndex, boolean editable) {
		editableColumns[columnIndex] = editable;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return editableColumns[column];
	}
}
