package com.scolfin.sudoku.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.scolfin.sudoku.game.Puzzle;
import com.scolfin.sudoku.solver.Solver;

public class Main {

	public static void main(String[] args) {
		Puzzle solution;
		try {
			if (args.length == 0) {
				System.out.println("Failure! You must pass the file with the puzzles to solve!");
				return;
			}
			Puzzle toSolve = loadPuzzleFromFile(args[0]);
			System.out.println(toSolve + "\n\n");
			solution = Solver.getSolution(toSolve);
			System.out.println("Solution found!");
			System.out.println(solution);
		} catch (Exception e) {
			System.out.println("Failure! Reason --> " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static Puzzle loadPuzzleFromFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new IOException(String.format("The file '%s' does NOT exist!", filePath));
		}
		InputStream fs = new FileInputStream(file);
		Reader reader = new InputStreamReader(fs);
		try {
			int val = reader.read();
			int [][] rawPuzzle = new int[9][];
			rawPuzzle[0] = new int[9];
			int i = 0;
			int j = 0;
			while (val != -1) {
				if (val >= 48 && val <= 57) {
					if (j == 9) {
						j = 0;
						if (++i < 9) {
							rawPuzzle[i] = new int[9];
						}
					} else {
						rawPuzzle[i][j] = Integer.parseInt((Character.valueOf((char)val).toString()));
						val = reader.read();
						++j;
					}
				} else {
					val = reader.read();
				}

			}
			return new Puzzle(rawPuzzle);
		} finally {
			reader.close();
		}
	}

}
