package com.nominet.notes.control.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nominet.notes.model.NotesEntity;
/**
 * Created by sandeep.
 */
@Repository
public interface NotesRepository extends CrudRepository<NotesEntity, Long>{
	
	/**
	 * Get notes by id
	 * @param id
	 * @return
	 */
	NotesEntity findByNoteId(Long id);
}
