package com.example.imuvi;

public class Movie {
    //MOVIE
    String imdbId;
    String title;
    String year;
    String rated;
    String released;
    String runtime;
    String genre;
    String director;
    String writer;
    String actors;
    String plot;
    String language;
    String country;
    String awards;
    String metascore;
    String imdbrating;
    String imdbvotes;
    String type;
    String dvd;
    String boxoffice;
    String production;
    String website;

    //RATINGS
    String source;
    String value;

    public Movie(String idImdb, String titleMovie){
        this.imdbId = idImdb;
        this.title = titleMovie;
    }

    public Movie(){


    }

    //IMDB ID
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
    //

    //TITLE
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //

    //YEAR
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    //

    //RATED
    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }
    //

    //RELEASED
    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }
    //

    //ACTORS
    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
    //

    //AWARDS
    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }
    //

    //BOXOFFICE
    public String getBoxoffice() {
        return boxoffice;
    }

    public void setBoxoffice(String boxoffice) {
        this.boxoffice = boxoffice;
    }
    //

    //COUNTRY
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    //

    //DIRECTOR
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    //


    //DVD
    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }
    //

    //GENRE
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    //

    //IMDBRATING
    public String getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(String imdbrating) {
        this.imdbrating = imdbrating;
    }
    //

    //IMDBVOTES
    public String getImdbvotes() {
        return imdbvotes;
    }

    public void setImdbvotes(String imdbvotes) {
        this.imdbvotes = imdbvotes;
    }
    //

    //LANGUAGE
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    //

    //METASCORE
    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }
    //

    //PLOT
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
    //

    //PRODUCTION
    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }
    //

    //RUNTIME
    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
    //

    //SOURCE
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    //

    //TYPE
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //

    //VALUE
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //

    //WEBSITE
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    //

    //WRITERS
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }


    //


}
