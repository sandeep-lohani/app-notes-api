package com.nominet.notes.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nominet.notes.control.service.ControlNotesService;
import com.nominet.notes.dto.NotesDto;

/**
 * Created by sandeep
 */
@RestController
@RequestMapping(value = "/notes", produces = APPLICATION_JSON_VALUE)
public class NotesController {
	
    private static final Logger LOG = LoggerFactory.getLogger(NotesController.class);
    
    private final ControlNotesService controlNotesService;

    @Autowired
    public NotesController(ControlNotesService controlNotesService) {
    	this.controlNotesService = controlNotesService;
    }
    
    /**
     * Get all notes
     * @return list of notes
     */
    @RequestMapping(method = GET)
    public
    @ResponseBody
    List<NotesDto> getNotes() {
        LOG.debug("get all notes");
        return controlNotesService.getNotes();
    }
    
    /**
     * Get note for id
     * @param id
     * @return note
     */
    @RequestMapping(value="/{key}", method = GET)
    public
    @ResponseBody
    NotesDto getNoteForId(@PathVariable("key") String id) {
        LOG.debug("get note for id : " + id);
        return controlNotesService.getNoteForId(Long.valueOf(id));
    }
    
    /**
     * Save note
     * @param note
     * @return
     */
    @RequestMapping(method = PUT, consumes=APPLICATION_JSON_VALUE)
    public
    NotesDto saveNotes(@RequestBody NotesDto note) {
        LOG.debug("save notes");
        return controlNotesService.createNotes(note.getValue());
    }
    
    /**
     * Update note
     * @param note
     * @return
     */
    @RequestMapping(method = POST, consumes=APPLICATION_JSON_VALUE)
    public
    NotesDto modifyNotes(@RequestBody NotesDto note) {
        LOG.debug("modify notes");
        return controlNotesService.updateNotes(Long.valueOf(note.getId()), note.getValue());
    }
    
    /**
     * Delete note
     * @param id
     * @return
     */
    @RequestMapping(value="/{key}", method = DELETE)
    public
    NotesDto deleteNote(@PathVariable("key") String id) {
        LOG.debug("delete note for id : "+ id);
        return controlNotesService.deleteNote(Long.valueOf(id));
    }

}
