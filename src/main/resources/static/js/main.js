// The root URL for the RESTful services
var rootURL = "http://localhost:8080/local/notes";

var currentNote;

// Retrieve notes list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

$('#btnAdd').click(function() {
	newNote();
	return false;
});

$('#btnSave').click(function() {
	if ($('#noteId').val() == '')
		addNote();
	else
		updateNote();
	return false;
});

$('#btnDelete').click(function() {
	deleteNote();
	return false;
});

$('#notesList').on('click', 'li a', function() {
	findById($(this).data('identity'));
});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByKey(searchKey);
}

function newNote() {
	$('#btnDelete').hide();
	currentNote = {};
	renderDetails(currentNote); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByKey(searchKey) {
	console.log('findByKey: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentNote = data;
			renderDetails(currentNote);
		}
	});
}

function addNote() {
	console.log('addNote');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Note created successfully');
			$('#btnDelete').show();
			$('#noteId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addNote error: ' + textStatus);
		}
	});
}

function updateNote() {
	console.log('updateNote');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Note updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateNote error: ' + textStatus);
		}
	});
}

function deleteNote() {
	console.log('deleteNote');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#noteId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Note deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteNote error' + textStatus);
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#notesList li').remove();
	$.each(list, function(index, note) {
		$('#notesList').append('<li><a href="#" data-identity="' + note.id + '">'+note.value+'</a></li>');
	});
}

function renderDetails(note) {
	$('#noteId').val(note.id);
	$('#value').val(note.value);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var noteId = $('#noteId').val();
	return JSON.stringify({
		"id": noteId == "" ? null : noteId, 
		"value": $('#value').val(), 
		});
}