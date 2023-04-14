package com.group.bookbillyapp.service.book;

import com.group.bookbillyapp.domain.book.Book;
import com.group.bookbillyapp.domain.book.BookRepository;
import com.group.bookbillyapp.domain.user.User;
import com.group.bookbillyapp.domain.user.UserRepository;
import com.group.bookbillyapp.domain.user.loanhistory.UserLoanHistory;
import com.group.bookbillyapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.bookbillyapp.dto.book.request.BookCreateRequest;
import com.group.bookbillyapp.dto.book.request.BookLoanRequest;
import com.group.bookbillyapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        //1. Book객체를 가져온다. optional을 통해 존재하지 않는다면 예외를 던진다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        //2. 대출 이력이 있는 책인지 확인. 만약 대출이력이 있으며, 반납되지 않은 책이라면 예외를 던진다.
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출된 책입니다.");
        }

        //3. 빌릴 유저 객체를 가져온다. Book조회 때와 마찬가지로 없다면 예외를 던진다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        //4. user 객체가 책을 대출한다.
        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        //이름으로 user객체를 가저온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
    }
}
































