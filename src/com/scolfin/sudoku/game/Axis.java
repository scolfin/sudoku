package com.scolfin.sudoku.game;

import java.util.ArrayList;
import java.util.List;

public abstract class Axis {
	private final int [] m_axisContains;
	private final Block[] m_axis;
	private final String m_stringReper;

	public Axis(Block[][] puzzle, int index) {
		m_axis = getAxis(puzzle, index);
		m_axisContains = getInitialAxisContCacheArray();
		for (int i = 0; i < m_axis.length; i++) {
			m_axisContains[m_axis[i].getSolution()] = i;
		}
		m_stringReper = String.valueOf(index);
	}

	boolean numberExists(int number) {
		return m_axisContains[number] != -1;
	}

	void setNumberExists(int number, int row, int column) throws RuleViolationException, InternalSolverException {
		int position;
		try {
			position = getPosition(row, column);
		} catch (IndexOutOfBoundsException ex) {
			throw new InternalSolverException(ex);
		}
		if (m_axisContains[number] != -1 && m_axisContains[number] != position) {
			throw new RuleViolationException("A solution can only appear once in an axis!");
		}
		m_axisContains[number] = position;
	}
	
	
	/*
	 *  Looks at the axis for only one values that can only appear in one place on that axis and fills it in.
	 */
	public int findSinglePossableEntries(final Puzzle puzzle) throws InternalSolverException, RuleViolationException {
		int foundSolution = 0;
		final List<ArrayList<Block>> matches = new ArrayList<ArrayList<Block>>(10);
		for (int i = 0; i < 10; ++i) {
			matches.add(new ArrayList<Block>());
		}
		for (final Block b : m_axis) {
			if (!b.isSolved()) {
				final List<Integer> possableSolutions = new ArrayList<Integer>(b.getPossableSolutions());
				b.resetPotentialSolutions();
				for (Integer possableSolution : possableSolutions) {
					matches.get(possableSolution).add(b);
					b.addPossableSolution(possableSolution);
				}
			}
		}
		for (int i = 1; i < 10; ++i) {
			ArrayList<Block> match = matches.get(i);
			if (match.size() == 1) {
				Block b = match.get(0);
				b.setSolution(i, puzzle);
				m_axisContains[i] = getPosition(b.getRow(), b.getColumn());
				++foundSolution;
			}
		}
		return foundSolution;
	}

	protected abstract int getPosition(final int row, final int column) throws IndexOutOfBoundsException;
	protected abstract Block[] getAxis(Block[][] puzzle, int index);
	
	private static int[] getInitialAxisContCacheArray() {
		final int[] retArray = new int[10];
		for (int i = 0; i < retArray.length; ++i) {
			retArray[i] = -1;
		}
		return retArray;
	}

	@Override
	public String toString() {
		return m_stringReper;
	}

}
