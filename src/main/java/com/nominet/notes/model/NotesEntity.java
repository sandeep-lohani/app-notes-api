package com.nominet.notes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "notes")
public class NotesEntity {
	
	@Id
	@GeneratedValue
	private Long noteId;
	
	@Column(nullable = false)
	private String note;

	public NotesEntity(String note) {
		super();
		this.note = note;
	}

	public NotesEntity() {
		super();
	}
	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "NotesEntity [noteId=" + noteId + ", note=" + note + "]";
	}

}
