package com.example.rentcarfrontend.books;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("test")
public class MainView extends VerticalLayout {

    private BookService bookService = BookService.getInstance();
    private Grid<Book> grid = new Grid<>(Book.class);
    //searchfield
    private TextField filter = new TextField();
    private BookForm form = new BookForm(this);

    //new book button
    private Button addNewBook = new Button("Add new book");




    public MainView() {
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("title", "author", "publicationYear", "type");
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        //add book button setup
        addNewBook.addClickListener(e -> {
            grid.asSingleSelect().clear(); //"czyścimy" zaznaczenie
            form.setBook(new Book());      //dodajemy nowy obiekt do formularza
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBook);

        add(toolbar, mainContent);
        form.setBook(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setBook(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(bookService.getBooks());
    }
    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }
}
