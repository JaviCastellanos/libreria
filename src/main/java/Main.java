import java.util.List;
import java.util.Scanner;
import api.GutendexAPI;
import api.GutendexResponse;
import api.Person;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GutendexAPI gutendexAPI = new GutendexAPI();

        while (true) {
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idiomas");
            System.out.println("0. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String titleQuery = scanner.nextLine();
                        List<GutendexResponse> searchResults = gutendexAPI.searchBooksByTitle(titleQuery);
                        if (searchResults == null || searchResults.isEmpty()) {
                            System.out.println("El libro no se encuentra en la base de datos.");
                        } else {
                            for (GutendexResponse book : searchResults) {
                                System.out.println("ID: " + book.getId());
                                System.out.println("Título: " + book.getTitle());
                                System.out.println("Autores: " + book.getAuthors().get(0).getName());
                                System.out.println("...");
                            }
                        }
                        break;
                    case 2:
                        List<GutendexResponse> allBooks = gutendexAPI.searchBooks("");
                        if (allBooks != null) {
                            for (GutendexResponse book : allBooks) {
                                System.out.println("ID: " + book.getId() + ", Título: " + book.getTitle());
                            }
                        }
                        break;
                    case 3:
                        List<GutendexResponse> authorsResults = gutendexAPI.searchBooks("");
                        Set<String> authors = new HashSet<>();
                        if (authorsResults != null) {
                            for (GutendexResponse book : authorsResults) {
                                for (Person author : book.getAuthors()) {
                                    authors.add(author.getName());
                                }
                            }
                            for (String author : authors) {
                                System.out.println("Autor: " + author);
                            }
                        }
                        break;
                    case 4:
                        System.out.print("Ingrese el año: ");
                        int year = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer
                        List<GutendexResponse> yearResults = gutendexAPI.searchBooks("author_year_start=" + year + "&author_year_end=" + year);
                        Set<String> authorsInYear = new HashSet<>();
                        if (yearResults != null) {
                            for (GutendexResponse book : yearResults) {
                                for (Person author : book.getAuthors()) {
                                    if ((author.getBirth_year() <= year) && (author.getDeath_year() >= year || author.getDeath_year() == 0)) {
                                        authorsInYear.add(author.getName());
                                    }
                                }
                            }
                            for (String author : authorsInYear) {
                                System.out.println("Autor vivo en " + year + ": " + author);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Seleccione un idioma:");
                        List<GutendexResponse> languageResults = gutendexAPI.searchBooks("");
                        Set<String> languages = new HashSet<>();
                        if (languageResults != null) {
                            for (GutendexResponse book : languageResults) {
                                languages.addAll(book.getLanguages());
                            }
                            int index = 1;
                            for (String lang : languages) {
                                System.out.println(index + ". " + lang);
                                index++;
                            }
                            int languageChoice = scanner.nextInt();
                            scanner.nextLine(); // Limpiar buffer
                            String selectedLanguage = (String) languages.toArray()[languageChoice - 1];
                            List<GutendexResponse> booksByLanguage = gutendexAPI.searchBooks("languages=" + selectedLanguage);
                            if (booksByLanguage != null) {
                                for (GutendexResponse book : booksByLanguage) {
                                    System.out.println("ID: " + book.getId() + ", Título: " + book.getTitle());
                                }
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }
}
