package CatalogoBibliografico.entities;

import CatalogoBibliografico.Periodicita;
import CatalogoBibliografico.interfaces.Catalogo;

import java.time.LocalDate;

public class Riviste implements Catalogo {
    // Attributes
    private long isbn;
    private String titolo;
    private LocalDate annoPubblicazione;
    private int numeroPagine;
    private Periodicita periodicita;

    // Constructor
    public Riviste(long isbn, String titolo, LocalDate annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        this.isbn =isbn;
        this.titolo =titolo;
        this.annoPubblicazione =annoPubblicazione;
        this.numeroPagine =numeroPagine;
        this.periodicita =periodicita;
}

    public long getISBN() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", periodicita=" + periodicita +
                '}';
    }
}
