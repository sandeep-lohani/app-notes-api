package com.nominet.notes.control.service;

import java.util.List;

import com.nominet.notes.dto.NotesDto;
/**
 * Created by sandeep.
 */
public interface ControlNotesService {
	
	NotesDto getNoteForId(Long id);
	
	List<NotesDto> getNotes();
	
	NotesDto updateNotes(Long id, String note);
	
	NotesDto deleteNote(Long id);
	
	NotesDto createNotes(String note);
}
