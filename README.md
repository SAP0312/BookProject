# BookProject
This project is to create REST API's for a book management where we can add,read,delete and
update books and also provide an option to upload CSV files containing books.

## For adding a book
     POST
     	API End point: /books
     	http://localhost:8080/books
     	Request Pay load or body

     	{
              "title": "Feast of vultures",
              "author": "Joey Joseph",
              "publisher": "Penguin",
              "language": "English",
              "category": "Politics"
     	}

## For getting a book by ID
         GET
            API End point: books/{book id}
        	http://localhost:8080/books/20
        	Response body
        	{
        		 "id": 20,
                 "title": "Feast of vultures",
                 "author": "Joey Joseph",
                 "publisher": "Penguin",
                 "language": "English",
                 "category": "Politics"
        	}

## For updating a book
         PUT
            API End point: /books/{book id}
        	http://localhost:8080/books/20
        	Request body
        	{
        		 "id": 20,
                 "title": "Feast of vultures",
                 "author": "Joey Joseph",
                 "publisher": "Penguin",
                 "language": "English",
                 "category": "Indian Politics"
        	}

## For deleting a books
         PUT
            API End point: /book/{book id}
        	http://localhost:8080/book/20

## For getting all books
         GET
            API End point: books/
        	http://localhost:8080/books/20
        	Response body
        	{
        		 "id": 20,
                 "title": "Feast of vultures",
                 "author": "Joey Joseph",
                 "publisher": "Penguin",
                 "language": "English",
                 "category": "Politics"
        	}