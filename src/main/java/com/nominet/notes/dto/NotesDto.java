package com.nominet.notes.dto;


/**
 * @author Sandeep
 *
 */
public class NotesDto {

	private String id;

	public NotesDto(String value) {
		super();
		this.value = value;
	}

	public NotesDto(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public NotesDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "NotesDto [id=" + id + ", value=" + value + "]";
	}	
}
