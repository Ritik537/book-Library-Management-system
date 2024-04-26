# book-Library-Management-system
The project is a Spring Boot application facilitating CRUD operations for managing a book library, including books, authors, and book rentals via RESTful endpoints.

i have divided this project in 4 packages 

package com.example.librarymanagement.Controllers : it contain three controllers for each one Author controller , Book controller and rental controller . these controllers contain endpoints for api request . 

package com.example.librarymanagement.Models : it contain 3 model class for each one Author.java , Book.java , Rental.java . these contains data models fields realted to each one.

package com.example.librarymanagement.Repositories : it contain repositories for each AuthorRepositories , BookRepositories, RentalRepositories . it controll the database request methods .

package com.example.librarymanagement.Service : this service package is medium of controllers and repositories which call the repositories methods of particular repositories class . service package also contain service class of each Author , Book , Rental . 

In this spring boot project  , we add dependency of spring web for web application , spring data jpa for databse connection , h2 database (which is an in-memory database) for relational tables. and spring - starter- validation for validate input ensure required fields are provided.

To run this project , we have to run this in spring boot suite tool and run as spring boot app . it will run by default on port 8080 (http) and h2 databse can access on url ( http://localhost:8080/h2-console ) .

we will use postman for hit our Rest Api endpoints 

Let's understand the endpoints of each controller

1.) Book Controller Endpoints: the default endpoint of this controller is @RequestMapping("/api/books")


@GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

    // use to get all books from db
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    // usr to get book by id from db
    }

    @PostMapping
    public ResponseEntity<Book> createBook( @RequestBody @Valid Book book) {
    // use to insert a new book in db
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable @Valid Long id, @RequestBody @Valid Book updatedBook) {
    // use to update existing book with book id
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    // delete a particular book
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
    // use to get books which is not rented 
    }

    @GetMapping("/rented")
    public ResponseEntity<List<Book>> getRentedBooks() {
    // use to get books which is rented
    }
    
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) {
    // use to get alist of books by author id 
    }


2.) Author Controller Endpoints: the default endpoint of this controller is @RequestMapping("/api/authors")

@GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        // use to get all author list 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
       // use to get author by author id
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody @Valid Author author) {
        // use to add new author in db
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable @Valid Long id, @RequestBody @Valid Author updatedAuthor) {
        // use to update existing author in db with author id
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        // use to delete an author by author id
    }

3.) Rental Controller Endpoints: the default endpoint of this controller is @RequestMapping("/api/rentals")

@PostMapping("/rent")
    public ResponseEntity<Rental> rentBook(@RequestParam @Valid Long bookId, @RequestParam @Valid String renterName) {
        // use to rent a book with book id and renter name
    }

    @PostMapping("/return")
    public ResponseEntity<Rental> returnBook(@RequestParam @Valid Long rentalId) {
        // use to return a book by rental id
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        // use to get all rental records
    }
    
    @GetMapping("/overdue")
    public ResponseEntity<List<Rental>> getOverdueRentalsEndpoint() {
        // use to get list of all overdue rentals (set time of return date of book is 14 days from renting time)
    }


    i use spring validation for validate input that is provide when post data in table .

    in application properties file i have done configuration of database like password to access it

    for testing purpose , i have created 2 package one for buisness logic testand one for integration test for endpoints of all controllers 


    i have use spring data jpa for entity object mapping for database and models.

