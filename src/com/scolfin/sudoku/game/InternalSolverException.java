package com.scolfin.sudoku.game;

public class InternalSolverException extends Exception {

	public InternalSolverException(String message) {
		super(message);
	}
	
	public InternalSolverException(Exception ex) {
		super(ex);
	}

	private static final long serialVersionUID = 7205181527289213479L;
}
