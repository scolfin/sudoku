package com.scolfin.sudoku.game;

public class Puzzle {

	protected final Block [][] m_puzzle;
	protected final Axis[] m_rows;
	protected final Axis[] m_columns;
	protected final Square[] m_squares;

	public Puzzle(final int [][] puzzle) {
		if (puzzle.length != 9) {
			throw new IllegalArgumentException("The puzzle must be a 9X9 puzzle.");
		}
		m_puzzle = new Block[puzzle.length][];
		for (int i = 0; i < m_puzzle.length; i++) {
			if (puzzle[i].length != 9) {
				throw new IllegalArgumentException("The puzzle must be a 9X9 puzzle.");
			}
			m_puzzle[i] = new Block[puzzle[i].length];
			for (int j = 0; j < m_puzzle[i].length; ++j) {
				m_puzzle[i][j] = new Block(puzzle[i][j], i, j);
			}
		}
		m_rows = new Axis[puzzle.length];
		m_columns = new Axis[puzzle.length];
		m_squares = new Square[puzzle.length];
		for (int i = 0; i < m_rows.length; i++) {
			m_rows[i] = new Row(m_puzzle, i);
			m_columns[i] = new Column(m_puzzle, i);
			m_squares[i] = new Square(i, m_puzzle);
		}
	}
	
	protected Puzzle (final Puzzle puzzle) {
		m_puzzle = puzzle.m_puzzle;
		m_rows = puzzle.m_rows;
		m_columns = puzzle.m_columns;
		m_squares = puzzle.m_squares;
	}

	public boolean isCompleteSolution() {
		for (int i = 0; i < m_puzzle.length; i++) {
			for (int j = 0; j < m_puzzle.length; j++) {
				if (!m_puzzle[i][j].isSolved()) {
					return false;
				}
			}
		}
		return true;
	}
	
	Axis getRow(int idx) {
		return m_rows[idx];
	}
	
	Axis getColumn(int idx) {
		return m_columns[idx];
	}
	
	Axis getSquare(int idx) {
		return m_squares[idx];
	}
	
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m_puzzle.length; ++i) {
			for (int j = 0; j < m_puzzle[i].length; ++j) {
				sb.append(m_puzzle[i][j].toString()).append(" ");
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}
