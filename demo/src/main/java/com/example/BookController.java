package com.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {
    private final BookService bookService = new BookService();

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.books.add(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.books.stream().filter(b -> b.id == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        bookService.books.removeIf(b -> b.id == id);
        bookService.books.add(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.books.removeIf(b -> b.id == id);
    }
}

@Service
class BookService {
    List<Book> books = new ArrayList<>();
}

@RestController
@RequestMapping("/users")
class UserController {
    UserService userService = new UserService();

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.users.add(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.users.stream().filter(u -> u.id == id).findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.users.removeIf(u -> u.id == id);
    }
}

@Service
class UserService {
    List<User> users = new ArrayList<>();
}

class User {
    public int id;
    public String name;
    public String email;
}

