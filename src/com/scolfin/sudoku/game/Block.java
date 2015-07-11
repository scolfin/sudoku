package com.scolfin.sudoku.game;

import java.util.ArrayList;
import java.util.List;

public class Block {
	
	private final List<Integer> m_possableSolutions;
	private int m_solution;
	private final int m_row;
	private final int m_column;
	
	public Block(int value, int row, int column) {
		m_possableSolutions = new ArrayList<Integer>();
		m_solution = value;
		m_row = row;
		m_column = column;
	}
	
	public boolean isSolved() {
		return m_solution != 0;
	}
		
	/*
	 * Sets the solution to this block if it has only one possible solution.
	 * Returns true if it sets a solution, false otherwise.
	 */
	public boolean setSolution(Puzzle puzzle) throws RuleViolationException, InternalSolverException {
		if (isSolved()) {
			return false;
		}
		if (m_possableSolutions.size() == 1) {
			m_solution = m_possableSolutions.get(0);
			m_possableSolutions.clear();
			puzzle.getRow(m_row).setNumberExists(m_solution, m_row, m_column);
			puzzle.getColumn(m_column).setNumberExists(m_solution, m_row, m_column);
			puzzle.getSquare(getSquareIndex()).setNumberExists(m_solution, m_row, m_column);
			return true;
		}
		return false;
	}
	
	void setSolution(int solutionValue, Puzzle puzzle) throws InternalSolverException, RuleViolationException {
		if (solutionValue == m_solution) {
			return; //This is a bug, fix this!
		}
		if (isSolved() && solutionValue != m_solution) {
			throw new RuleViolationException("The block you are trying to set already has a solution.");
		}
		if (!m_possableSolutions.contains(solutionValue)) {
			throw new InternalSolverException("The solution you are trying to set is not in the collection of possible solutions to this block!");
		}
		m_solution = solutionValue;
		m_possableSolutions.clear();
		puzzle.getRow(m_row).setNumberExists(solutionValue, m_row, m_column);
		puzzle.getColumn(m_column).setNumberExists(solutionValue, m_row, m_column);
		puzzle.getSquare(getSquareIndex()).setNumberExists(solutionValue, m_row, m_column);
	}

	public void recalculatePossableSolutions(Puzzle puzzle) throws InternalSolverException {
		if (isSolved()) {
			return;
		}
		resetPossibleSolutions();
		Axis row = puzzle.getRow(m_row);
		Axis column = puzzle.getColumn(m_column);
		Axis square = puzzle.getSquare(getSquareIndex());
		for (int i = 1; i < 10; i++) {
			boolean numberExists = false;
			if (row.numberExists(i)) {
				numberExists = true;
			}
			if (!numberExists && column.numberExists(i)) {
				numberExists = true;
			}
			if (!numberExists && square.numberExists(i)) {
				numberExists = true;
			}
			if (!numberExists) {
				addPossibleSolution(i);
			}
		}
		if (m_possableSolutions.isEmpty()) {
			throw new InternalSolverException("The list of potential solutions is empty!");
		}
	}

	protected void addPossibleSolution(int i) {
		m_possableSolutions.add(i);
	}

	protected void resetPossibleSolutions() {
		m_possableSolutions.clear();
	}

	public int getSolution() {
		return m_solution;
	}

	public int getSquareIndex() {
		return ((int)(m_row/3) * 3) + (int)(m_column/3);
	}
	
	public List<Integer> getPossableSolutions() {
		return m_possableSolutions;
	}
	
	public int getRow() {
		return m_row;
	}
	
	public int getColumn() {
		return m_column;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getSolution());
	}
}
