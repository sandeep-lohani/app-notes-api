/**
 * 
 */
package com.nominet.notes.control.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.nominet.notes.Application;
import com.nominet.notes.controller.NotesController;
import com.nominet.notes.dto.NotesDto;

/**
 * @author Sandeep
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql") 
})
public class ControlNotesServiceIntegrationTest {
	
	@Autowired
	private NotesController notesController;
	
	@Before
	public void setup(){
		
	}
	
	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#getNotes()}.
	 */
	@Test
	public void testGetNotes() throws Exception{
		List<NotesDto> notesDtoList = notesController.getNotes();
		Assert.notNull(notesDtoList);
		Assert.isTrue(notesDtoList.get(0).getId().equals("1"));
		Assert.isTrue(notesDtoList.get(1).getId().equals("2"));
		Assert.isTrue(notesDtoList.get(0).getValue().equals("first comment"));
		Assert.isTrue(notesDtoList.get(1).getValue().equals("second comment"));
	}

	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#getNoteForId(java.lang.Long)}.
	 */
	@Test
	public void testGetNoteForId() throws Exception{
		String id = "1";
		NotesDto notesDto = notesController.getNoteForId(id);
		Assert.notNull(notesDto);
		Assert.isTrue(notesDto.getValue().equals("first comment"));
	}
	 
	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#updateNotes(java.lang.Long, java.lang.String)}.
	 */
	@Test
	public void testUpdateNotes() throws Exception{
		NotesDto notesDto = new NotesDto("1","first modified comment");
		notesDto = notesController.modifyNotes(notesDto);
		Assert.notNull(notesDto);
		Assert.isTrue(notesDto.getValue().equals("first modified comment"));
	}
	
	 
	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#deleteNote(java.lang.Long)}.
	 */
	@Test
	public void testDeleteNote() throws Exception{
		NotesDto notesDto = notesController.deleteNote("1");
		Assert.notNull(notesDto);
		Assert.isTrue(notesDto.getValue().equals("1"));
	}
	
	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#createNotes(java.lang.String)}.
	 *//*
	@Test
	public void testCreateNotes() throws Exception{
		NotesDto notesDto1 = new NotesDto("1", "first comment");;
		NotesDto notesDto2 = new NotesDto("2", "second comment");;
		notesDto1 = notesController.saveNotes(notesDto1);
		notesDto2 = notesController.saveNotes(notesDto2);
		Assert.notNull(notesDto1);
		Assert.notNull(notesDto2);
		Assert.isTrue(notesDto1.getValue().equals("first comment"));
		Assert.isTrue(notesDto2.getValue().equals("second comment"));
	}*/
}
