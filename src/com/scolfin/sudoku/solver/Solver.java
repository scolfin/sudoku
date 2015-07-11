package com.scolfin.sudoku.solver;

import com.scolfin.sudoku.game.Puzzle;

public class Solver {

	public static Puzzle getSolution(Puzzle puzzleToSolve) throws Exception {
		//Main solver loop.
		long iters = 0;
		final SolvablePuzzle puzzle = new SolvablePuzzle(puzzleToSolve);
		while (!puzzle.isCompleteSolution()) {
			++iters;
			if (!puzzle.reduce() && !puzzle.isCompleteSolution()) {
				System.out.println(puzzle.toString());
				throw new Exception("Can not solve puzzle in closed form! Reduction cycle count: " + iters);
			}
		}
		return puzzle;
	}

}
