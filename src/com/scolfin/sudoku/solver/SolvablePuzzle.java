package com.scolfin.sudoku.solver;

import com.scolfin.sudoku.game.Block;
import com.scolfin.sudoku.game.InternalSolverException;
import com.scolfin.sudoku.game.Puzzle;
import com.scolfin.sudoku.game.RuleViolationException;

class SolvablePuzzle extends Puzzle {

	public SolvablePuzzle(Puzzle puzzle) {
		super(puzzle);
	}

	/*
	 * Reduces the puzzle, by finding solutions given the current state of the puzzle.
	 * Returns true if it successfully moved closer toward a solution.
	 */
	boolean reduce() throws RuleViolationException, InternalSolverException {
		int newSolutionCounter = 0;
		for (int i = 0; i < m_puzzle.length; ++i) {
			for (int j = 0; j < m_puzzle[i].length; j++) {
				Block b = m_puzzle[i][j];
				b.recalculatePossableSolutions(this);
				if (b.setSolution(this)) {
					++newSolutionCounter;
				}
			}
		}
		for (int i = 0; i < 9; ++i) {
			newSolutionCounter += m_rows[i].findSinglePossableEntries(this);
			newSolutionCounter += m_columns[i].findSinglePossableEntries(this);
			newSolutionCounter += m_squares[i].findSinglePossableEntries(this);
		}
		return newSolutionCounter != 0;
	}
}
