package com.nominet.notes.control.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nominet.notes.control.repository.NotesRepository;
import com.nominet.notes.dto.NotesDto;
import com.nominet.notes.model.NotesEntity;
/**
 * Created by sandeep.
 */
@Component
@Transactional
public class ControlNotesServiceImpl implements ControlNotesService {
	
	@Autowired
	private NotesRepository notesRepository;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(ControlNotesServiceImpl.class);

	@Override
	public List<NotesDto> getNotes() {
		List<NotesDto> list = new ArrayList<>();
		try{
		Iterable<NotesEntity> notes = notesRepository.findAll();		
		notes.forEach(entity -> list.add(new NotesDto(String.valueOf(entity.getNoteId()), entity.getNote())));
		LOG.debug("Total notes retrieved from DB : " + list.size());
		} catch (Exception e){
			LOG.error("Error while getting note" + e.getMessage());
		}		
		return list;
	}

	@Override
	public NotesDto getNoteForId(Long id) {
		NotesDto dto = new NotesDto();
		try{
		NotesEntity entity= notesRepository.findByNoteId(id);
		LOG.debug("Notes retrieved from DB %s for id %s ",id, entity.toString());
		if(entity!=null)
			dto = new NotesDto(String.valueOf(entity.getNoteId()), entity.getNote());
		} catch (Exception e){
			LOG.error("Error while getting note for id "+ id + e.getMessage());
		}
		return dto;
	}
	
	@Override
	public NotesDto updateNotes(Long id, String note) {
		try{
		NotesEntity entity = notesRepository.findByNoteId(id);
		LOG.debug("Updating note for id %s old value %s with new value %s",id, entity.getNote(), note);
		if(entity != null) {
			entity.setNote(note);
			notesRepository.save(entity);
		}
		} catch (Exception e){
			LOG.error("Error while updating note for id "+ id + e.getMessage());
		}
		return new NotesDto(String.valueOf(id), note);
	}

	@Override
	public NotesDto deleteNote(Long id) {
		try{
		LOG.debug("deleting note with id %s",id);
		notesRepository.delete(id);
		} catch (Exception e){
			LOG.error("Error while deleting note for id "+ id + e.getMessage());
		}
		return new NotesDto(String.valueOf(id));
	}
	
	@Override
	public NotesDto createNotes(String note) {
		NotesDto dto = new NotesDto();
		try{
		NotesEntity entity = new NotesEntity(note);			
		notesRepository.save(entity);
		LOG.debug("Saved entity with id %s and note %s", entity.getNoteId(), note);		
		dto.setValue(entity.getNote());
		dto.setId(String.valueOf(entity.getNoteId()));
		} catch (Exception e){
			LOG.error("Error while saing new note "+ note + e.getMessage());
		}
		return dto;
	}
}
