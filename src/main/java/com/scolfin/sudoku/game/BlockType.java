package com.scolfin.sudoku.game;

public enum BlockType {
	Row {
		@Override
		public Axis extractValue(Block [][] puzzle, int index) {
			return null;
		}
	},
	Column {
		@Override
		public Axis extractValue(Block [][] puzzle, int index) {
			return null;
		}
	},
	Square {

		@Override
		public Axis extractValue(Block[][] puzzle, int index) {
			return null;
		}
	};
	
	public abstract Axis extractValue(Block [][] puzzle, int index);
}
