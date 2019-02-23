# BookProject
This project is to create REST API's for a book management where we can add,read,delete and update books and also provide an option to upload CSV files containg books.

##For adding a book
POST
	api/books

	http://localhost:8080/book

	Request Pay load or body

	{
		 "id": 20,
         "title": "Feast of vultures",
         "author": "Joey Joseph",
         "publisher": "Penguin",
         "language": "English",
         "category": "Politics"

	}