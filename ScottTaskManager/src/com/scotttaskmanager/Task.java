package com.scotttaskmanager;

public class Task {

	private String text;
	private Boolean complete;

	public Task() {
		this.text = "";
		this.complete = false;
	}

	public Task(String text) {
		this.setText(text);
		this.setComplete(false);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	// Task implicity inherits from "object"

	@Override
	public String toString() {
		// FIXME: try using a String Builder instead of doing string concatenation...and
		// then display to System out..
		return this.getText() + (getComplete() ? Constants.COMPLETE : "");

	}
}
