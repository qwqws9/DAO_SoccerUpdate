package com.ejfrm.boardcontroller;

import com.ejfrm.boardaction.Action;
import com.ejfrm.boardaction.BoardDelete;
import com.ejfrm.boardaction.BoardList;
import com.ejfrm.boardaction.BoardPass;
import com.ejfrm.boardaction.BoardUpdate;
import com.ejfrm.boardaction.BoardView;
import com.ejfrm.boardaction.BoardWrite;

public class ActionControl {
	
	private ActionControl() {
		
	}
	
	private static ActionControl instance = new ActionControl();
	
	public static ActionControl getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("board_list")) {
			action = new BoardList();
		}else if (command.equals("board_write_form")) {
			action = new BoardWrite();
		}else if (command.equals("board_write")) {
			action = new BoardWrite();
		}else if (command.equals("board_view")) {
			action = new BoardView();
		}else if (command.equals("board_pass")) {
			action = new BoardPass();
		}else if (command.equals("board_pass_check")) {
			action = new BoardPass();
		}else if (command.equals("board_update_form")) {
			action = new BoardUpdate();
		}else if (command.equals("board_update")) {
			action = new BoardUpdate();
		}else if (command.equals("board_delete")) {
			action = new BoardDelete();
		}
		return action;
	}

}
