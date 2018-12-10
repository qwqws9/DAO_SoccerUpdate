package com.ejfrm.boardcontroller;

import com.ejfrm.boardaction.Action;
import com.ejfrm.boardaction.BoardDelete;
import com.ejfrm.boardaction.BoardList;
import com.ejfrm.boardaction.BoardPass;
import com.ejfrm.boardaction.BoardReply;
import com.ejfrm.boardaction.BoardUpdate;
import com.ejfrm.boardaction.BoardView;
import com.ejfrm.boardaction.BoardWrite;
import com.ejfrm.boardaction.CommentDelete;
import com.ejfrm.boardaction.CommentUpdate;
import com.ejfrm.boardaction.CommentWrite;

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
		}else if (command.equals("comment_write")) {
			action = new CommentWrite();
		}else if (command.equals("comment_edit_delete")) {
			action = new CommentUpdate();
		}else if (command.equals("comment_edit_form")) {
			action = new CommentUpdate();
		}else if (command.equals("comment_edit")) {
			action = new CommentUpdate();
		}else if (command.equals("comment_delete")) {
			action = new CommentDelete();
		}else if (command.equals("board_reply_form")) {
			action = new BoardReply();
		}else if (command.equals("board_reply")) {
			action = new BoardReply();
		}
		return action;
	}

}
