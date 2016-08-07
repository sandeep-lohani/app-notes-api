/**
 * 
 */
package com.nominet.notes.control.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import com.nominet.notes.controller.NotesController;
import com.nominet.notes.dto.NotesDto;

/**
 * @author Sandeep
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ControlNotesServiceImplTest {

	@Mock
	private ControlNotesService controlNotesService;
	
	private NotesController notesController;
	
	@Before
	public void setup(){
		notesController = new NotesController(controlNotesService);
	}

	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#getNotes()}.
	 */
	@Test
	public void testGetNotes() throws Exception{
		List<NotesDto> notesDtoList = mockNotesDtoList();
		Mockito.when(controlNotesService.getNotes()).thenReturn(notesDtoList);
		notesDtoList = notesController.getNotes();
		Assert.notNull(notesDtoList);
		Assert.isTrue(notesDtoList.get(0).getValue().equals("first comment"));
		Assert.isTrue(notesDtoList.get(1).getValue().equals("second comment"));
	}

	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#getNoteForId(java.lang.Long)}.
	 */
	@Test
	public void testGetNoteForId() throws Exception{
		String id = "1";
		NotesDto notesDto = mockNotesDto();
		Mockito.when(controlNotesService.getNoteForId(Long.valueOf(id))).thenReturn(notesDto);
		notesDto = notesController.getNoteForId(id);
		Assert.notNull(notesDto);
		Assert.isTrue(notesDto.getValue().equals("first comment"));
	}

	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#updateNotes(java.lang.Long, java.lang.String)}.
	 */
	@Test
	public void testUpdateNotes() throws Exception{
		NotesDto notesDto = mockNotesDto();
		notesDto.setValue("first modified comment");
		Mockito.when(controlNotesService.updateNotes(Long.valueOf(notesDto.getId()), notesDto.getValue())).thenReturn(notesDto);
		notesDto = notesController.modifyNotes(notesDto);
		Assert.notNull(notesDto);
		Assert.isTrue(notesDto.getValue().equals("first modified comment"));
	}

	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#createNotes(java.lang.String)}.
	 */
	@Test
	public void testCreateNotes() throws Exception{
		NotesDto notesDto = mockNotesDto();
		Mockito.when(controlNotesService.createNotes(notesDto.getValue())).thenReturn(notesDto);
		notesDto = notesController.saveNotes(notesDto);
		Assert.notNull(notesDto);
		Assert.isTrue(notesDto.getValue().equals("first comment"));	
	}
	
	/**
	 * Test method for {@link com.nominet.notes.control.service.ControlNotesServiceImpl#deleteNote(java.lang.Long)}.
	 */
	@Test
	public void testDeleteNote() throws Exception{
		NotesDto notesDto = mockNotesDto();
		Mockito.when(controlNotesService.deleteNote(Long.valueOf(notesDto.getId()))).thenReturn(notesDto);
		notesDto = notesController.deleteNote(notesDto.getId());
		Assert.notNull(notesDto);
		Assert.isTrue(notesDto.getValue().equals("first comment"));
	}
	
	/**
	 * @return
	 */
	private NotesDto mockNotesDto() {
		NotesDto notesDto = new NotesDto("1", "first comment");
        return notesDto;
    }
	
	/**
	 * @return
	 */
	private List<NotesDto> mockNotesDtoList() {
		List<NotesDto> list = new ArrayList<NotesDto>();
		NotesDto notesDto1 = new NotesDto("1", "first comment");
		NotesDto notesDto2 = new NotesDto("2", "second comment");
        list.add(notesDto1);
        list.add(notesDto2);
		return list;
    }

}
