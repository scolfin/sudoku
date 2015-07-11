package com.scolfin.sudoku.game;

public class Row extends Axis {

	public Row(Block[][] puzzle, int index) {
		super(puzzle, index);
	}

	@Override
	protected int getPosition(int row, int column) {
		return column;
	}

	@Override
	protected Block[] getAxis(final Block[][] puzzle, final int index) {
		Block[] ret = new Block[9];
		for (int i = 0; i < puzzle.length; i++) {
			ret[i] = puzzle[index][i];
		}
		return ret;
	}

}
