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
    String poster;
    String metascore;
    String imdbrating;
    String imdbvotes;
    String type;
    String dvd;
    String boxoffice;
    String production;
    String website;
    String totalseasons;



    public Movie(String idImdb, String titleMovie, String yearMovie, String ratedMovie, String releasedMovie,
                 String runtimeMovie, String genreMovie, String directorMovie, String writerMovie, String actorsMovie,
                 String plotMovie, String languageMovie, String countryMovie, String awardsMovie, String posterMovie,
                 String metascoreMovie, String imdbratingMovie, String imdbvotesMovie, String typeMovie, String dvdMovie,
                 String boxofficeMovie, String productionMovie, String websiteMovie, String totalseasonsMovie){

        this.imdbId = idImdb;
        this.title = titleMovie;
        this.year = yearMovie;
        this.rated = ratedMovie;
        this.released = releasedMovie;
        this.runtime = runtimeMovie;
        this.genre = genreMovie;
        this.director = directorMovie;
        this.writer = writerMovie;
        this.actors = actorsMovie;
        this.plot = plotMovie;
        this.language = languageMovie;
        this.country = countryMovie;
        this.awards = awardsMovie;
        this.poster = posterMovie;
        this.metascore = metascoreMovie;
        this.imdbrating = imdbratingMovie;
        this.imdbvotes = imdbvotesMovie;
        this.type = typeMovie;
        this.dvd = dvdMovie;
        this.boxoffice = boxofficeMovie;
        this.production = productionMovie;
        this.website = websiteMovie;
        this.totalseasons = totalseasonsMovie;
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

    //POSTER
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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



    //TYPE
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    //TOTALSEASONS

    public String getTotalseasons() {
        return totalseasons;
    }

    public void setTotalseasons(String totalseasons) {
        this.totalseasons = totalseasons;
    }

    //

}
