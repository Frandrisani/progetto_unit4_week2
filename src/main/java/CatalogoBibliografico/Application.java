package CatalogoBibliografico;


import CatalogoBibliografico.entities.Book;
import CatalogoBibliografico.entities.Riviste;
import CatalogoBibliografico.interfaces.Catalogo;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.*;

public class Application {

    public static void main(String[] args) {
        //----- Utilizzo la classe Faker per generare dati casuali -----
        Faker faker = new Faker();
        // ----- Dichiaro un set di elementi di tipo Catalogo. Perché è un set? Così non posso inserire due volte lo stesso elemento.
        // ----- Perchè creo un'interfaccia "Catalogo"? Così posso inserire sia inserire elementi di tipo Riviste che di tipo Book.
        Set<Catalogo> elementi = new HashSet<>();
        // ----- 5 libri -----
        Book b1 = new Book(123456789, faker.programmingLanguage().name(), LocalDate.of(2019, 1, 1), 206, "Luca x", faker.book().genre());
        Book b2 = new Book(987654321, faker.programmingLanguage().name(), LocalDate.of(2023, 4, 25), 95, "Giacomo y", faker.book().genre());
        Book b3 = new Book(134679852, faker.programmingLanguage().name(), LocalDate.of(2023, 8, 16), 347, "Lidia z", faker.book().genre());
        Book b4 = new Book(976431852, faker.programmingLanguage().name(), LocalDate.of(2021, 9, 19), 194, "Giacomo  y", faker.book().genre());
        Book b5 = new Book(197346825, faker.programmingLanguage().name(), LocalDate.of(2021, 2, 6), 83, "Luca x", faker.book().genre());
        // ----- 5 riviste -----
        Riviste r1 = new Riviste(112233445, faker.company().name(), LocalDate.of(2017, 6, 4), 25, Periodicita.MENSILE);
        Riviste r2 = new Riviste(223344556, faker.company().name(), LocalDate.of(2022, 7, 24), 19, Periodicita.SETTIMANALE);
        Riviste r3 = new Riviste(334455667, faker.company().name(), LocalDate.of(2020, 1, 30), 75, Periodicita.SEMESTRALE);
        Riviste r4 = new Riviste(445566778, faker.company().name(), LocalDate.of(2023, 5, 16), 15, Periodicita.SETTIMANALE);
        Riviste r5 = new Riviste(556677889, faker.company().name(), LocalDate.of(2016, 10, 9), 32, Periodicita.MENSILE);

        // ----- Aggiungo i libri e le riviste al set, in questo modo all'esecuzione del programma saranno presenti già 10 elementi di default -----
        elementi.add(b1);
        elementi.add(b2);
        elementi.add(b3);
        elementi.add(b4);
        elementi.add(b5);
        elementi.add(r1);
        elementi.add(r2);
        elementi.add(r3);
        elementi.add(r4);
        elementi.add(r5);

        // ----- Creo due Supplier per generare dati casuali -----
        Supplier<Integer> randomGiorni = () -> {
            Random rndm = new Random();
            return rndm.nextInt(1, 31);
        };

        Supplier<Integer> randomMesi= () -> {
            Random rndm = new Random();
            return rndm.nextInt(1, 12);
        };

        // ----- Creo un menu di navigazione per far effettuare una scelta delle possibili azioni -----
        boolean continuo = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nel Catalogo Bibliografico!\n");
        do {
            System.out.println(" 1. Aggiungere elemento \n 2. Rimozione elemento tramite codice ISBN \n 3. Ricerca per ISBN \n 4. Ricerca per anno di publicazione \n 5. Ricerca per autore \n 6. Salvataggio su disco dell'archivio \n 7. Caricamento dal disco dell'archivio \n 8. Esci");
            int scelta = Integer.parseInt(scanner.nextLine());
            switch (scelta) {
                // ----- Aggiunta di un elemento -----
                case 1:
                System.out.println("Hai selezionato l'opzione 1. Aggiungere elemento");
                    System.out.println("1. Rivista / 2. Libro");
                    int scelta2 = Integer.parseInt(scanner.nextLine());
                    if (scelta2 == 1) {
                        System.out.println("Hai selezionato l'opzione 1. Rivista");
                        System.out.println("Inserisci i dati della nuova rivista: ");
                        System.out.println("ISBN: ");
                        long isbn = Long.parseLong(scanner.nextLine());
                        System.out.println("Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.println("Anno di pubblicazione: ");
                        int annoPubblicazione = Integer.parseInt(scanner.nextLine());
                        System.out.println("Numero di pagine: ");
                        int numeroPagine = Integer.parseInt(scanner.nextLine());
                        System.out.println("Periodicità: ");
                        Periodicita periodicita = Periodicita.valueOf(scanner.nextLine());
                        elementi.add(new Riviste(isbn, titolo, LocalDate.of(annoPubblicazione, randomMesi.get(), randomGiorni.get()), numeroPagine, periodicita));
                        elementi.forEach(System.out::println);
                    } else if (scelta2 == 2) {
                        System.out.println("Hai selezionato l'opzione 2. Libro");
                        System.out.println("Inserisci i dati del nuovo libro: ");
                        System.out.println("ISBN: ");
                        long isbn = scanner.nextLong();
                        System.out.println("Titolo: ");
                        String titolo = scanner.next();
                        System.out.println("Anno di pubblicazione: ");
                        int annoPubblicazione = Integer.parseInt(scanner.nextLine());
                        System.out.println("Numero di pagine: ");
                        int numeroPagine = Integer.parseInt(scanner.nextLine());
                        System.out.println("Autore: ");
                        String autore = scanner.nextLine();
                        System.out.println("Genere: ");
                        String genere = scanner.nextLine();
                        elementi.add(new Book(isbn, titolo, LocalDate.of(annoPubblicazione, randomMesi.get(), randomGiorni.get()), numeroPagine, autore, genere));
                        elementi.forEach(System.out::println);
                    }
                break;
                    // ----- Rimozione elemento tramite codice ISBN -----
                case 2:
                    System.out.println("Hai selezionato l'opzione 2. Rimozione elemento tramite codice ISBN");
                    System.out.println("Inserisci il codice ISBN del libro da rimuovere: ");
                    long isbn = Long.parseLong(scanner.nextLine());

                    // Cerca l'elemento con il codice ISBN inserito
                    // Cosa faccio qui? Creo uno stream dalla lista elementi e filtro gli elementi che hanno il codice ISBN corrispondente
                    Catalogo elementiDaRimuovere = elementi.stream()
                            .filter(i -> {
                               // Con instanceof verifico se l'elemento è di tipo Book o Riviste
                                if (i instanceof Book) {
                                    // Con getISBN() recupero il codice ISBN dell'elemento
                                    return ((Book) i).getISBN() == isbn;
                                } else if (i instanceof Riviste) {
                                    return ((Riviste) i).getISBN() == isbn;
                                }
                                return false;
                            })
                            // Con findFirst() cerco l'elemento che ha il codice ISBN inserito, il primo che trova
                            .findFirst()
                            // Con una ricerca veb noto che con orElse si può specificare un valore di default nel caso in cui non trovi l'elemento, in questo caso
                            .orElse(null);

                    // Se l'elemento è stato trovato, lo mostri e lo rimuovi dalla lista
                    //iniziamo con un if per verificare se l'elemento è diverso da null
                    if (elementiDaRimuovere != null) {
                        //se lo è lo mostriamo, avendo sempre cura di verificare si si tratta di un libro o di una rivista
                        System.out.println("Elemento trovato:");
                        if (elementiDaRimuovere instanceof Book) {
                            System.out.println(((Book) elementiDaRimuovere).toString());
                        } else if (elementiDaRimuovere instanceof Riviste) {
                            System.out.println(((Riviste) elementiDaRimuovere).toString());
                        }
                        //Una volta mostrato all'utente quale sarà l'elemento da rimuovere, con removeIf si rimuove l'elemento dalla lista
                        elementi.removeIf(i -> {
                            if (i instanceof Book) {
                                return ((Book) i).getISBN() == isbn;
                            } else if (i instanceof Riviste) {
                                return ((Riviste) i).getISBN() == isbn;
                            }
                            return false;
                        });

                        System.out.println("Elemento rimosso con successo.");
                        elementi.forEach(System.out::println);
                    } else {
                        System.err.println("Nessun elemento trovato con il codice ISBN specificato.");
                    }
                    break;
                    // ----- Ricerca elemento tramite codice ISBN -----
                case 3:
                    System.out.println("Hai selezionato l'opzione 3. Ricerca elemento tramite codice ISBN");
                    System.out.println("Inserisci il codice ISBN del libro da cercare: ");
                    long isbn2 = Long.parseLong(scanner.nextLine());
                    // Cerca l'elemento con il codice ISBN inserito
                    // Creo uno stream dalla lista elementi e filtro gli elementi che hanno il codice ISBN corrispondente
                    try {
                        Catalogo elementiDaCercareTramiteISBN = elementi.stream().filter(i -> {
                                    // Con instanceof verifico se l'elemento è di tipo Book o Riviste
                                    if (i instanceof Book) {
                                        // Con getISBN() recupero il codice ISBN dell'elemento
                                        return ((Book) i).getISBN() == isbn2;
                                    } else if (i instanceof Riviste) {
                                        return ((Riviste) i).getISBN() == isbn2;
                                    }
                                    return false;
                                })
                                .findFirst()
                                .orElse(null);
                        System.out.println("Elemento trovato:");
                        if (elementiDaCercareTramiteISBN instanceof Book) {
                        System.out.println(((Book) elementiDaCercareTramiteISBN).toString());
                    } else if (elementiDaCercareTramiteISBN instanceof Riviste) {
                    System.out.println(((Riviste) elementiDaCercareTramiteISBN).toString());
                }

                    }catch (Exception e){
                        System.err.println ("Nessun elemento trovato con il codice ISBN specificato.");
            }

                break;
                // ----- Ricerca elemento per anno di pubblicazione-----
                case 4:
                    System.out.println("Hai selezionato l'opzione 4. Ricerca elementi per anno di pubblicazione");
                    System.out.println("Inserisci l'anno di pubblicazione: ");
                    int annoPubblicazione2 = Integer.parseInt(scanner.nextLine());
                    // Cerca l'elemento con l'anno di pubblicazione inserito
                    // Creo uno stream dalla lista elementi e filtro gli elementi che hanno l'anno di pubblicazione corrispondente
                    try{
                        elementi.stream().filter(i -> {
                            if (i instanceof Book) {
                            return ((Book) i).getAnnoPubblicazione().getYear() == annoPubblicazione2;
                        } else if (i instanceof Riviste) {
                            return ((Riviste) i).getAnnoPubblicazione().getYear() == annoPubblicazione2;
                        }
                        return false;
                    }).forEach(System.out::println);
            }catch(Exception e){
                System.err.println("Nessun elemento trovato con l'anno di pubblicazione specificato.");
                    }
                    break;
                    // ----- Ricerca per autore -----
                case 5:
                    System.out.println("Hai selezionato l'opzione 5. Ricerca per autore");
                    System.out.println("Inserisci l'anno di pubblicazione: ");

                    break;
            }
        }while (!continuo);
    }
}
