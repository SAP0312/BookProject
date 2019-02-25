# BookProject
This project is to create REST API's for a book management where we can add,read,delete and
update books and also provide an option to upload CSV files containing books.

## Prerequisites
- JAVA SDK 1.8
- Postgres SQL(You can add your own DB but then you'll have to change the DB connection settings in application.properties)
- Intellij

## Usage
### If using Intellij
- Open the project in IntelliJ
- Go to src/java/com.bookCRUD.BookAPIApp and right-click and select "Run BookAPIApp.main()" in IntelliJ
- Navigate to your web browser to http://localhost:8090 (You can change the port number in application.properties)

### For adding a book
     POST
     	API End point: /books
     	http://localhost:8090/books
     	Request Pay load or body

     	{
              "title": "Feast of vultures",
              "author": "Joey Joseph",
              "publisher": "Penguin",
              "language": "English",
              "category": "Politics"
     	}

### For getting a book by ID
         GET
            API End point: books/{book id}
        	http://localhost:8090/books/20
        	Response body
        	{
        		 "id": 20,
                 "title": "Feast of vultures",
                 "author": "Joey Joseph",
                 "publisher": "Penguin",
                 "language": "English",
                 "category": "Politics"
        	}

### For updating a book
         PUT
            API End point: /books/{book id}
        	http://localhost:8090/books/20
        	Request body
        	{
        		 "id": 20,
                 "title": "Feast of vultures",
                 "author": "Joey Joseph",
                 "publisher": "Penguin",
                 "language": "English",
                 "category": "Indian Politics"
        	}

### For deleting a book
         PUT
            API End point: /books/{book id}
        	http://localhost:8090/books/20

### For getting all books
         GET
            API End point: books
        	http://localhost:8090/books
        	Response body
        	[{
        		 "id": 20,
                 "title": "Feast of vultures",
                 "author": "Joey Joseph",
                 "publisher": "Penguin",
                 "language": "English",
                 "category": "Politics"
        	},
        	 {
                    "id": 123,
                    "title": "God Created the Integers",
                    "author": "Hawking Stephen",
                    "publisher": "Penguin",
                    "language": null,
                    "category": "mathematics"
                },
                ]
### For uploading CSV file
        POST
            API End Point : books/uploadFile
            http://localhost:8090/books/uploadFile