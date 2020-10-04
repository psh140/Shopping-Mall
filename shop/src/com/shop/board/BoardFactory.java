package com.shop.board;

import com.shop.Action;


public class BoardFactory {
	private static BoardFactory instance = null;
	private BoardFactory() {}
	
	public static BoardFactory getInstance() {
		if(instance == null) {
			instance = new BoardFactory();
		}
		
		return instance;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		
		if (cmd.equals("boardList")) {
			action = new BoardList();
		} else if(cmd.equals("boardView")) {
			action = new BoardView();
		} else if(cmd.equals("boardUpdate")) {
			action = new BoardUpdateForm();
		} else if(cmd.equals("boardInsertForm")) {
			action = new BoardInsertForm();
		} else if(cmd.equals("boardInsertAction")) {
			action = new BoardInsertAction();
		} else if(cmd.equals("boardUpdateAction")) {
			action = new BoardUpdateAction();
		} else if(cmd.equals("boardDelete")) {
			action = new BoardDelete();
		} else if(cmd.equals("commentInsert")) {
			action = new CommentInsert();
		} else if(cmd.equals("commentUpdateForm")) {
			action = new CommentUpdateForm();
		} else if(cmd.equals("commentUpdate")) {
			action = new CommentUpdateAction();
		} else if(cmd.equals("commentDelete")) {
			action = new CommentDelete();
		}
		return action;
		
	}
}
