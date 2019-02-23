# BookProject
This project is to create REST API's for a book management where we can add,read,delete and update books and also provide an option to upload CSV files containg books.

## For adding a book
     POST
     	 API End point: /books

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

## For getting a book by ID
         GET
            API End point: book/{book id}

        	http://localhost:8080/book/20

        	Response body

        	{
        		 "id": 20,
                 "title": "Feast of vultures",
                 "author": "Joey Joseph",
                 "publisher": "Penguin",
                 "language": "English",
                 "category": "Politics"

        	}