package com.scolfin.sudoku.solver;

import com.scolfin.sudoku.game.Block;
import com.scolfin.sudoku.game.InternalSolverException;
import com.scolfin.sudoku.game.Puzzle;
import com.scolfin.sudoku.game.RuleViolationException;

class SolvablePuzzle extends Puzzle {

	public SolvablePuzzle(Puzzle puzzle) {
		super(puzzle);
	}

	/**
	 * Reduces the puzzle, by finding solutions given the current state of the puzzle.
	 * Returns true if it successfully moved closer toward a solution.
	 */
	boolean reduce() throws RuleViolationException, InternalSolverException {
		int newSolutionCounter = 0;
		newSolutionCounter = resetPossableSolutionsForAll(newSolutionCounter);
		for (int i = 0; i < 9; ++i) {
			newSolutionCounter += m_rows[i].findSinglePossibleEntries(this);
			newSolutionCounter = resetPossableSolutionsForAll(newSolutionCounter);
			newSolutionCounter += m_columns[i].findSinglePossibleEntries(this);
			newSolutionCounter = resetPossableSolutionsForAll(newSolutionCounter);
			newSolutionCounter += m_squares[i].findSinglePossibleEntries(this);
		}
		return newSolutionCounter != 0;
	}

	private int resetPossableSolutionsForAll(int newSolutionCounter) throws InternalSolverException, RuleViolationException {
		for (int i = 0; i < m_puzzle.length; ++i) {
			for (int j = 0; j < m_puzzle[i].length; j++) {
				Block b = m_puzzle[i][j];
				b.recalculatePossableSolutions(this);
				if (b.setSolution(this)) {
					++newSolutionCounter;
				}
			}
		}
		return newSolutionCounter;
	}
}
