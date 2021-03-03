package Library.service;

import Library.entity.Author;
import Library.repository.Author.AuthorRepository;

import java.util.List;

public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveNewAuthor(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.save(author);
        return author;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    public List<Author> findAuthorsByKeyword(String keyWord) {
        return authorRepository.searchByKeyWord(keyWord);
    }
}
