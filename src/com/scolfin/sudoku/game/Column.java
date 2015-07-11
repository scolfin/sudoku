package com.scolfin.sudoku.game;

public class Column extends Axis {

	public Column(Block[][] puzzle, int index) {
		super(puzzle, index);
	}

	@Override
	protected int getPosition(int row, int column) {
		return row;
	}

	@Override
	protected Block[] getAxis(Block[][] puzzle, int index) {
		Block[] ret = new Block[9];
		for (int i = 0; i < puzzle.length; i++) {
			ret[i] = puzzle[i][index];
		}
		return ret;
	}

}
