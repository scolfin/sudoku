package com.scolfin.sudoku.game;

public class Square extends Axis {

	private int m_startRow;
	private int m_startColumn;

	public Square(int index, Block[][] puzzle) {
		super(puzzle, index);
	}

	@Override
	protected int getPosition(int row, int column) throws IndexOutOfBoundsException {
		final int rowOffset = (row - m_startRow) * 3;
		if (rowOffset < 0) {
			throw new IndexOutOfBoundsException("This row index resolves to a different square than the one you are trying to get the index from.");
		}
		final int columnOffset = column - m_startColumn;
		if (columnOffset < 0) {
			throw new IndexOutOfBoundsException("This column index resolves to a different square than the one you are trying to get the index from.");
		}
		final int squareIdx = rowOffset + columnOffset;
		if (squareIdx > 8) {
			throw new IndexOutOfBoundsException("The index you are trying to aquire is not in this square.");
		}
		return squareIdx;
	}

	@Override
	protected Block[] getAxis(Block[][] puzzle, int index) {
		m_startRow = getStartRow(index);
		m_startColumn = getStartColumn(index);
		final Block[] ret = new Block[9];
		int idx = 0;
		for (int i = m_startRow; i < m_startRow + 3; i++ ) {
			for (int j = m_startColumn; j < m_startColumn + 3; j++) {
				ret[idx++] = puzzle[i][j];
			}
		}
		return ret;
	}

	private int getStartRow(int index) {
		return (int)(index / 3) * 3;
	}

	private int getStartColumn(int index) {
		return (index % 3) * 3;
	}

}
